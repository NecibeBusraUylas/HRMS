package kodlama.io.hrms.dataAccess.abstracts;

import org.springframework.data.jpa.repository.JpaRepository;

import kodlama.io.hrms.entities.concretes.EmployeeSchoolDepartment;

public interface EmployeeSchoolDepartmentDao extends JpaRepository<EmployeeSchoolDepartment, Integer>{

}