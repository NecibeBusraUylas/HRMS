package kodlama.io.hrms.business.concretes;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import kodlama.io.hrms.business.abstracts.JobPositionService;
import kodlama.io.hrms.core.utilities.results.DataResult;
import kodlama.io.hrms.core.utilities.results.ErrorResult;
import kodlama.io.hrms.core.utilities.results.Result;
import kodlama.io.hrms.core.utilities.results.SuccessDataResult;
import kodlama.io.hrms.core.utilities.results.SuccessResult;
import kodlama.io.hrms.dataAccess.abstracts.JobPositionDao;
import kodlama.io.hrms.entities.concretes.JobPosition;

@Service
public class JobPositionManager implements JobPositionService{

	private JobPositionDao jobPositionDao;

	@Autowired
	public JobPositionManager(JobPositionDao jobPostionDao) {
		super();
		this.jobPositionDao = jobPostionDao;
	}

	@Override
	public DataResult<List<JobPosition>>getAll() {
		return new SuccessDataResult<List<JobPosition>>(this.jobPositionDao.findAll(),"Job positions listed!");
	}
	
	@Override
	public DataResult<JobPosition> getById(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Result add(JobPosition jobPosition) {
		if(checkIfJobTitleAlreadyExists(jobPosition)) {
			return new ErrorResult("This job title already exists!");
		}	
		jobPositionDao.save(jobPosition);
		return new SuccessResult("Job position added!");
	}

	@Override
	public Result update(JobPosition jobPosition) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Result delete(JobPosition jobPosition) {
		// TODO Auto-generated method stub
		return null;
	}
	
	private boolean checkIfJobTitleAlreadyExists(JobPosition jobPosition) {	
		if(jobPositionDao.findByName(jobPosition.getName()) == null) {			
			return false;			
		}			
		return true;
	}
}