package kodlama.io.hrms.entities.dtos;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import kodlama.io.hrms.entities.concretes.Skill;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SkillAddDto {
	
	@NotNull
    private Skill skill;

    @NotNull
    @Min(1)
    @Max(5)
    private int level;
}