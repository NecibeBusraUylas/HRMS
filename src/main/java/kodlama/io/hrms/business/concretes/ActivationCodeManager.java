package kodlama.io.hrms.business.concretes;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kodlama.io.hrms.business.abstracts.ActivationCodeService;
import kodlama.io.hrms.core.utilities.results.DataResult;
import kodlama.io.hrms.core.utilities.results.Result;
import kodlama.io.hrms.core.utilities.results.SuccessDataResult;
import kodlama.io.hrms.core.utilities.results.SuccessResult;
import kodlama.io.hrms.dataAccess.abstracts.ActivationCodeDao;
import kodlama.io.hrms.entities.concretes.ActivationCode;

@Service
public class ActivationCodeManager implements ActivationCodeService{

	private ActivationCodeDao activationCodeDao;
	
	@Autowired
	public ActivationCodeManager(ActivationCodeDao activationCodeDao) {
		super();
		this.activationCodeDao = activationCodeDao;
	}

	@Override
	public DataResult<List<ActivationCode>> getall() {
		var result =  this.activationCodeDao.findAll();
		return new SuccessDataResult<List<ActivationCode>>(result);
	}

	@Override
	public Result add(ActivationCode activationcode) {
		this.activationCodeDao.save(activationcode);
		return new SuccessResult("Activation code has been saved.");
	}
}