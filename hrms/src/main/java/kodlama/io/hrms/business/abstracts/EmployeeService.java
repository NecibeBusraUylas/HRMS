package kodlama.io.hrms.business.abstracts;

import java.util.List;

import kodlama.io.hrms.core.utilities.results.DataResult;
import kodlama.io.hrms.core.utilities.results.Result;
import kodlama.io.hrms.entities.concretes.Employee;
import kodlama.io.hrms.entities.dtos.EmployeeRegisterDto;

public interface EmployeeService {

	DataResult<List<Employee>> getAll();
	DataResult<Employee> getById(int id);
	Result add(EmployeeRegisterDto employee);
}