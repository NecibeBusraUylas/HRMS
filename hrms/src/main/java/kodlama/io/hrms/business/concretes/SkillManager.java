package kodlama.io.hrms.business.concretes;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kodlama.io.hrms.business.abstracts.SkillService;
import kodlama.io.hrms.core.utilities.results.DataResult;
import kodlama.io.hrms.core.utilities.results.ErrorDataResult;
import kodlama.io.hrms.core.utilities.results.SuccessDataResult;
import kodlama.io.hrms.dataAccess.abstracts.SkillDao;
import kodlama.io.hrms.entities.concretes.Skill;
import kodlama.io.hrms.entities.dtos.SkillDetailsDto;

@Service
public class SkillManager implements SkillService {
    private SkillDao skillDao;

    @Autowired 
    public SkillManager(SkillDao skillDao) {
        this.skillDao = skillDao;
    }

    @Override
    public DataResult<List<SkillDetailsDto>> findByResume_Id(int id) {
        return new SuccessDataResult<List<SkillDetailsDto>>(this.skillDao.findByResume_ResumeId(id));
    }

    @Override
    public DataResult<Skill> findById(int id) {
        Skill skill = this.skillDao.findById(id);
        if (skill == null)
            return new ErrorDataResult<Skill>();
        return new SuccessDataResult<Skill>(skill);
    }
}