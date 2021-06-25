package kodlama.io.hrms.business.abstracts;

import java.util.List;

import kodlama.io.hrms.core.utilities.results.DataResult;
import kodlama.io.hrms.entities.concretes.ForeignLanguage;
import kodlama.io.hrms.entities.dtos.ForeignLanguageDetailsDto;

public interface ForeignLanguageService {
	DataResult<List<ForeignLanguageDetailsDto>> findByResume_Id(int id);

    DataResult<ForeignLanguage> findById(int id);
}