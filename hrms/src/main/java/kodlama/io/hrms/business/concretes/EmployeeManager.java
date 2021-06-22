package kodlama.io.hrms.business.concretes;

import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kodlama.io.hrms.business.abstracts.EmployeeService;
import kodlama.io.hrms.business.abstracts.UserService;
import kodlama.io.hrms.core.utilities.adapters.ValidationService;
import kodlama.io.hrms.core.utilities.business.BusinessRules;
import kodlama.io.hrms.core.utilities.results.DataResult;
import kodlama.io.hrms.core.utilities.results.ErrorDataResult;
import kodlama.io.hrms.core.utilities.results.ErrorResult;
import kodlama.io.hrms.core.utilities.results.Result;
import kodlama.io.hrms.core.utilities.results.SuccessDataResult;
import kodlama.io.hrms.core.utilities.results.SuccessResult;
import kodlama.io.hrms.dataAccess.abstracts.EmployeeDao;
import kodlama.io.hrms.entities.concretes.Employee;
import kodlama.io.hrms.entities.concretes.User;
import kodlama.io.hrms.entities.dtos.EmployeeRegisterDto;

@Service
public class EmployeeManager implements EmployeeService{
	private EmployeeDao employeeDao;
	private UserService userService;
	private ValidationService validationService;
	
	@Autowired
	public EmployeeManager(EmployeeDao employeeDao, UserService userService, ValidationService validationService) {
		this.employeeDao = employeeDao;
	    this.userService = userService;
	    this.validationService = validationService;
	}
	
	@Override
	public DataResult<Employee> getById(int id) {
		Employee employee = this.employeeDao.findById(id);
	    if (employee == null)
	    	return new ErrorDataResult<Employee>();
	    return new SuccessDataResult<Employee>(employee);
	}

	@Override
	public DataResult<List<Employee>> getAll() {
		return new SuccessDataResult<List<Employee>>(this.employeeDao.findAll(),"Employees listed!");
	}

	@Override
	public Result add(EmployeeRegisterDto employee) {
		 Result businessRules = BusinessRules.run(
	                isPasswordsMatch(employee.getPassword(), employee.getVerifyPassword()),
	                isUserExistWithEmail(employee.getEmail()),
	                isUserExistWithNationalId(employee.getNationalId())
	        );
	        if (businessRules != null) return businessRules;

	        Calendar calendar = new GregorianCalendar();
	        calendar.setTime(employee.getDateOfBirth());

	        if (!this.validationService.validate(employee.getNationalId(), employee.getFirstName(), 
	        		employee.getLastName(), calendar.get(Calendar.YEAR)).isSuccess()) {
	            return new ErrorResult("Validation failed!");
	        }

	        User userToRegister = new User(employee.getEmail(), employee.getPassword(), false, UUID.randomUUID().toString());
	        this.userService.add(userToRegister);

	        Employee employeeToRegister = new Employee(
	                userToRegister.getId(),
	                employee.getFirstName(),
	                employee.getLastName(),
	                employee.getNationalId(),
	                employee.getDateOfBirth());
	        this.employeeDao.save(employeeToRegister);

	        return new SuccessResult("Employee has created an account in the system. Please confirm your email address by clicking the link sent your email address.");
	}
	
	private Result isPasswordsMatch(String password, String passwordConfirm) {
        if (!password.equals(passwordConfirm))
            return new ErrorResult("Password error!");
        return new SuccessResult();
    }

    private Result isUserExistWithEmail(String email) {
        if (this.userService.getByEmail(email).getData() != null)
            return new ErrorResult("This email address already exists in the system!");
        return new SuccessResult();
    }

    private Result isUserExistWithNationalId(String nationalId) {
        if (this.employeeDao.findByNationalId(nationalId) != null)
            return new ErrorResult("This national id already exists in the system!");
        return new SuccessResult();
    }
}