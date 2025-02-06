package kw.kng.nad.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import kw.kng.nad.entities.NadMPTemplateDays;

public interface NadMPTemplateDaysRepo extends JpaRepository<NadMPTemplateDays, Long> 
{

	List<NadMPTemplateDays> findAllByOrderByIdDesc();
	
	@Query("from NadMPTemplateDays u where u.nadMPTemplate.id = :pid")
	List<NadMPTemplateDays> findBy_Id(@Param("pid") Long id);
	
	
}
