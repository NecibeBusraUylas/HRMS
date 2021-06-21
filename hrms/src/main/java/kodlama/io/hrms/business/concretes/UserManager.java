package kodlama.io.hrms.business.concretes;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kodlama.io.hrms.business.abstracts.UserService;
import kodlama.io.hrms.core.utilities.email.EmailService;
import kodlama.io.hrms.core.utilities.results.*;
import kodlama.io.hrms.dataAccess.abstracts.UserDao;
import kodlama.io.hrms.entities.concretes.User;

@Service
public class UserManager implements UserService{
	
	 private UserDao userDao;
	 private EmailService emailService;

	 @Autowired
	 public UserManager(UserDao userDao, EmailService emailService) {
		 this.userDao = userDao;
	     this.emailService = emailService;
	 }

	 @Override
	 public DataResult<List<User>> getAll() {
		 return new SuccessDataResult<List<User>>(userDao.findAll(), "Users listed!");
	 }

	 @Override
	 public DataResult<User> getByEmail(String email) {
	     return new SuccessDataResult<User>(this.userDao.findByEmail(email));
	 }

	 @Override
	 public Result add(User user) {
	      this.userDao.save(user);
	      this.emailService.send(user.getEmail(), "Verification Link", "Welcome to HRMS " 
			     + "Please click the link below to verify your email. " 
			     + "www.localhost:8080/api/users/verify?email=" + user.getEmail() + "&verifycode=" + user.getEmailVerifyCode());
	      return new SuccessResult("User added!");
	  }

	  @Override
	  public Result verifyUser(String email, String verificationCode) {
		  User user = this.userDao.findByEmailAndEmailVerifyCode(email, verificationCode);
	      if (user == null)
	          return new ErrorResult("Verification Failed!");
	      user.setEmailVerified(true);
	      this.userDao.save(user);
	      return new SuccessResult("User verified!");
	  }
}
