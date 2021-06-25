package kodlama.io.hrms.business.abstracts;

import java.util.List;

import kodlama.io.hrms.core.utilities.results.DataResult;
import kodlama.io.hrms.core.utilities.results.Result;
import kodlama.io.hrms.entities.dtos.SchoolDepartmentAddDto;
import kodlama.io.hrms.entities.dtos.SchoolDepartmentDetailsDto;

public interface SchoolDepartmentService {
	DataResult<List<SchoolDepartmentDetailsDto>> findByEmployeeSchoolDepartments_Employee_UserId(int id);

    Result assignToEmployee(SchoolDepartmentAddDto schoolDepartment);
}