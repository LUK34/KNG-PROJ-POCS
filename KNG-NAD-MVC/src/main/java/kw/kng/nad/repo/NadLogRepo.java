package kw.kng.nad.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import kw.kng.nad.entities.NadLog;

public interface NadLogRepo extends JpaRepository<NadLog, Long> 
{

	List<NadLog> findAllByOrderByIdDesc();
}
