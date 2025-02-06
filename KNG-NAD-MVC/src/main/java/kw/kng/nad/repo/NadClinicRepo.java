package kw.kng.nad.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import kw.kng.nad.entities.NadClinics;

public interface NadClinicRepo extends JpaRepository<NadClinics, Long>
{
	
	List<NadClinics> findAllByOrderByIdDesc();
}
