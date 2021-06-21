package kodlama.io.hrms.entities.concretes;

import javax.persistence.*;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@Table(name = "system_users")
@AllArgsConstructor
@NoArgsConstructor
public class SystemUser {
	
	@Id
	@Column(name = "user_id",nullable = false, updatable = false)
	private int userId;

	@Column(name = "roles",nullable = false)
	private String roles;
}