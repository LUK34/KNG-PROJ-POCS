package kw.kng.nad.service;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import kw.kng.nad.dto.NadMPCategoryDto;
import kw.kng.nad.entities.NadMPCategory;
import kw.kng.nad.exceptions.ResourceNotFoundException;
import kw.kng.nad.repo.NadMPCategoryRepo;

@Service
public class NadMPCategoryServiceImpl implements NadMPCategoryService
{

	// ----------------------------------------------------------------------------------------------------------
			// Constructor Injection
		private NadMPCategoryRepo nmpcat_repo;
		private ModelMapper modelMapper;
		
		public NadMPCategoryServiceImpl(NadMPCategoryRepo nmpcat_repo, ModelMapper modelMapper) 
		{
			this.nmpcat_repo = nmpcat_repo;
			this.modelMapper = modelMapper;
		}
	// ----------------------------------------------------------------------------------------------------------

		@Override
		public NadMPCategoryDto createNadMealPlanCategory(NadMPCategoryDto nadMPCategoryDto) 
		{
			//Transfer DTO to Entity Class
			NadMPCategory nadMPCategory = modelMapper.map(nadMPCategoryDto, NadMPCategory.class);
			
			System.out.println("---------------------------------------------- ");
			System.out.println("Service Layer -> NadClinics -> POST -> PASS -> " + nadMPCategory.getId() +" "+ nadMPCategory.getCtg() +" "+ nadMPCategory.getCtga() +" "+ nadMPCategory.getStat());
			System.out.println("---------------------------------------------- ");
			
			//Save the Clinic details in PERSISTENCE layer
			NadMPCategory savedNadMPCategory = nmpcat_repo.save(nadMPCategory);
			
			//Transfer ENTITY to DTO
			return modelMapper.map(savedNadMPCategory, NadMPCategoryDto.class);
		}

		@Override
		public NadMPCategoryDto getNadMealPlanCategoryById(Long id)
		{
			//Find the specific MEAL PLAN CATEGORY from persistence using ID. If not found throw ResourceNotFoundException
			NadMPCategory nadMPCategory = nmpcat_repo.findById(id).orElseThrow(() -> new ResourceNotFoundException("Meal Plan Category with id: "+id+" not found in DB !!!"));
			
			System.out.println("---------------------------------------------- ");
			System.out.println("Service Layer -> NadClinics get by id-> GET -> PASS -> " + nadMPCategory.getId() +" "+ nadMPCategory.getCtg() +" "+ nadMPCategory.getCtga() +" "+ nadMPCategory.getStat());
			System.out.println("---------------------------------------------- ");
			
			
			return modelMapper.map(nadMPCategory, NadMPCategoryDto.class);
		}

		@Override
		public NadMPCategoryDto updateNadMealPlanCategoryById(Long id, NadMPCategoryDto nadMPCategoryDto) 
		{
			//Find the specific MEAL PLAN CATEGORY from persistence using ID. If not found throw ResourceNotFoundException
			NadMPCategory nadMPCategory = nmpcat_repo.findById(id).orElseThrow(() -> new ResourceNotFoundException("Meal Plan Category with id: "+id+" not found in DB !!!"));
		
			System.out.println("---------------------------------------------- ");
			System.out.println("Service Layer -> NadClinics -> PUT -> PASS -> " + nadMPCategory.getId() +" "+ nadMPCategory.getCtg() +" "+ nadMPCategory.getCtga() +" "+ nadMPCategory.getStat());
			System.out.println("---------------------------------------------- ");
			
			nadMPCategory.setId(nadMPCategoryDto.getId());
			nadMPCategory.setCtg(nadMPCategoryDto.getCtg());
			nadMPCategory.setCtga(nadMPCategoryDto.getCtga());
			nadMPCategory.setStat(nadMPCategoryDto.getStat());
			
			NadMPCategory updatenadMPCategory=nmpcat_repo.save(nadMPCategory);
			
			return modelMapper.map(updatenadMPCategory, NadMPCategoryDto.class);
			
		}

		@Override
		public void deletNadMealPlanCategoryDtoById(Long id) 
		{
			//Find the specific MEAL PLAN CATEGORY from persistence using ID. If not found throw ResourceNotFoundException
			NadMPCategory nadMPCategory = nmpcat_repo.findById(id).orElseThrow(() -> new ResourceNotFoundException("Meal Plan Category with id: "+id+" not found in DB !!!"));
				
			nmpcat_repo.delete(nadMPCategory);
			
		}

		@Override
		public List<NadMPCategoryDto> getNadMealPlanCategoryListAll() 
		{
			//Find all the MEAL PLAN CATEGORY from PERSISTENCE
			List<NadMPCategory> nadMPCategoryList = nmpcat_repo.findAllByOrderByIdDesc();
			
			//Transfer ENTITY to DTO
			return nadMPCategoryList.stream().map(n -> modelMapper.map(n, NadMPCategoryDto.class)).collect(Collectors.toList());
		}

		@Override
		public List<NadMPCategoryDto> getNadClinicListAll_nameAsc() 
		{
			//Find all the MEAL PLAN CATEGORY from PERSISTENCE
			List<NadMPCategory> nadMPCategoryList = nmpcat_repo.findAllCategoriesByStat();
			
			//Transfer ENTITY to DTO
			return nadMPCategoryList.stream().map(n -> modelMapper.map(n, NadMPCategoryDto.class)).collect(Collectors.toList());
		}

	
	

}
