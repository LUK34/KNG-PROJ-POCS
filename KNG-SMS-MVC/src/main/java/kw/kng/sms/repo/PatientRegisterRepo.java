package kw.kng.sms.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import kw.kng.sms.entities.PatRegister;

public interface PatientRegisterRepo extends JpaRepository<PatRegister, Long> 
{
	List<PatRegister> findAllByOrderByIdDesc();

}
