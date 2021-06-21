package kodlama.io.hrms.business.abstracts;

import java.util.List;

import kodlama.io.hrms.core.utilities.results.DataResult;
import kodlama.io.hrms.core.utilities.results.Result;
import kodlama.io.hrms.entities.concretes.Employer;
import kodlama.io.hrms.entities.dtos.EmployerRegisterDto;

public interface EmployerService {

	DataResult<List<Employer>> getAll();
	DataResult<Employer> getById(int id);
	Boolean existById(int id);
	Result add(EmployerRegisterDto employer);
}