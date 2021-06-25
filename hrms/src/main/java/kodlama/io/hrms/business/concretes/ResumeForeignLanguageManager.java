package kodlama.io.hrms.business.concretes;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kodlama.io.hrms.business.abstracts.ForeignLanguageService;
import kodlama.io.hrms.business.abstracts.ResumeForeignLanguageService;
import kodlama.io.hrms.core.utilities.business.BusinessRules;
import kodlama.io.hrms.core.utilities.results.ErrorResult;
import kodlama.io.hrms.core.utilities.results.Result;
import kodlama.io.hrms.core.utilities.results.SuccessResult;
import kodlama.io.hrms.dataAccess.abstracts.ResumeForeignLanguageDao;
import kodlama.io.hrms.entities.concretes.Resume;
import kodlama.io.hrms.entities.concretes.ResumeForeignLanguage;
import kodlama.io.hrms.entities.dtos.ForeignLanguageAddDto;

@Service
public class ResumeForeignLanguageManager implements ResumeForeignLanguageService{

	 private ResumeForeignLanguageDao foreignLanguageDao;
	 private ForeignLanguageService foreignLanguageService;

	 @Autowired
	 public ResumeForeignLanguageManager(ResumeForeignLanguageDao foreignLanguageDao, ForeignLanguageService foreignLanguageService) {
		 this.foreignLanguageDao = foreignLanguageDao;
	     this.foreignLanguageService = foreignLanguageService;
	 }
	    
	@Override
	public Result add(ForeignLanguageAddDto foreignLanguage, Resume resume) {
		Result businessRulesResult = BusinessRules.run(checkIfLanguageExistsInDatabase(foreignLanguage.getForeignLanguage().getId()));		
		if (businessRulesResult != null) {
			return businessRulesResult;
        }			
		ResumeForeignLanguage resumeForeignLanguage = new ResumeForeignLanguage(resume, foreignLanguage.getForeignLanguage(), foreignLanguage.getLevel());
        this.foreignLanguageDao.save(resumeForeignLanguage);
        return new SuccessResult();
    }

	@Override
	public Result addRange(List<ForeignLanguageAddDto> foreignLanguages, Resume resume) {
		for (ForeignLanguageAddDto foreignLanguage : foreignLanguages) {
			Result businessRulesResult = BusinessRules.run(checkIfLanguageExistsInDatabase(foreignLanguage.getForeignLanguage().getId()));		
			if (businessRulesResult != null) {
				return businessRulesResult;
	        }			
			ResumeForeignLanguage resumeForeignLanguage = new ResumeForeignLanguage(resume, foreignLanguage.getForeignLanguage(), foreignLanguage.getLevel());
	        this.foreignLanguageDao.save(resumeForeignLanguage);
	    }
		return new SuccessResult();
	}
	
	private Result checkIfLanguageExistsInDatabase(int id) {
		if (foreignLanguageService.findById(id) == null) {
			return new ErrorResult();
	    }
	    return new SuccessResult();
	}
}
