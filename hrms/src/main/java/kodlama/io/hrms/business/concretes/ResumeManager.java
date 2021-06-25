package kodlama.io.hrms.business.concretes;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kodlama.io.hrms.business.abstracts.EmployeeService;
import kodlama.io.hrms.business.abstracts.ForeignLanguageService;
import kodlama.io.hrms.business.abstracts.JobExperienceService;
import kodlama.io.hrms.business.abstracts.ResumeForeignLanguageService;
import kodlama.io.hrms.business.abstracts.ResumeService;
import kodlama.io.hrms.business.abstracts.ResumeSkillService;
import kodlama.io.hrms.business.abstracts.SchoolDepartmentService;
import kodlama.io.hrms.business.abstracts.SkillService;
import kodlama.io.hrms.core.utilities.adapters.CloudinaryService;
import kodlama.io.hrms.core.utilities.business.BusinessRules;
import kodlama.io.hrms.core.utilities.results.DataResult;
import kodlama.io.hrms.core.utilities.results.ErrorDataResult;
import kodlama.io.hrms.core.utilities.results.Result;
import kodlama.io.hrms.core.utilities.results.SuccessDataResult;
import kodlama.io.hrms.core.utilities.results.SuccessResult;
import kodlama.io.hrms.dataAccess.abstracts.ResumeDao;
import kodlama.io.hrms.entities.concretes.Employee;
import kodlama.io.hrms.entities.concretes.Resume;
import kodlama.io.hrms.entities.dtos.ResumeAddDto;
import kodlama.io.hrms.entities.dtos.ResumeWithDetailsDto;

@Service
public class ResumeManager implements ResumeService {
    private ResumeDao resumeDao;
    private SchoolDepartmentService schoolDepartmentService;
    private JobExperienceService jobExperienceService;
    private ForeignLanguageService foreignLanguageService;
    private SkillService skillService;
    private ResumeForeignLanguageService resumeForeignLanguageService;
    private EmployeeService employeeService;
    private ResumeSkillService resumeSkillService;
    private CloudinaryService fileService;

    @Autowired
    public ResumeManager(ResumeDao resumeDao, SchoolDepartmentService schoolDepartmentService, JobExperienceService jobExperienceService, ForeignLanguageService foreignLanguageService, SkillService skillService, ResumeForeignLanguageService resumeForeignLanguageService, EmployeeService employeeService, ResumeSkillService resumeSkillService, CloudinaryService fileService) {
        this.resumeDao = resumeDao;
        this.schoolDepartmentService = schoolDepartmentService;
        this.jobExperienceService = jobExperienceService;
        this.foreignLanguageService = foreignLanguageService;
        this.skillService = skillService;
        this.resumeForeignLanguageService = resumeForeignLanguageService;
        this.employeeService = employeeService;
        this.resumeSkillService = resumeSkillService;
        this.fileService = fileService;
    }

    @Override
    public DataResult<ResumeWithDetailsDto> getResumeWithDetails(int id) {
        ResumeWithDetailsDto resume = this.resumeDao.getResumeWithDetailsById(id);

        if (resume == null)
            return new ErrorDataResult<ResumeWithDetailsDto>();

        resume.setSchoolDepartments(this.schoolDepartmentService.findByEmployeeSchoolDepartments_Employee_UserId(resume.getEmployee().getId()).getData());

        resume.setJobExperiences(this.jobExperienceService.findByResume_Id(resume.getId()).getData());

        resume.setForeignLanguages(this.foreignLanguageService.findByResume_Id(id).getData());

        resume.setSkills(this.skillService.findByResume_Id(resume.getId()).getData());

        return new SuccessDataResult<ResumeWithDetailsDto>(resume);
    }

    @Override
    public DataResult<List<Resume>> getAll() {
        return new SuccessDataResult<List<Resume>>(this.resumeDao.findAll());
    }

    @Override
    public Result add(ResumeAddDto resume) {
        Result businessRulesResult = BusinessRules.run();

        if (businessRulesResult != null)
            return businessRulesResult;

        Employee employee = this.employeeService.getById(resume.getEmployeeId()).getData();

        Resume resumeToAdd = new Resume(
                resume.getGithubAddress(),
                resume.getLinkedinAddress(),
                resume.getCoverLetter(),
                this.fileService.upload(resume.getPicture()),
                employee
        );

        this.resumeDao.save(resumeToAdd);

        this.resumeForeignLanguageService.addRange(resume.getForeignLanguages(), resumeToAdd);
        this.jobExperienceService.addRange(resume.getJobExperiences(), resumeToAdd);
        this.resumeSkillService.addRange(resume.getSkills(), resumeToAdd);

        return new SuccessResult("CV added to the system!");
    }
}