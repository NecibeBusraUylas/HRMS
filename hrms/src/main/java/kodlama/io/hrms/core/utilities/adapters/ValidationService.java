package kodlama.io.hrms.core.utilities.adapters;

import kodlama.io.hrms.core.utilities.results.Result;

public interface ValidationService {
	Result validate(String nationalId,String firstName, String lastName, int yearOfBirth);
}