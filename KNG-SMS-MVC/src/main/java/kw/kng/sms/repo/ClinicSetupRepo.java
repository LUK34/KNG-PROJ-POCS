package kw.kng.sms.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import kw.kng.sms.entities.ClinicSetup;

public interface ClinicSetupRepo extends JpaRepository<ClinicSetup, Long>
{
	
	List<ClinicSetup> findAllByOrderByIdDesc();
}
