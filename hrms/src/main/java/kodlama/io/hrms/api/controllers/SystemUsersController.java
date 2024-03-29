package kodlama.io.hrms.api.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import kodlama.io.hrms.business.abstracts.SystemUserService;
import kodlama.io.hrms.core.utilities.results.DataResult;
import kodlama.io.hrms.entities.concretes.SystemUser;

@RestController
@RequestMapping("/api/systemusers")
public class SystemUsersController {
	
	private SystemUserService systemUserService;
	 
    @Autowired
    public SystemUsersController(SystemUserService systemUserService) {
    	this.systemUserService = systemUserService;
	}

    @GetMapping("/getall")
	public DataResult<List<SystemUser>> getAll() {
		return this.systemUserService.getAll();
	}
}