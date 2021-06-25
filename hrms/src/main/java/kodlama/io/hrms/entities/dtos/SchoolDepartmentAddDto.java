package kodlama.io.hrms.entities.dtos;

import java.util.Date;

import javax.validation.constraints.NotNull;

import kodlama.io.hrms.entities.concretes.Employee;
import kodlama.io.hrms.entities.concretes.SchoolDepartment;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SchoolDepartmentAddDto {

	@NotNull
    private SchoolDepartment schoolDepartment;
	
	@NotNull
    private Employee employee;
	
	@NotNull
    private Date startDate;
	
	@NotNull
    private Date graduationDate;
}