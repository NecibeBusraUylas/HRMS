package kodlama.io.hrms.api.controllers;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import kodlama.io.hrms.business.abstracts.ActivationCodeService;
import kodlama.io.hrms.core.utilities.results.DataResult;
import kodlama.io.hrms.entities.concretes.ActivationCode;

@RestController
@RequestMapping("/api/activationCodes")
public class ActivationCodesController {

	private ActivationCodeService activationCodeService;

	public ActivationCodesController(ActivationCodeService activationCodeService) {
		super();
		this.activationCodeService = activationCodeService;
	}
	
	@GetMapping("/getall")
	public DataResult<List<ActivationCode>> getAll(){	
		return this.activationCodeService.getall();
	}
}