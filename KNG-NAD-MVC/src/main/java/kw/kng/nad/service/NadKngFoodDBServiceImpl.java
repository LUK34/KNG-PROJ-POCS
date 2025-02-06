package kw.kng.nad.service;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import kw.kng.nad.dto.NadKngFoodDBDto;
import kw.kng.nad.entities.NadKngFoodDB;
import kw.kng.nad.exceptions.ResourceNotFoundException;
import kw.kng.nad.repo.NadKngFoodDBRepo;

@Service
public class NadKngFoodDBServiceImpl implements NadKngFoodDBService {

	// ----------------------------------------------------------------------------------------------------------
	// Constructor Injection
	private NadKngFoodDBRepo nrepo;
	private ModelMapper modelMapper;

	public NadKngFoodDBServiceImpl(NadKngFoodDBRepo nrepo, ModelMapper modelMapper) 
	{
		this.nrepo = nrepo;
		this.modelMapper = modelMapper;
	}
	// ----------------------------------------------------------------------------------------------------------

	@Override
	public NadKngFoodDBDto createNadKngFoodDB(NadKngFoodDBDto nadKngFoodDBDto) 
	{
		//Transfer DTO to Entity Class
		NadKngFoodDB nadKngFoodDB = modelMapper.map(nadKngFoodDBDto, NadKngFoodDB.class);
				
		//Save the KNG FOOD DB details in PERSISTENCE layer
		NadKngFoodDB savedNadKngFoodDB = nrepo.save(nadKngFoodDB);
			
		//Transfer ENTITY to DTO
		return modelMapper.map(savedNadKngFoodDB, NadKngFoodDBDto.class);
	}

	@Override
	public NadKngFoodDBDto getNadKngFoodDBById(Long id)
	{
		//Find the specific KNG FOOD DB from persistence using ID. If not found throw ResourceNotFoundException
		NadKngFoodDB nadKngFoodDB = nrepo.findById(id).orElseThrow(() -> new ResourceNotFoundException("KNG Food DB with id: "+id+" not found in DB !!!"));
				
		return modelMapper.map(nadKngFoodDB, NadKngFoodDBDto.class);
	}

	@Override
	public NadKngFoodDBDto updateNadKngFoodDBById(Long id, NadKngFoodDBDto nadKngFoodDBDto)
	{
		//Find the specific CLINIC from persistence using ID. If not found throw ResourceNotFoundException
		NadKngFoodDB nadKngFoodDB = nrepo.findById(id).orElseThrow(() -> new ResourceNotFoundException("KNG Food DB with id: "+id+" not found in DB !!!"));
							
				nadKngFoodDB.setFcode(nadKngFoodDBDto.getFcode());
				nadKngFoodDB.setFoodName(nadKngFoodDBDto.getFoodName());
				nadKngFoodDB.setFoodType(nadKngFoodDBDto.getFoodType());
				nadKngFoodDB.setPortions(nadKngFoodDB.getPortions());
				nadKngFoodDB.setMeasurement(nadKngFoodDBDto.getMeasurement());
				nadKngFoodDB.setCalories(nadKngFoodDBDto.getCalories());
				nadKngFoodDB.setCarbohydrates(nadKngFoodDBDto.getCarbohydrates());
				nadKngFoodDB.setFat(nadKngFoodDBDto.getFat());
				nadKngFoodDB.setProtein(nadKngFoodDBDto.getProtein());
				nadKngFoodDB.setFiber(nadKngFoodDBDto.getFiber());
				nadKngFoodDB.setSodium(nadKngFoodDBDto.getSodium());
				nadKngFoodDB.setStat(nadKngFoodDBDto.getStat());
				
				NadKngFoodDB updateNadKngFoodDB = nrepo.save(nadKngFoodDB);
				
		return modelMapper.map(updateNadKngFoodDB, NadKngFoodDBDto.class);
	}

	@Override
	public void deletNadKngFoodDBById(Long id) 
	{
		//Find the specific CLINIC from persistence using ID. If not found throw ResourceNotFoundException
		NadKngFoodDB nadKngFoodDB = nrepo.findById(id).orElseThrow(() -> new ResourceNotFoundException("KNG Food DB with id: "+id+" not found in DB !!!"));
					
		nrepo.delete(nadKngFoodDB);
	}

	@Override
	public List<NadKngFoodDBDto> getNadKngFoodDBListAll() 
	{
		//Find all the category from PERSISTENCE
		List<NadKngFoodDB> nadKngFoodDBList = nrepo.findAllByOrderByFoodNameAsc();
				
		System.out.println("Service Layer -> Nad KNG Food DB List ->" +nadKngFoodDBList);
				
		//Transfer ENTITY to DTO
		return nadKngFoodDBList.stream().map(n -> modelMapper.map(n, NadKngFoodDBDto.class)).collect(Collectors.toList());
	}

}
