package kodlama.io.hrms.business.concretes;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kodlama.io.hrms.business.abstracts.EmployeeSchoolDepartmentService;
import kodlama.io.hrms.core.utilities.results.Result;
import kodlama.io.hrms.core.utilities.results.SuccessResult;
import kodlama.io.hrms.dataAccess.abstracts.EmployeeSchoolDepartmentDao;
import kodlama.io.hrms.entities.concretes.EmployeeSchoolDepartment;

@Service
public class EmployeeSchoolDepartmentManager implements EmployeeSchoolDepartmentService{

	private EmployeeSchoolDepartmentDao employeeSchoolDepartmentDao;

    @Autowired
    public EmployeeSchoolDepartmentManager(EmployeeSchoolDepartmentDao employeeSchoolDepartmentDao) {
        this.employeeSchoolDepartmentDao = employeeSchoolDepartmentDao;
    }
    
	@Override
	public Result add(EmployeeSchoolDepartment employeeSchoolDepartment) {
		this.employeeSchoolDepartmentDao.save(employeeSchoolDepartment);
        return new SuccessResult();
	}
}