package kodlama.io.hrms.api.controllers;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import kodlama.io.hrms.business.abstracts.JobAdvertisementService;
import kodlama.io.hrms.core.utilities.results.DataResult;
import kodlama.io.hrms.core.utilities.results.Result;
import kodlama.io.hrms.entities.concretes.JobAdvertisement;
import kodlama.io.hrms.entities.dtos.JobAdvertisementAddDto;

@RestController
@RequestMapping("/api/jobadvertisements")
public class JobAdvertisementController {
    private JobAdvertisementService jobAdvertisementService;

    @Autowired
    public JobAdvertisementController(JobAdvertisementService jobAdvertisementService) {
        this.jobAdvertisementService = jobAdvertisementService;
    }

    @GetMapping("/getall")
    DataResult<List<JobAdvertisement>> getAll() {
        return this.jobAdvertisementService.getAll();
    }

    @GetMapping("/getallactive")
    public DataResult<List<JobAdvertisement>> getAllActive() {
        return this.jobAdvertisementService.findByIsActiveTrue();
    }

    @GetMapping("/getallactiveorderbydate")
    public DataResult<List<JobAdvertisement>> getAllActiveOrderDate() {
        return this.jobAdvertisementService.findByIsActiveTrueOrderByCreateDate();
    }

    @GetMapping("/getallactivebyemployer")
    public DataResult<List<JobAdvertisement>> getAllActiveByEmployer(int employerId) {
        return this.jobAdvertisementService.finfByIsActiveTrueAndEmployer_Id(employerId);
    }

    @PutMapping("/changestatus")
    public Result changeStatus(int jobadvertisementId, int employerId) {
        return this.jobAdvertisementService.changeStatus(jobadvertisementId, employerId);
    }

    @PostMapping("/add")
    public Result addNew(@RequestBody JobAdvertisementAddDto jobAdvertisementAddDto) {
        return this.jobAdvertisementService.add(jobAdvertisementAddDto);
    }
}