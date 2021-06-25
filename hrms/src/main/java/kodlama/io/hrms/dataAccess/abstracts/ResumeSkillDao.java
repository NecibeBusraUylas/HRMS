package kodlama.io.hrms.dataAccess.abstracts;

import org.springframework.data.jpa.repository.JpaRepository;

import kodlama.io.hrms.entities.concretes.ResumeSkill;

public interface ResumeSkillDao extends JpaRepository<ResumeSkill, Integer>{

}
