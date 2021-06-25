package kodlama.io.hrms.dataAccess.abstracts;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import kodlama.io.hrms.entities.concretes.Skill;
import kodlama.io.hrms.entities.dtos.SkillDetailsDto;

public interface SkillDao extends JpaRepository<Skill, Integer> {
    @Query("Select new kodlama.io.hrms.entities.dtos.SkillDetailsDto(s.name, rs.level) From Skill s Join s.resumeSkills rs Where rs.resume.id =: id")
    List<SkillDetailsDto> findByResume_ResumeId(int id);

    Skill findById(int id);
}