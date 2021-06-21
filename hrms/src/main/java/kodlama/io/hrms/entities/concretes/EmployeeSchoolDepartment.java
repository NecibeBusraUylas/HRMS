package kodlama.io.hrms.entities.concretes;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

@Data
@Entity
@Table(name = "employee_school_departments")
@AllArgsConstructor
@NoArgsConstructor
public class EmployeeSchoolDepartment {

	@Id
    @Column(name = "id",nullable = false, updatable = false)
    private int id;

    @Column(name = "start_date",nullable = false)
    private Date startDate;

    @Column(name = "graduation_date",nullable = false)
    private Date graduationDate;
    
    @ManyToOne(fetch = FetchType.LAZY, cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinColumn(name="employee_id")
    private Employee employee;
    
    @ManyToOne(fetch = FetchType.LAZY, cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinColumn(name="department_id")
    private SchoolDepartment schoolDepartment;

	public EmployeeSchoolDepartment(Date startDate, Date graduationDate, Employee employee,
			SchoolDepartment schoolDepartment) {
		this.startDate = startDate;
		this.graduationDate = graduationDate;
		this.employee = employee;
		this.schoolDepartment = schoolDepartment;
	}
}