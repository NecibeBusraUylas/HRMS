package kodlama.io.hrms.dataAccess.abstracts;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import kodlama.io.hrms.entities.concretes.SchoolDepartment;
import kodlama.io.hrms.entities.dtos.SchoolDepartmentDetailsDto;

public interface SchoolDepartmentDao extends JpaRepository<SchoolDepartment, Integer>{
	
	@Query("Select new kodlama.io.hrms.entities.dtos.SchoolDepartmentDetailsDto(sd.department, es.startDate, es.graduationDate, sd.school.name)"
	        + "From SchoolDepartment sd JOIN sd.employeeSchoolDepartments es Where es.employee.id=:userId Order By es.graduationDate DESC")
	List<SchoolDepartmentDetailsDto> findByEmployeeSchoolDepartments_Employee_UserId(int userId);
}