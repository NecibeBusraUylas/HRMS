package kodlama.io.hrms.dataAccess.abstracts;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import kodlama.io.hrms.entities.concretes.JobAdvertisement;

public interface JobAdvertisementDao extends JpaRepository<JobAdvertisement, Integer>{
	 List<JobAdvertisement> findByIsActiveTrue();

	 List<JobAdvertisement> findByIsActiveTrueOrderByCreateDate();

	 List<JobAdvertisement> findByIsActiveTrueAndEmployer_Id(int employerId);

	 JobAdvertisement findByIdAndEmployer_Id(int jobadvertisementId, int employerId);
}