package kodlama.io.hrms.api.controllers;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import kodlama.io.hrms.business.abstracts.JobSeekerService;
import kodlama.io.hrms.core.utilities.results.DataResult;
import kodlama.io.hrms.core.utilities.results.Result;
import kodlama.io.hrms.entities.concretes.JobSeeker;

@RestController
@RequestMapping("/api/jobseekers")
public class JobSeekersController {

	private JobSeekerService jobSeekerService;

	public JobSeekersController(JobSeekerService jobSeekerService) {
		super();
		this.jobSeekerService = jobSeekerService;
	}
	
	@GetMapping("/getall")
	public DataResult<List<JobSeeker>> getAll(){
		return this.jobSeekerService.getAll();
	}
	
	@GetMapping("/getbyid")
	public DataResult<JobSeeker> getById(int id){
		return this.jobSeekerService.getById(id);
	}
	
	@PostMapping("/add")
	public Result add(@RequestBody JobSeeker jobSeeker){
		return this.jobSeekerService.add(jobSeeker);
	}
	
	@PostMapping("/update")
	public Result update(@RequestBody JobSeeker jobSeeker){
		return this.jobSeekerService.update(jobSeeker);
	}
	
	@PostMapping("/delete")
	public Result delete(@RequestBody JobSeeker jobSeeker){
		return this.jobSeekerService.delete(jobSeeker);
	}
}