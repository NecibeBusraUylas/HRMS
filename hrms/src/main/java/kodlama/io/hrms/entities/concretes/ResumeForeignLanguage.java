package kodlama.io.hrms.entities.concretes;

import javax.persistence.*;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@Table(name = "resume_foreign_languages")
@AllArgsConstructor
@NoArgsConstructor
public class ResumeForeignLanguage {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id",nullable = false,updatable = false)
    private int id;

    @Column(name = "level",nullable = false)
    private String level;

	@ManyToOne(fetch = FetchType.LAZY, cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinColumn(name="resume_id")
    private Resume resume;
    
    @ManyToOne(fetch = FetchType.LAZY, cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinColumn(name="language_id")
    private ForeignLanguage foreignLanguage;
    
    public ResumeForeignLanguage(String level, Resume resume, ForeignLanguage foreignLanguage) {
  		this.level = level;
  		this.resume = resume;
  		this.foreignLanguage = foreignLanguage;
  	}
}