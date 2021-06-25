package kodlama.io.hrms.business.abstracts;

import java.util.List;

import kodlama.io.hrms.core.utilities.results.DataResult;
import kodlama.io.hrms.core.utilities.results.Result;
import kodlama.io.hrms.entities.concretes.JobExperience;
import kodlama.io.hrms.entities.concretes.Resume;
import kodlama.io.hrms.entities.dtos.JobExperienceAddDto;

public interface JobExperienceService {
	DataResult<List<JobExperience>> findByResume_Id(int id);

    Result add(JobExperienceAddDto jobExperience, Resume resume);

    Result addRange(List<JobExperienceAddDto> jobExperiences, Resume resume);
}