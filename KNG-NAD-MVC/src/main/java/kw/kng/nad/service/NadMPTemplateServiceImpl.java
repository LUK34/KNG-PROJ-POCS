package kw.kng.nad.service;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import kw.kng.nad.dto.NadMPTemplateDto;
import kw.kng.nad.entities.NadMPCategory;
import kw.kng.nad.entities.NadMPTemplate;
import kw.kng.nad.exceptions.ResourceNotFoundException;
import kw.kng.nad.repo.NadMPCategoryRepo;
import kw.kng.nad.repo.NadMPTemplateRepo;

@Service
public class NadMPTemplateServiceImpl implements NadMPTemplateService
{

	// ----------------------------------------------------------------------------------------------------------
				// Constructor Injection
			private NadMPTemplateRepo nmp_temp_repo;
			private NadMPCategoryRepo nmp_cat_repo;
			private ModelMapper modelMapper;
			
			public NadMPTemplateServiceImpl(NadMPTemplateRepo nmp_temp_repo,NadMPCategoryRepo nmp_cat_repo, ModelMapper modelMapper) 
			{
				this.nmp_temp_repo = nmp_temp_repo;
				this.nmp_cat_repo=nmp_cat_repo;
				this.modelMapper = modelMapper;
			}
	// ----------------------------------------------------------------------------------------------------------

			@Override
			public NadMPTemplateDto createNadMealPlanTemplate(NadMPTemplateDto nadMPTemplateDto)
			{
				//Transfer DTO to Entity Class
				System.out.println("Meal plan controller -> CREATE started.");
				NadMPTemplate nadMPTemplate = modelMapper.map(nadMPTemplateDto, NadMPTemplate.class);
				
				// The nadMPTemplate.getNadMPCategory().getId() will not be mapped by modelMapper. So we have to do it manually.
				NadMPCategory nadMPCategory=nmp_cat_repo.findById(nadMPTemplateDto.getTplCat()).orElseThrow(() -> new ResourceNotFoundException("Meal Plan Category with id: " + nadMPTemplateDto.getTplCat() + " not found"));
				nadMPTemplate.setNadMPCategory(nadMPCategory);
			
				//Save the MEAL PLAN TEMPLATE details in PERSISTENCE layer
				NadMPTemplate savedNadMPTemplate = nmp_temp_repo.save(nadMPTemplate);
				
				System.out.println("---------------------------------------------- ");
				System.out.println("Service Layer -> NadMealPlanTemplate -> POST -> PASS -> " + 
				nadMPTemplate.getId() +" "+ nadMPTemplate.getTplName() +" "+ nadMPTemplate.getTplCode() +" "+ 
				nadMPTemplate.getTpleCrsFre() +" "+ nadMPTemplate.getTplAvl() +" "+ nadMPTemplate.getTplPub() +" "+ nadMPTemplate.getTplObj() +" "+ nadMPTemplate.getTplPlan() +" "+
				""+nadMPTemplate.getNadMPCategory().getId()+
				""+nadMPTemplate.getTplTEn() +" "+ nadMPTemplate.getTplTFa() +" "+ nadMPTemplate.getTplTCh() +" "+ nadMPTemplate.getTplTPr() +" "+ nadMPTemplate.getTplTSod() +" "+
				nadMPTemplate.getTplTSug() );
				System.out.println("---------------------------------------------- ");
				
				
				//Transfer ENTITY to DTO
				return modelMapper.map(savedNadMPTemplate, NadMPTemplateDto.class);
			}

			@Override
			public NadMPTemplateDto getNadMealPlanTemplateById(Long id)
			{
				//Find the specific MEAL PLAN TEMPLATE from persistence using ID. If not found throw ResourceNotFoundException
				NadMPTemplate nadMPTemplate = nmp_temp_repo.findById(id).orElseThrow(() -> new ResourceNotFoundException("Meal Plan Template with id: "+id+" not found in DB !!!"));
				
				System.out.println("---------------------------------------------- ");
				System.out.println("Service Layer -> NAD Meal Plan Template -> GET by Id -> PASS -> " + 
						nadMPTemplate.getId() +" "+ nadMPTemplate.getTplName() +" "+ nadMPTemplate.getTplCode() +" "+ 
						nadMPTemplate.getTpleCrsFre() +" "+ nadMPTemplate.getTplAvl() +" "+ nadMPTemplate.getTplPub() +" "+ nadMPTemplate.getTplObj() +" "+ nadMPTemplate.getTplPlan() +" "+
						nadMPTemplate.getNadMPCategory()+""+nadMPTemplate.getTplTEn() +" "+ nadMPTemplate.getTplTFa() +" "+ nadMPTemplate.getTplTCh() +" "+ nadMPTemplate.getTplTPr() +" "+ nadMPTemplate.getTplTSod() +" "+
						nadMPTemplate.getTplTSug());
				System.out.println("---------------------------------------------- ");
				
				// Custom ModelMapper mapping to include NadMPCategory's id in the DTO
			    NadMPTemplateDto nadMPTemplateDto = modelMapper.map(nadMPTemplate, NadMPTemplateDto.class);

			    // Manually map the tplCat from NadMPCategory to NadMPTemplateDto
			    nadMPTemplateDto.setTplCat(nadMPTemplate.getNadMPCategory().getId());

			    return nadMPTemplateDto;
			}

			@Override
			public NadMPTemplateDto updateNadMealPlanTemplateById(Long id, NadMPTemplateDto nadMPTemplateDto) 
			{
				//Find the specific MEAL PLAN TEMPLATE from persistence using ID. If not found throw ResourceNotFoundException
				NadMPTemplate nadMPTemplate = nmp_temp_repo.findById(id).orElseThrow(() -> new ResourceNotFoundException("Meal Plan Template with id: "+id+" not found in DB !!!"));
			
				System.out.println("---------------------------------------------- ");
				System.out.println("Service Layer -> NAD Meal Plan Template -> PUT -> PASS -> "+" "
				+nadMPTemplate.getId() +" "+ nadMPTemplate.getTplName() +" "+ nadMPTemplate.getTplCode() +" "+ 
				nadMPTemplate.getTpleCrsFre() +" "+ nadMPTemplate.getTplAvl() +" "+ nadMPTemplate.getTplPub() +" "+ nadMPTemplate.getTplObj() +" "+ nadMPTemplate.getTplPlan() +" "+
				nadMPTemplate.getNadMPCategory()+""+nadMPTemplate.getTplTEn() +" "+ nadMPTemplate.getTplTFa() +" "+ nadMPTemplate.getTplTCh() +" "+ nadMPTemplate.getTplTPr() +" "+ nadMPTemplate.getTplTSod() +" "+
				nadMPTemplate.getTplTSug());
				System.out.println("---------------------------------------------- ");
				
				nadMPTemplate.setId(nadMPTemplateDto.getId());
				nadMPTemplate.setTplName(nadMPTemplateDto.getTplName());
				nadMPTemplate.setTplCode(nadMPTemplateDto.getTplCode());
				nadMPTemplate.setTpleCrsFre(nadMPTemplateDto.getTpleCrsFre());
				nadMPTemplate.setTplAvl(nadMPTemplateDto.getTplAvl());
				nadMPTemplate.setTplPub(nadMPTemplateDto.getTplPub());
				nadMPTemplate.setTplObj(nadMPTemplateDto.getTplObj());
				nadMPTemplate.setTplPlan(nadMPTemplateDto.getTplPlan());
				nadMPTemplate.setTplTEn(nadMPTemplateDto.getTplTEn());
				nadMPTemplate.setTplTFa(nadMPTemplateDto.getTplTFa());
				nadMPTemplate.setTplTCh(nadMPTemplateDto.getTplTCh());
				nadMPTemplate.setTplTPr(nadMPTemplateDto.getTplTPr());
				nadMPTemplate.setTplTSod(nadMPTemplateDto.getTplTSod());
				nadMPTemplate.setTplTSug(nadMPTemplateDto.getTplTSug());
				
				
				NadMPCategory nadMPCategory=nmp_cat_repo.findById(nadMPTemplateDto.getTplCat()).orElseThrow(() -> new ResourceNotFoundException("Category with id: " + nadMPTemplateDto.getTplCat() + " not found"));
				nadMPTemplate.setNadMPCategory(nadMPCategory);
			
				
				NadMPTemplate updatenadMPTemplate=nmp_temp_repo.save(nadMPTemplate);
				
				return modelMapper.map(updatenadMPTemplate, NadMPTemplateDto.class);
			}

			@Override
			public void deletNadMealPlanTemplateDtoById(Long id) 
			{
				//Find the specific MEAL PLAN TEMPLATE from persistence using ID. If not found throw ResourceNotFoundException
				NadMPTemplate nadMPTemplate = nmp_temp_repo.findById(id).orElseThrow(() -> new ResourceNotFoundException("Meal Plan Template with id: "+id+" not found in DB !!!"));
				nmp_temp_repo.delete(nadMPTemplate);
				
			}

			@Override
			public List<NadMPTemplateDto> getNadMealPlanTemplateListAll() 
			{
				//Find all the MEAL PLAN TEMPLATE from PERSISTENCE
				List<NadMPTemplate> nadMPTemplateList = nmp_temp_repo.findAllByOrderByIdDesc();
				
				// Configure ModelMapper for the custom mapping
			    modelMapper.typeMap(NadMPTemplate.class, NadMPTemplateDto.class)
			               .addMappings(mapper -> mapper.map(src -> src.getNadMPCategory().getId(), NadMPTemplateDto::setTplCat));
				
				//Transfer ENTITY to DTO
				return nadMPTemplateList.stream().map(n -> modelMapper.map(n, NadMPTemplateDto.class)).collect(Collectors.toList());
			}

			@Override
			public List<NadMPTemplateDto> getNadMealPlanTemplateListSpecificById(Long id)
			{
				//Find the MEAL PLAN TEMPLATE by id from PERSISTENCE
				NadMPTemplate nadMPTemplate = nmp_temp_repo.findById(id).orElseThrow(() -> new ResourceNotFoundException("Meal Plan Template with id: "+id+" not found in DB !!!"));
				
				// Custom ModelMapper mapping to include NadMPCategory's id in the DTO
				// Configure ModelMapper for the custom mapping
			    modelMapper.typeMap(NadMPTemplate.class, NadMPTemplateDto.class)
			               .addMappings(mapper -> mapper.map(src -> src.getNadMPCategory().getId(), NadMPTemplateDto::setTplCat));
				
			    // Transfer ENTITY to DTO
			    NadMPTemplateDto nadMPTemplateDto = modelMapper.map(nadMPTemplate, NadMPTemplateDto.class);

			    // Return as a list (if required by your method signature)
			    return List.of(nadMPTemplateDto);
			}


}
