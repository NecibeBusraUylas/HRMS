package kodlama.io.hrms.dataAccess.abstracts;

import org.springframework.data.jpa.repository.JpaRepository;

import kodlama.io.hrms.entities.concretes.ResumeForeignLanguage;

public interface ResumeForeignLanguageDao extends JpaRepository<ResumeForeignLanguage, Integer>{

}