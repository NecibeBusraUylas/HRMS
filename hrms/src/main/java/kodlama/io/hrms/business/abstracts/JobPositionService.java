package kodlama.io.hrms.business.abstracts;

import java.util.List;

import kodlama.io.hrms.core.utilities.results.DataResult;
import kodlama.io.hrms.core.utilities.results.Result;
import kodlama.io.hrms.entities.concretes.JobPosition;

public interface JobPositionService {
	
	DataResult<List<JobPosition>> getAll();
	DataResult<JobPosition> getById(int id);
	DataResult<JobPosition> getByPositionName(String positionName);
	Result add(JobPosition jobPosition);
}