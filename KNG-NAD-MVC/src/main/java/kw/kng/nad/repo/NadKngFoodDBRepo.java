package kw.kng.nad.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import kw.kng.nad.entities.NadKngFoodDB;

public interface NadKngFoodDBRepo extends JpaRepository<NadKngFoodDB, Long> 
{
	
	List<NadKngFoodDB> findAllByOrderByIdDesc();
	List<NadKngFoodDB> findAllByOrderByFoodNameAsc();	
	
}
