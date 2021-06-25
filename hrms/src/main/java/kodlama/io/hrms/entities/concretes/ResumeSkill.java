package kodlama.io.hrms.entities.concretes;

import javax.persistence.*;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@Table(name = "resume_skills")
@AllArgsConstructor
@NoArgsConstructor
public class ResumeSkill {
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id",nullable = false,updatable = false)
	private int id;

	@Column(name = "level",nullable = false)
	private int level;
	    
	@ManyToOne
	@JoinColumn(name="resume_id")
	private Resume resume;
	
	@ManyToOne
	@JoinColumn(name="skill_id")
	private Skill skill;

	public ResumeSkill(Resume resume, Skill skill,int level) {
		this.level = level;
		this.resume = resume;
		this.skill = skill;
	}
}