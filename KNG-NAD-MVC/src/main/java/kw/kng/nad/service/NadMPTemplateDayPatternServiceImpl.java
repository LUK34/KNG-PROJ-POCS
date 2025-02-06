package kw.kng.nad.service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import kw.kng.nad.dto.NadMPTemplateDayPatternDto;
import kw.kng.nad.dto.NadMPTemplateDaysDto;
import kw.kng.nad.entities.NadMPTemplateDayPattern;
import kw.kng.nad.repo.NadMPTemplateDayPatternRepo;

@Service
public class NadMPTemplateDayPatternServiceImpl implements NadMPTemplateDayPatternService 
{
	
	// ----------------------------------------------------------------------------------------------------------
	// Constructor Injection
	private NadMPTemplateDayPatternRepo nmp_temp_daypattern_repo;
	private ModelMapper modelMapper;

	public NadMPTemplateDayPatternServiceImpl(NadMPTemplateDayPatternRepo nmp_temp_daypattern_repo, ModelMapper modelMapper) 
	{
		this.nmp_temp_daypattern_repo = nmp_temp_daypattern_repo;
		this.modelMapper = modelMapper;
	}
// ----------------------------------------------------------------------------------------------------------

	

	@Override
	public NadMPTemplateDayPatternDto createNadMPTemplateDayPatternDto(
			NadMPTemplateDayPatternDto nadMPTemplateDayPatternDto) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public NadMPTemplateDayPatternDto getNadMPTemplateDayPatternDtoById(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public NadMPTemplateDayPatternDto updateNadMPTemplateDayPatternDtoById(Long id,
			NadMPTemplateDayPatternDto nadMPTemplateDayPatternDto) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void deletNadMPTemplateDayPatternDtoById(Long id) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<NadMPTemplateDayPatternDto> getNadMPTemplateDayPatternDtoListAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<NadMPTemplateDayPatternDto> getNadMPTemplateDayPatternDtoListSpecificById(Long id) 
	{
		System.out.println("Meal Plan Day Pattern List: "+id);
		
		//findById return -> Optional. so If you want list create ur custom method
		 
		//Find the MEAL PLAN TEMPLATE by id from PERSISTENCE
		List<NadMPTemplateDayPattern> nadMPTemplateDayPatternList_byId = (List<NadMPTemplateDayPattern>) nmp_temp_daypattern_repo.findBy_Id(id);
		System.out.println("Service Layer -> Meal Plan Days Pattern List-> GET -> List details by id: "+nadMPTemplateDayPatternList_byId);
		
		
		//If null.Compare directly since `n.getId()` is a primitive long. Long cannot be null so no need to compare.
		if(nadMPTemplateDayPatternList_byId
				.stream()
			    .filter(n -> n.getId() == id)
			    .findFirst() == null)
		 {
			System.out.println("Service hello 1");
			 return new ArrayList<>();
		 }
		else 
		{
			//Transfer ENTITY to DTO
			return nadMPTemplateDayPatternList_byId
					.stream()
					.map(n ->{
						NadMPTemplateDayPatternDto dto=modelMapper.map(n, NadMPTemplateDayPatternDto.class);
							dto.setTplDayId(n.getNadMPTemplateDays().getId()); 
							return dto;
						})
					.collect(Collectors.toList());
		}
	
	}

}
