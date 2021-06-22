package kodlama.io.hrms.entities.concretes;

import java.util.List;
import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@Table(name = "employers")
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler", "jobAdvertisements"})
public class Employer {
    @Id
    @Column(name = "user_id")
    private int id;

    @Column(name = "company_name")
    private String companyName;

    @Column(name = "phone_number")
    private String phoneNumber;

    @Column(name = "verified_by_system")
    private boolean verifiedBySystem;

    @Column(name = "web_address")
    private String webAddress;

    @OneToMany(mappedBy = "employer", fetch = FetchType.LAZY)
    private List<JobAdvertisement> jobAdvertisements;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    public Employer(int id, String companyName, String phone_number, boolean verifiedBySystem, String webAddress) {
        this.id = id;
        this.companyName = companyName;
        this.phoneNumber = phone_number;
        this.verifiedBySystem = verifiedBySystem;
        this.webAddress = webAddress;
    }
}