package kodlama.io.hrms.entities.dtos;

import java.util.List;

import javax.validation.Valid;
import org.springframework.web.multipart.MultipartFile;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ResumeAddDto {
	
	private String githubAddress;
	private String linkedinAddress;
    private String coverLetter;
	private MultipartFile picture;
	private int employeeId;

	@Valid
	private List<ForeignLanguageAddDto> foreignLanguages;

	@Valid
	private List<SkillAddDto> skills;

	@Valid
    private List<JobExperienceAddDto> jobExperiences;
}