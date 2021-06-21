package kodlama.io.hrms.core.utilities.email;

import kodlama.io.hrms.core.utilities.results.Result;

public interface EmailService {
	Result send(String to, String title, String message);
}