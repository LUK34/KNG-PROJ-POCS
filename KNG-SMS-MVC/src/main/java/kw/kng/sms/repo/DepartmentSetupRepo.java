package kw.kng.sms.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import kw.kng.sms.entities.DepartmentSetup;

public interface DepartmentSetupRepo extends JpaRepository<DepartmentSetup, Long>
{
	
	List<DepartmentSetup> findAllByOrderByIdDesc();
}
