package kodlama.io.hrms.core.utilities.activations;

public interface ActivationService {
	void sendLink(String email);
	String sendCode();
}