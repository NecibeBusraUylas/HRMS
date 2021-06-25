package kodlama.io.hrms.business.abstracts;

import java.util.List;

import kodlama.io.hrms.core.utilities.results.Result;
import kodlama.io.hrms.entities.concretes.Resume;
import kodlama.io.hrms.entities.dtos.ForeignLanguageAddDto;

public interface ResumeForeignLanguageService {
	Result add(ForeignLanguageAddDto foreignLanguage, Resume resume);

	Result addRange(List<ForeignLanguageAddDto> foreignLanguages, Resume resume);
}