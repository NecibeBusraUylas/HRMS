package kodlama.io.hrms.business.concretes;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kodlama.io.hrms.business.abstracts.EmployeeSchoolDepartmentService;
import kodlama.io.hrms.business.abstracts.EmployeeService;
import kodlama.io.hrms.business.abstracts.SchoolDepartmentService;
import kodlama.io.hrms.core.utilities.results.DataResult;
import kodlama.io.hrms.core.utilities.results.Result;
import kodlama.io.hrms.core.utilities.results.SuccessDataResult;
import kodlama.io.hrms.core.utilities.results.SuccessResult;
import kodlama.io.hrms.dataAccess.abstracts.SchoolDepartmentDao;
import kodlama.io.hrms.entities.concretes.EmployeeSchoolDepartment;
import kodlama.io.hrms.entities.dtos.SchoolDepartmentAddDto;
import kodlama.io.hrms.entities.dtos.SchoolDepartmentDetailsDto;

@Service
public class SchoolDepartmentManager implements SchoolDepartmentService {
    private SchoolDepartmentDao schoolDepartmentDao;
    private EmployeeSchoolDepartmentService employeeSchoolDepartmentService;
    private EmployeeService employeeService;

    @Autowired
    public SchoolDepartmentManager(SchoolDepartmentDao schoolDepartmentDao, EmployeeSchoolDepartmentService employeeSchoolDepartmentService, EmployeeService employeeService) {
        this.schoolDepartmentDao = schoolDepartmentDao;
        this.employeeSchoolDepartmentService = employeeSchoolDepartmentService;
        this.employeeService = employeeService;
    }

    @Override
    public DataResult<List<SchoolDepartmentDetailsDto>> findByEmployeeSchoolDepartments_Employee_UserId(int id) {
        return new SuccessDataResult<List<SchoolDepartmentDetailsDto>>(this.schoolDepartmentDao.findByEmployeeSchoolDepartments_Employee_UserId(id));
    }

	@Override
	public Result assignToEmployee(SchoolDepartmentAddDto schoolDepartment) {
		this.employeeSchoolDepartmentService.add(new EmployeeSchoolDepartment(
				this.employeeService.getById(schoolDepartment.getEmployee().getId()).getData(),
				this.schoolDepartmentDao.getById(schoolDepartment.getSchoolDepartment().getId()),
				schoolDepartment.getStartDate(), schoolDepartment.getGraduationDate()));
		return new SuccessResult();
	}
}