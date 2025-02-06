package kw.kng.nad.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import kw.kng.nad.entities.NadMPCategory;

public interface NadMPCategoryRepo extends JpaRepository<NadMPCategory, Long> 
{
	List<NadMPCategory> findAllByOrderByIdDesc();
	
	List<NadMPCategory> findAllByOrderByCtgAsc();
	
	@Query("FROM NadMPCategory c WHERE c.stat = 'Y' ORDER BY c.ctg ASC ")
	 List<NadMPCategory> findAllCategoriesByStat();
}
