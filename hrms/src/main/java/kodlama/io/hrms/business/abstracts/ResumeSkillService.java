package kodlama.io.hrms.business.abstracts;

import java.util.List;

import kodlama.io.hrms.core.utilities.results.Result;
import kodlama.io.hrms.entities.concretes.Resume;
import kodlama.io.hrms.entities.dtos.SkillAddDto;

public interface ResumeSkillService {
	Result add(SkillAddDto skill, Resume resume);

	Result addRange(List<SkillAddDto> skills, Resume resume);

}