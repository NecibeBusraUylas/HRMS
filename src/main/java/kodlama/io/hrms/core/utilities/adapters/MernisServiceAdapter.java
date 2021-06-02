package kodlama.io.hrms.core.utilities.adapters;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kodlama.io.hrms.mernisService.FakeMernisService;

@Service
public class MernisServiceAdapter implements ValidationService{

	@Autowired
	public MernisServiceAdapter() {
	}
	
	@Override
	public boolean validateByMernis(long nationalId, String firstName, String lastName, int yearOfBirth) {
		FakeMernisService client=new FakeMernisService();
		boolean result=true;
		
		try {		
			result=client.ValidateByUserInfo(nationalId, firstName, lastName, yearOfBirth);
		}catch(Exception e) {
			e.printStackTrace();
		}
		return result;
	}
}