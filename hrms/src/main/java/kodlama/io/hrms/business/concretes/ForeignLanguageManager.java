package kodlama.io.hrms.business.concretes;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kodlama.io.hrms.business.abstracts.ForeignLanguageService;
import kodlama.io.hrms.core.utilities.results.DataResult;
import kodlama.io.hrms.core.utilities.results.SuccessDataResult;
import kodlama.io.hrms.dataAccess.abstracts.ForeignLanguageDao;
import kodlama.io.hrms.entities.concretes.ForeignLanguage;
import kodlama.io.hrms.entities.dtos.ForeignLanguageDetailsDto;

@Service
public class ForeignLanguageManager implements ForeignLanguageService{

	private ForeignLanguageDao foreignLanguageDao;

    @Autowired
    public ForeignLanguageManager(ForeignLanguageDao foreignLanguageDao) {
        this.foreignLanguageDao = foreignLanguageDao;
    }
    
	@Override
	public DataResult<List<ForeignLanguageDetailsDto>> findByResume_Id(int id) {
		 return new SuccessDataResult<List<ForeignLanguageDetailsDto>>(this.foreignLanguageDao.findByResume_ResumeId(id));
	}

	@Override
	public DataResult<ForeignLanguage> findById(int id) {
		return new SuccessDataResult<ForeignLanguage>(this.foreignLanguageDao.findById(id));
	}
}