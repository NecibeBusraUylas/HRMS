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
    @Column(name = "id")
    private int id;

    @Column(name = "level")
    private int level;

    @ManyToOne
    @JoinColumn(name = "resume_id")
    private Resume resume;

    @ManyToOne
    @JoinColumn(name = "foreign_language_id")
    private ForeignLanguage foreignLanguage;

    public ResumeForeignLanguage(Resume resume, ForeignLanguage foreignLanguage, int level) {
        this.resume = resume;
        this.foreignLanguage = foreignLanguage;
        this.level = level;
    }
}