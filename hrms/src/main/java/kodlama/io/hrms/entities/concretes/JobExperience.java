package kodlama.io.hrms.entities.concretes;

import javax.persistence.*;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@Table(name = "job_experience")
@AllArgsConstructor
@NoArgsConstructor
public class JobExperience {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", nullable = false,updatable = false)
	private int id;

	@Column(name = "office_name", nullable = false)
	private String officeName;

	@Column(name = "position", nullable = false)
	private String position;

	@Column(name = "start_date", nullable = false)
	private Date startDate;

	@Column(name = "end_date", nullable = true)
    private Date endDate;
	  
	@ManyToOne(fetch = FetchType.LAZY, cascade = {CascadeType.PERSIST, CascadeType.MERGE})
	@JoinColumn(name="resume_id")
	@JsonIgnore
    private Resume resume;

	public JobExperience(String officeName, String position, Date startDate, Date endDate, Resume resume) {
		super();
		this.officeName = officeName;
		this.position = position;
		this.startDate = startDate;
		this.endDate = endDate;
		this.resume = resume;
	}
}