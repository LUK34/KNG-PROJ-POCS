package kw.kng.nad.repo;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import kw.kng.nad.entities.NadMPTemplate;

public interface NadMPTemplateRepo extends JpaRepository<NadMPTemplate, Long> 
{
	List<NadMPTemplate> findAllByOrderByIdDesc();
	Optional<NadMPTemplate> findById(Long id);

}
