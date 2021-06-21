package kodlama.io.hrms.entities.concretes;

import javax.persistence.*;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@Table(name = "school_departments")
@AllArgsConstructor
@NoArgsConstructor
public class SchoolDepartment {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private int id;

	@Column(name = "department", nullable = false)
	private String department;
	
	@ManyToOne(fetch = FetchType.LAZY, cascade = {CascadeType.PERSIST, CascadeType.MERGE})
	@JoinColumn(name="school_id")
	@JsonIgnore
	private School school;
	
	@OneToMany(mappedBy = "schoolDepartment",cascade = {CascadeType.PERSIST, CascadeType.MERGE})
	@JsonIgnore
	Set<EmployeeSchoolDepartment> employeeSchoolDepartments;
}