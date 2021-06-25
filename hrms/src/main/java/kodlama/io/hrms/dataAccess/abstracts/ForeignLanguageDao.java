package kodlama.io.hrms.dataAccess.abstracts;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import kodlama.io.hrms.entities.concretes.ForeignLanguage;
import kodlama.io.hrms.entities.dtos.ForeignLanguageDetailsDto;

public interface ForeignLanguageDao extends JpaRepository<ForeignLanguage, Integer>{
	@Query("Select new kodlama.io.hrms.entities.dtos.ForeignLanguageDetailsDto(f.name, rf.level) From ForeignLanguage f Join f.resumeForeignLanguages rf Where rf.resume.id=:id")
	List<ForeignLanguageDetailsDto> findByResume_ResumeId(int id);

    ForeignLanguage findById(int id);
}