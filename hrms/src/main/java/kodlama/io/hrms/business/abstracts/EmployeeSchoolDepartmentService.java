package kodlama.io.hrms.business.abstracts;

import kodlama.io.hrms.core.utilities.results.Result;
import kodlama.io.hrms.entities.concretes.EmployeeSchoolDepartment;

public interface EmployeeSchoolDepartmentService {
	Result add(EmployeeSchoolDepartment employeeSchoolDepartment);
}