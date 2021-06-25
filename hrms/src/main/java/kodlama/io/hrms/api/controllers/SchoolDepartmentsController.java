package kodlama.io.hrms.api.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import kodlama.io.hrms.business.abstracts.SchoolDepartmentService;
import kodlama.io.hrms.core.utilities.results.DataResult;
import kodlama.io.hrms.core.utilities.results.Result;
import kodlama.io.hrms.entities.dtos.SchoolDepartmentAddDto;
import kodlama.io.hrms.entities.dtos.SchoolDepartmentDetailsDto;

import java.util.List;

@RestController
@RequestMapping("/api/schooldepartments")
public class SchoolDepartmentsController {
    private SchoolDepartmentService schoolDepartmentService;

    @Autowired
    public SchoolDepartmentsController(SchoolDepartmentService schoolDepartmentService) {
        this.schoolDepartmentService = schoolDepartmentService;
    }

    @GetMapping("/getbyuserid")
    public DataResult<List<SchoolDepartmentDetailsDto>> getByUserId(int id) {
        return this.schoolDepartmentService.findByEmployeeSchoolDepartments_Employee_UserId(id);
    }

    @PostMapping("/assigntouser")
    public Result assignToUser(@RequestBody SchoolDepartmentAddDto schoolDepartment) {
        return this.schoolDepartmentService.assignToEmployee(schoolDepartment);
    }
}