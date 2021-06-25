package kodlama.io.hrms.entities.dtos;

import java.sql.Date;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class JobAdvertisementAddDto {
	
	@NotNull
	private int cityId;
	
	@NotNull
	private int jobPositionId;
	
	@NotNull
	private int employerId;
	
	@NotBlank
	@NotNull
	private String jobDescription;
	
	@NotNull
    private Double minSalary;
	
	@NotNull
    private Double maxSalary;
	
	@NotNull
    private int openPositionCount;
	
	@NotNull
    private Date lastApplicationDate;
	
	@NotNull
    private boolean isActive;
}