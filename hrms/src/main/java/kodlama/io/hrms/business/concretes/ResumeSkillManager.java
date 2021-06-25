package kodlama.io.hrms.business.concretes;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kodlama.io.hrms.business.abstracts.ResumeSkillService;
import kodlama.io.hrms.core.utilities.business.BusinessRules;
import kodlama.io.hrms.core.utilities.results.ErrorResult;
import kodlama.io.hrms.core.utilities.results.Result;
import kodlama.io.hrms.core.utilities.results.SuccessResult;
import kodlama.io.hrms.dataAccess.abstracts.ResumeSkillDao;
import kodlama.io.hrms.entities.concretes.Resume;
import kodlama.io.hrms.entities.concretes.ResumeSkill;
import kodlama.io.hrms.entities.dtos.SkillAddDto;

@Service
public class ResumeSkillManager implements ResumeSkillService {
	private ResumeSkillDao resumeSkillDao;

    @Autowired
    public ResumeSkillManager(ResumeSkillDao resumeSkillDao) {
        this.resumeSkillDao = resumeSkillDao;
    }

	@Override
	public Result add(SkillAddDto skill, Resume resume) {
		Result businessRules = BusinessRules.run( checkIfSkillExistsInDatabase(skill.getSkill().getId()));
		if (businessRules != null)
			return businessRules;
		ResumeSkill resumeSkill = new ResumeSkill(resume, skill.getSkill(), skill.getLevel());
		this.resumeSkillDao.save(resumeSkill);
		return new SuccessResult();
	}

	@Override
	public Result addRange(List<SkillAddDto> skills, Resume resume) {
		for (SkillAddDto skill : skills) {
			Result businessRules = BusinessRules.run( checkIfSkillExistsInDatabase(skill.getSkill().getId()));
			if (businessRules != null)
				return businessRules;
			ResumeSkill resumeSkill = new ResumeSkill(resume, skill.getSkill(), skill.getLevel());
			this.resumeSkillDao.save(resumeSkill);
        }
		return new SuccessResult();
    }
	
	private Result checkIfSkillExistsInDatabase(int skillId) {
		if (resumeSkillDao.findById(skillId) == null) {
            return new ErrorResult();
        }
        return new SuccessResult();
    }
}