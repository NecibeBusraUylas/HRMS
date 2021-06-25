package kodlama.io.hrms.business.abstracts;

import java.util.List;

import kodlama.io.hrms.core.utilities.results.DataResult;
import kodlama.io.hrms.core.utilities.results.Result;
import kodlama.io.hrms.entities.concretes.Resume;
import kodlama.io.hrms.entities.dtos.ResumeAddDto;
import kodlama.io.hrms.entities.dtos.ResumeWithDetailsDto;

public interface ResumeService {
	DataResult<ResumeWithDetailsDto> getResumeWithDetails(int id);

	DataResult<List<Resume>> getAll();
	
	Result add(ResumeAddDto resume);
}
