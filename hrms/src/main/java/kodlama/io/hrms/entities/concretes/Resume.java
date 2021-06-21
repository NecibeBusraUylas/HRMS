package kodlama.io.hrms.entities.concretes;

import java.util.Date;
import java.util.Set;
import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@Table(name = "resumes")
@AllArgsConstructor
@NoArgsConstructor
public class Resume {

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id",nullable = false, updatable = false)
    private int id;

    @Column(name = "github_address", nullable = true)
    private String githubAddress;

    @Column(name = "linkedin_address", nullable = true)
    private String linkedinAddress;

    @Column(name = "cover_letter", nullable = true)
    private String coverLetter;

    @Column(name = "picture", nullable = true)
    private String picture;

    @Column(name = "create_date", nullable = false)
    private Date createDate;
    
    @ManyToOne
    @JoinColumn(name="employee_id")
    @JsonIgnore
    private Employee employee;
    
    @OneToMany(mappedBy = "resume",cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JsonIgnore
    private Set<JobExperience> jobExperiences;
    
    @OneToMany(mappedBy = "resume",cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JsonIgnore
    Set<ResumeForeignLanguage> resumeForeignLanguages;
    
    @OneToMany(mappedBy = "resume",cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JsonIgnore
    Set<ResumeSkill> resumeSkills;

	public Resume(String githubAddress, String linkedinAddress, String coverLetter, String picture, Date createDate,
			Employee employee) {
		this.githubAddress = githubAddress;
		this.linkedinAddress = linkedinAddress;
		this.coverLetter = coverLetter;
		this.picture = picture;
		this.createDate = new Date();
		this.employee = employee;
	}
}