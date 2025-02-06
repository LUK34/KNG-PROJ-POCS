package kw.kng.nad.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import kw.kng.nad.entities.NadMPTemplateDayPattern;

public interface NadMPTemplateDayPatternRepo extends JpaRepository<NadMPTemplateDayPattern, Long>
{
List<NadMPTemplateDayPattern> findAllByOrderByIdDesc();
	
	@Query("from NadMPTemplateDayPattern u where u.nadMPTemplateDays.id = :pid")
	List<NadMPTemplateDayPattern> findBy_Id(@Param("pid") Long id);

}
