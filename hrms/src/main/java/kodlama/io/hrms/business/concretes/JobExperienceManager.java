package kodlama.io.hrms.business.concretes;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kodlama.io.hrms.business.abstracts.JobExperienceService;
import kodlama.io.hrms.core.utilities.results.DataResult;
import kodlama.io.hrms.core.utilities.results.Result;
import kodlama.io.hrms.core.utilities.results.SuccessDataResult;
import kodlama.io.hrms.core.utilities.results.SuccessResult;
import kodlama.io.hrms.dataAccess.abstracts.JobExperienceDao;
import kodlama.io.hrms.entities.concretes.JobExperience;
import kodlama.io.hrms.entities.concretes.Resume;
import kodlama.io.hrms.entities.dtos.JobExperienceAddDto;

@Service
public class JobExperienceManager implements JobExperienceService{
	
	private JobExperienceDao jobExperienceDao;

    @Autowired
    public JobExperienceManager(JobExperienceDao jobExperienceDao) {
    	this.jobExperienceDao = jobExperienceDao;
	}
	    
	@Override
	public DataResult<List<JobExperience>> findByResume_Id(int id) {
		return new SuccessDataResult<List<JobExperience>>(this.jobExperienceDao.findByResume_IdOrderByEndDateDesc(id));
	}

	@Override
	public Result add(JobExperienceAddDto jobExperience, Resume resume) {
		JobExperience jobExperienceToAdd = new JobExperience(
				jobExperience.getOfficeName(),
                jobExperience.getPosition(),
                jobExperience.getStartDate(),
                jobExperience.getEndDate(),
                resume);
        this.jobExperienceDao.save(jobExperienceToAdd);
        return new SuccessResult();
	}

	@Override
	public Result addRange(List<JobExperienceAddDto> jobExperiences, Resume resume) {
		for (JobExperienceAddDto jobExperience : jobExperiences) {
            JobExperience jobExperienceToAdd = new JobExperience(
                    jobExperience.getOfficeName(),
                    jobExperience.getPosition(),
                    jobExperience.getStartDate(),
                    jobExperience.getEndDate(),
                    resume);
            this.jobExperienceDao.save(jobExperienceToAdd);
        }
        return new SuccessResult();
    }
}