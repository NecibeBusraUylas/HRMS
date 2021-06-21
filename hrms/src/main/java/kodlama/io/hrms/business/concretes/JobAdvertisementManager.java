package kodlama.io.hrms.business.concretes;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kodlama.io.hrms.business.abstracts.CityService;
import kodlama.io.hrms.business.abstracts.EmployerService;
import kodlama.io.hrms.business.abstracts.JobAdvertisementService;
import kodlama.io.hrms.business.abstracts.JobPositionService;
import kodlama.io.hrms.core.utilities.business.BusinessRules;
import kodlama.io.hrms.core.utilities.results.DataResult;
import kodlama.io.hrms.core.utilities.results.ErrorResult;
import kodlama.io.hrms.core.utilities.results.Result;
import kodlama.io.hrms.core.utilities.results.SuccessDataResult;
import kodlama.io.hrms.core.utilities.results.SuccessResult;
import kodlama.io.hrms.dataAccess.abstracts.JobAdvertisementDao;
import kodlama.io.hrms.entities.concretes.JobAdvertisement;
import kodlama.io.hrms.entities.dtos.JobAdvertisementAddDto;

@Service
public class JobAdvertisementManager implements JobAdvertisementService{
	private JobAdvertisementDao jobAdvertisementDao;
    private JobPositionService jobPositionService;
    private CityService cityService;
    private EmployerService employerService;

    @Autowired
    public JobAdvertisementManager(JobAdvertisementDao jobAdvertisementDao, JobPositionService jobPositionService, CityService cityService, EmployerService employerService) {
        this.jobAdvertisementDao = jobAdvertisementDao;
        this.jobPositionService = jobPositionService;
        this.cityService = cityService;
        this.employerService = employerService;
    }
    
	@Override
	public DataResult<List<JobAdvertisement>> getAll() {
		return new SuccessDataResult<List<JobAdvertisement>>(this.jobAdvertisementDao.findAll(), "Job advertisements listed!");
	}

	@Override
	public DataResult<List<JobAdvertisement>> findByIsActiveTrue() {
		 return new SuccessDataResult<List<JobAdvertisement>>(this.jobAdvertisementDao.findByIsActiveTrue());
	}

	@Override
	public DataResult<List<JobAdvertisement>> findByIsActiveTrueOrderByCreateDate() {
		return new SuccessDataResult<List<JobAdvertisement>>(this.jobAdvertisementDao.findByIsActiveTrueOrderByCreateDate());
	}

	@Override
	public DataResult<List<JobAdvertisement>> finfByIsActiveTrueAndEmployer_Id(int employerId) {
		return new SuccessDataResult<List<JobAdvertisement>>(this.jobAdvertisementDao.findByIsActiveTrueAndEmployer_Id(employerId));
	}

	@Override
	public Result changeStatus(int jobadvertisementId, int employerId) {
		JobAdvertisement jobAdvertisementToUpdate = this.jobAdvertisementDao.findByIdAndEmployer_Id(jobadvertisementId, employerId);
	    if (jobAdvertisementToUpdate == null)
	    	return new ErrorResult("There are no job advertisement which satisfy the criteria!");
	    jobAdvertisementToUpdate.setActive(!jobAdvertisementToUpdate.isActive());
	    this.jobAdvertisementDao.save(jobAdvertisementToUpdate);
	    return new SuccessResult("Job advertisement's status changed to " + (jobAdvertisementToUpdate.isActive() ? "active" : "passive"));
	}

	@Override
	public Result add(JobAdvertisementAddDto jobAdvertisementAddDto) {
		Result businessRules = BusinessRules.run(
                isJobPositionValid(jobAdvertisementAddDto.getJobPositionId()),
                isJobDescriptionValid(jobAdvertisementAddDto.getJobDescription()),
                isCityValid(jobAdvertisementAddDto.getCityId()),
                isOpenPositionValid(jobAdvertisementAddDto.getOpenPositionCount()),
                isLastApplicationDateValid(jobAdvertisementAddDto.getLastApplicationDate())
        );
        if (businessRules != null)
            return businessRules;      
        if (!this.employerService.existById(jobAdvertisementAddDto.getEmployerId()))
            return new ErrorResult("There is no employer in the system that matches this name.");
        JobAdvertisement jobAdvertisementToAdd = new JobAdvertisement(
                jobAdvertisementAddDto.getJobDescription(),
                jobAdvertisementAddDto.getMinSalary(),
                jobAdvertisementAddDto.getMaxSalary(),
                jobAdvertisementAddDto.getOpenPositionCount(),
                jobAdvertisementAddDto.getLastApplicationDate(),
                new Date(),
                jobAdvertisementAddDto.isActive()
        );
        jobAdvertisementToAdd.setCity(this.cityService.getById(jobAdvertisementAddDto.getCityId()).getData());
        jobAdvertisementToAdd.setJobPosition(this.jobPositionService.getById(jobAdvertisementAddDto.getJobPositionId()).getData());
        jobAdvertisementToAdd.setEmployer(this.employerService.getById(jobAdvertisementAddDto.getEmployerId()).getData());
        this.jobAdvertisementDao.save(jobAdvertisementToAdd);

        return new SuccessResult("Job advertisement created successfully!");
	}
	
	 private Result isJobPositionValid(int id) {
	        if (id <= 0)
	            return new ErrorResult("Job position entered incorrectly!");
	        if (this.jobPositionService.getById(id).getData() == null)
	            return new ErrorResult("There is no job position in the system that matches this name.");
	        return new SuccessResult();
	    }

	    private Result isJobDescriptionValid(String jobDescription) {
	        if (jobDescription == null || jobDescription.equals(""))
	            return new ErrorResult("Job description entered incorrectly!");
	        return new SuccessResult();
	    }

	    private Result isCityValid(int id) {
	        if (id <= 0)
	            return new ErrorResult("City entered incorrectly");
	        if (this.cityService.getById(id).getData() == null)
	            return new ErrorResult("There is no city in the system that matches this name.");
	        return new SuccessResult();
	    }

	    private Result isOpenPositionValid(int count) {
	        if (count <= 0)
	            return new ErrorResult("The number of open positions cannot be equal to or less than 0.");
	        return new SuccessResult();
	    }

	    private Result isLastApplicationDateValid(Date endDate) {
	        if (new Date().compareTo(endDate) > 0)
	            return new ErrorResult("Last application date can not be before the creation of advertisement.");
	        return new SuccessResult();
	    }
}