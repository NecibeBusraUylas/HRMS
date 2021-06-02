package kodlama.io.hrms.core.utilities.activations;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ActivationManager implements ActivationService{

	@Autowired
	public ActivationManager() {
		
	}
	
	@Override
	public void sendLink(String email) {
		UUID uuid=UUID.randomUUID();
		String activationLink="https://hmrsactivationmail/" + uuid.toString();
		System.out.println("Activation link has been send to " + email);
		System.out.println("Please click on the link to verify your account:  " + activationLink);
	}
	
	@Override
	public String sendCode() {
		UUID uuid=UUID.randomUUID();
		String activationCode= uuid.toString();
		System.out.println("Your activation code:  " + activationCode);
		return activationCode;
	}
}