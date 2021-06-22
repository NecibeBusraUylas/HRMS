package kodlama.io.hrms.business.concretes;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import kodlama.io.hrms.business.abstracts.EmployerService;
import kodlama.io.hrms.business.abstracts.UserService;
import kodlama.io.hrms.core.utilities.business.BusinessRules;
import kodlama.io.hrms.core.utilities.results.*;
import kodlama.io.hrms.dataAccess.abstracts.EmployerDao;
import kodlama.io.hrms.entities.concretes.Employer;
import kodlama.io.hrms.entities.concretes.User;
import kodlama.io.hrms.entities.dtos.EmployerRegisterDto;

@Service
public class EmployerManager implements EmployerService{
	private EmployerDao employerDao;
	private UserService userService;
	
	@Autowired
	public EmployerManager(EmployerDao employerDao, UserService userService) {
		this.employerDao = employerDao;
		this.userService = userService;
	}

	@Override
	public DataResult<List<Employer>> getAll() {
		return new SuccessDataResult<List<Employer>>(this.employerDao.findAll(),"Employers listed!");
	}

	@Override
	public Result add(EmployerRegisterDto employer) {
		Result businessRules = BusinessRules.run(
				isPasswordsMatch(employer.getPassword(), employer.getVerifyPassword()),
	            isEmailandWebAddressDomainSame(employer.getEmail(), employer.getWebAddress())
	    );
	    if (businessRules != null) return businessRules;
	    User userToRegister = new User(employer.getEmail(), employer.getPassword(), false, UUID.randomUUID().toString());
	    this.userService.add(userToRegister);

	    Employer employerToRegister = new Employer(userToRegister.getId(), employer.getCompanyName(), employer.getPhone(), false, employer.getWebAddress());
	    this.employerDao.save(employerToRegister);

	     return new SuccessResult("Employer has created an account in the system. Please confirm your email address by clicking the link sent your email address.");
	}

	@Override
	public DataResult<Employer> getById(int id) {
		Employer employer = this.employerDao.findById(id);
        if (employer == null)
            return new ErrorDataResult<Employer>();
        return new SuccessDataResult<Employer>(employer);
	}

	@Override
	public Boolean existById(int id) {
		boolean isEmployerExist=this.employerDao.existsById(id);
		if(!isEmployerExist)
			return false;
		return true;
	}
	
	private Result isPasswordsMatch(String password, String passwordConfirm) {
		if (!password.equals(passwordConfirm)) 
			return new ErrorResult("Password error!" + passwordConfirm);
	    return new SuccessResult();
	}
	

    private Result isEmailandWebAddressDomainSame(String email, String webAddress) {
        String[] emailSplit = email.split("@");
        if (emailSplit.length < 2)
            return new ErrorResult("Email format error!");

        if (!webAddress.contains(emailSplit[1]))
            return new ErrorResult("The web address and the email address are not in the same domain!");

        return new SuccessResult();
    }

    private Result isEmailAlreadyExist(String email) {
        if (this.userService.getByEmail(email).getData() != null)
            return new ErrorResult("This email address already exists in the system!");
        return new SuccessResult();
    }

}
