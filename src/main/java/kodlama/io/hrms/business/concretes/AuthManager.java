package kodlama.io.hrms.business.concretes;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kodlama.io.hrms.business.abstracts.ActivationCodeService;
import kodlama.io.hrms.business.abstracts.AuthService;
import kodlama.io.hrms.business.abstracts.EmployerService;
import kodlama.io.hrms.business.abstracts.JobSeekerService;
import kodlama.io.hrms.business.abstracts.UserService;
import kodlama.io.hrms.core.utilities.activations.ActivationService;
import kodlama.io.hrms.core.utilities.adapters.ValidationService;
import kodlama.io.hrms.core.utilities.results.ErrorResult;
import kodlama.io.hrms.core.utilities.results.Result;
import kodlama.io.hrms.core.utilities.results.SuccessResult;
import kodlama.io.hrms.entities.concretes.ActivationCode;
import kodlama.io.hrms.entities.concretes.Employer;
import kodlama.io.hrms.entities.concretes.JobSeeker;
import kodlama.io.hrms.entities.dtos.UserForLoginDto;

@Service
public class AuthManager implements AuthService{

	private UserService userService;
	private EmployerService employerService;
	private JobSeekerService jobSeekerService;
	private ValidationService validationService;
	private ActivationCodeService activationCodeService;
	private ActivationService activationService;

	@Autowired
	public AuthManager(UserService userService, EmployerService employerService, JobSeekerService jobSeekerService,
			ValidationService validationService, ActivationCodeService activationCodeService,
			ActivationService activationService) {
		super();
		this.userService = userService;
		this.employerService = employerService;
		this.jobSeekerService = jobSeekerService;
		this.validationService = validationService;
		this.activationCodeService = activationCodeService;
		this.activationService = activationService;
	}

	@Override
	public Result login(UserForLoginDto userForLoginDto) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Result registerEmployer(Employer employer, String confirmPassword) {
		if (checkIfEmployerInfoComplete(employer) == false) {
			return new ErrorResult("You have entered missing information. Please fill in all fields!");
		}
		
		if (checkIfEmailExists(employer.getEmail())) {
			return new ErrorResult(employer.getEmail() + " already exists!");
		}
		
		if (checkIfEmailMatchesDomain(employer.getEmail(), employer.getWebAddress()) == false) {
			return new ErrorResult("Invalid email address! Please enter your company email adress.");
		}
		
		if (checkIfPasswordAndConfirmPasswordMatches(employer.getPassword(), confirmPassword) == false) {
			return new ErrorResult("Passwords do not match!");
		}
		
		employerService.add(employer);
		String activationCode = activationService.sendCode();
		String activationResponse = saveActivationCodeToDb(activationCode, userService.getByEmail(employer.getEmail()).getData().getId(), employer.getEmail());
		return new SuccessResult("Registration has been successfully completed! " + activationResponse);
	}

	@Override
	public Result registerJobSeeker(JobSeeker jobSeeker, String confirmPassword) {
		if (checkIfJobSeekerInfoComplete(jobSeeker, confirmPassword) == false) {
			return new ErrorResult("You have entered missing information. Please fill in all fields!");
		}
		
		if (checkIfNationalIdExists(jobSeeker.getNationalId()) == true) {
			return new ErrorResult(jobSeeker.getNationalId() + " already exists!");
		}
		
		if (checkIfEmailExists(jobSeeker.getEmail())) {

			return new ErrorResult(jobSeeker.getEmail() + " already exists!");
		}
		
		if (checkIfRealPerson(Long.parseLong(jobSeeker.getNationalId()), jobSeeker.getFirstName(),
				jobSeeker.getLastName(), jobSeeker.getDateOfBirth().getYear()) == false) {
			return new ErrorResult("National Id could not be verified!");
		}		
		
		jobSeekerService.add(jobSeeker);
		String activationCode = activationService.sendCode();
		String activationResponse = saveActivationCodeToDb(activationCode, userService.getByEmail(jobSeeker.getEmail()).getData().getId(), jobSeeker.getEmail());
		return new SuccessResult("Registration has been successfully completed! " + activationResponse);
		
	}
	
	//VALIDATION FOR EMPLOYER
	private boolean checkIfEmployerInfoComplete(Employer employer) {

		if (employer.getCompanyName().isEmpty() ||
				employer.getWebAddress().isEmpty() ||
				employer.getEmail().isEmpty() ||
				employer.getPhoneNumber().isEmpty() ||
				employer.getPassword().isEmpty()) 
		{
			return false;
		}
		return true;
	}
	
	private boolean checkIfEmailMatchesDomain(String email, String webAddress) {
		String[] emailArray = email.split("@", 2);
		String domain = webAddress.substring(4, webAddress.length());
		if (emailArray[1].equals(domain)) {
			return true;
		}
		return false;
	}
	
	//VALIDATION FOR JOB SEEKER
	private boolean checkIfJobSeekerInfoComplete(JobSeeker jobSeeker, String confirmPassword) {	
		DateFormat dateFormat = new SimpleDateFormat("yyyy-mm-dd hh:mm:ss"); 

		if (jobSeeker.getFirstName().isEmpty() || jobSeeker.getLastName().isEmpty() || 
			jobSeeker.getNationalId().isEmpty() || dateFormat.format(jobSeeker.getDateOfBirth()).isEmpty() || 
			jobSeeker.getPassword().isEmpty() || jobSeeker.getEmail().isEmpty() ||
			confirmPassword.isEmpty() )
		{
			return false;
		}
		return true;
	}
	
	private boolean checkIfNationalIdExists(String nationalId) {
		if (jobSeekerService.getByNationalId(nationalId).getData() != null) {
			return true;
		}
		return false;
	}
	
	private boolean checkIfRealPerson(long nationalId, String firstName, String lastName, int birthYear) {

		if (validationService.validateByMernis(nationalId, firstName, lastName, birthYear)) {
			return true;
		}
		return false;
	}
	
	//COMMON VALIDATION
	private boolean checkIfEmailExists(String email) {
		if (this.userService.getByEmail(email).getData() != null) {
			return true;
		}
		return false;
	}

	private boolean checkIfPasswordAndConfirmPasswordMatches(String password, String confirmPassword) {
		if (password.equals(confirmPassword)) {
			return true;
		}
		return false;
	}
	
	private String saveActivationCodeToDb(String code, int userId, String email) {		
		ActivationCode activationCode = new ActivationCode(userId, code, false, LocalDate.now());
		activationCodeService.add(activationCode);
		return ("Activation code has been sent to " + email );	
	}
}