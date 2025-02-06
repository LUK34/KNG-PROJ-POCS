package kw.kng.sms.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import kw.kng.sms.entities.DoctorConsult;

public interface DoctorConsultRepo extends JpaRepository<DoctorConsult, Long> 
{
	List<DoctorConsult> findAllByOrderByIdDesc();

}
