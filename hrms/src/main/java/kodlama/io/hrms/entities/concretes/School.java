package kodlama.io.hrms.entities.concretes;

import javax.persistence.*;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@Table(name = "schools")
@AllArgsConstructor
@NoArgsConstructor
public class School {
	
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false,  updatable = false)
    private int id;

    @Column(name = "name", nullable = false)
    private String name;
}