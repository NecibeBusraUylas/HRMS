package kodlama.io.hrms.core.utilities.email;

import org.springframework.stereotype.Service;

import kodlama.io.hrms.core.utilities.results.Result;
import kodlama.io.hrms.core.utilities.results.SuccessResult;

@Service
public class EmailManager implements EmailService{

	@Override
	public Result send(String to, String title, String message) {
		return new SuccessResult("E-mail sent!");
	}

}