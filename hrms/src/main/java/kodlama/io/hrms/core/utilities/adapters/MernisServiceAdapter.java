package kodlama.io.hrms.core.utilities.adapters;

import org.springframework.stereotype.Service;

import kodlama.io.hrms.core.utilities.results.Result;
import kodlama.io.hrms.core.utilities.results.SuccessResult;

@Service
public class MernisServiceAdapter implements ValidationService{
	
	@Override
	public Result validate(String nationalId, String firstName, String lastName, int yearOfBirth) {
		return new SuccessResult("Validated succesfully!");
	}
}