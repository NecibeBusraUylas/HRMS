package kodlama.io.hrms.entities.dtos;

import java.sql.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class JobAdvertisementAddDto {	
	private int cityId;
	private int jobPositionId;
	private int employerId;
	private String jobDescription;
    private Double minSalary;
    private Double maxSalary;
    private int openPositionCount;
    private Date lastApplicationDate;
    private boolean isActive;
}