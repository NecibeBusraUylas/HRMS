package kodlama.io.hrms.business.abstracts;

import java.util.List;

import kodlama.io.hrms.core.utilities.results.DataResult;
import kodlama.io.hrms.core.utilities.results.Result;
import kodlama.io.hrms.entities.concretes.JobAdvertisement;
import kodlama.io.hrms.entities.dtos.JobAdvertisementAddDto;

public interface JobAdvertisementService {

	DataResult<List<JobAdvertisement>> getAll();
    DataResult<List<JobAdvertisement>> findByIsActiveTrue();
    DataResult<List<JobAdvertisement>> findByIsActiveTrueOrderByCreateDate();
    DataResult<List<JobAdvertisement>> finfByIsActiveTrueAndEmployer_Id(int employerId);
    Result changeStatus(int jobadvertisementId, int employerId);
    Result add(JobAdvertisementAddDto jobAdvertisementAddDto);

}