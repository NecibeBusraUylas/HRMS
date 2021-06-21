package kodlama.io.hrms.entities.concretes;

import javax.persistence.*;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@Table(name = "skills")
@AllArgsConstructor
@NoArgsConstructor
public class Skill {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id",nullable = false, updatable = false)
	private int id;

	@Column(name = "name", nullable = false)
	private String name;
	
	@OneToMany(mappedBy = "skill",cascade = {CascadeType.PERSIST, CascadeType.MERGE})
	@JsonIgnore
	Set<ResumeSkill> resumeSkills;
}