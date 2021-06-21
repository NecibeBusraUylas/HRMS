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
@Table(name="employees")
@AllArgsConstructor
@NoArgsConstructor
public class Employee{

	@Id
	@Column(name = "id", nullable = false,updatable = false)
    private int id;
	
	@Column(name="first_Name", nullable = false)
	private String firstName;
	
	@Column(name="last_Name", nullable = false)
	private String lastName;
	
	@Column(name="national_id", nullable = false, unique = true)
	private String nationalId;
	
	@Column(name="date_of_birth", nullable = false)
	private Date dateOfbirth;

	@OneToOne(fetch = FetchType.LAZY, cascade = {CascadeType.PERSIST, CascadeType.MERGE})
	@JoinColumn(name="id")
	private User user;
	
	@OneToMany(mappedBy = "employee",cascade = {CascadeType.PERSIST, CascadeType.MERGE})
	@JsonIgnore
	Set<EmployeeSchoolDepartment> employeeSchoolDepartments;
	
	@OneToMany(mappedBy = "employee",cascade = {CascadeType.PERSIST, CascadeType.MERGE})
	@JsonIgnore
	Set<Resume> resumes;
	
	public Employee(int id,String firstName, String lastName, String nationalId, Date date) {
		this.id = id;
		this.firstName = firstName;
		this.lastName = lastName;
		this.nationalId = nationalId;
		this.dateOfbirth = date;
	}
}