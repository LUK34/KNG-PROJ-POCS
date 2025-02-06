package kw.kng.nad.service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import kw.kng.nad.dto.NadMPTemplateDaysDto;
import kw.kng.nad.entities.NadMPTemplate;
import kw.kng.nad.entities.NadMPTemplateDays;
import kw.kng.nad.exceptions.ResourceNotFoundException;
import kw.kng.nad.repo.NadMPTemplateDaysRepo;
import kw.kng.nad.repo.NadMPTemplateRepo;

@Service
public class NadMPTemplateDaysServiceImpl implements NadMPTemplateDaysService 
{
	// ----------------------------------------------------------------------------------------------------------
				// Constructor Injection
			private NadMPTemplateRepo nmp_temp_repo;
			private NadMPTemplateDaysRepo nmp_temp_days_repo;
			private ModelMapper modelMapper;
			
			public NadMPTemplateDaysServiceImpl(NadMPTemplateRepo nmp_temp_repo, NadMPTemplateDaysRepo nmp_temp_days_repo, ModelMapper modelMapper) 
			{
				this.nmp_temp_repo=nmp_temp_repo;
				this.nmp_temp_days_repo = nmp_temp_days_repo;
				this.modelMapper = modelMapper;
			}
	// ----------------------------------------------------------------------------------------------------------

			@Override
			public NadMPTemplateDaysDto createNadMPTemplateDays(NadMPTemplateDaysDto nadMPTemplateDaysDto)
			{
				//Transfer DTO to Entity Class
				System.out.println("Meal plan template Service -> CREATE started.");
				NadMPTemplateDays nadMPTemplateDays = modelMapper.map(nadMPTemplateDaysDto, NadMPTemplateDays.class);
				
				// The nadMPTemplateDaysDto.getTplId()will not be mapped by modelMapper. So we have to do it manually.
				NadMPTemplate nadMPTemplate=nmp_temp_repo.findById(nadMPTemplateDaysDto.getTplId()).orElseThrow( () -> new ResourceNotFoundException("Meal Plan Template with id: " + nadMPTemplateDaysDto.getTplId() + " not found" ));
				nadMPTemplateDays.setNadMPTemplate(nadMPTemplate);
			
				//Save the MEAL PLAN TEMPLATE DAYS details in PERSISTENCE layer
				NadMPTemplateDays savedNadMPTemplateDays = nmp_temp_days_repo.save(nadMPTemplateDays);
				
				System.out.println("---------------------------------------------- ");
				System.out.println("Service Layer -> NadMealPlanTemplateDays -> POST -> PASS -> " + 
						"MPTD Id value:"+
						nadMPTemplateDays.getId() +"\n"+
						"MPTD Day Name value:"+
						nadMPTemplateDays.getTplDays() +"\n"+
						"MPTD Template code value:"+
						nadMPTemplateDays.getTplCode() +"\n"+ 
						"MPTD Template Crse Frequency value:"+
						nadMPTemplateDays.getTpleCrsFre() +"\n"+
						"MPTD Template Availability Status value:"+
						nadMPTemplateDays.getTplAvl() +"\n"+ 
						"MPTD Template Public Status value:"+
						nadMPTemplateDays.getTplPub() +"\n"+ 
						"MPTD Day value:"+
						nadMPTemplateDays.getTplDay() +"\n"+
						"MPTD Template Relationship id value:"+
						nadMPTemplateDays.getNadMPTemplate().getId()+"\n"+
						"MPTD Total Energy value:"+
						nadMPTemplateDays.getTplTEn() +"\n"+ 
						"MPTD Total Fat value:"+
						nadMPTemplateDays.getTplTFa() +"\n"+ 
						"MPTD Total Ch value:"+
						nadMPTemplateDays.getTplTCh() +"\n"+ 
						"MPTD Total Protein value:"+
						nadMPTemplateDays.getTplTPr() +"\n"+ 
						"MPTD Total Sodium value:"+
						nadMPTemplateDays.getTplTSod() +"\n"+
						"MPTD Total Sugar value:"+
						nadMPTemplateDays.getTplTSug()+"\n"+
						"DTO Treatment Id Value:"+
						nadMPTemplateDaysDto.getTplId() );
				System.out.println("---------------------------------------------- ");
				
				
				//Transfer ENTITY to DTO
				return modelMapper.map(savedNadMPTemplateDays, NadMPTemplateDaysDto.class);
			}

			@Override
			public NadMPTemplateDaysDto getNadMPTemplateDaysById(Long id) 
			{
				//Find the specific MEAL PLAN TEMPLATE DAYS from persistence using ID. If not found throw ResourceNotFoundException
				NadMPTemplateDays nadMPTemplateDays = nmp_temp_days_repo.findById(id).orElseThrow(() -> new ResourceNotFoundException("Meal Plan Template Days with id: "+id+" not found in DB !!!"));
				
				System.out.println("---------------------------------------------- ");
				System.out.println("Service Layer -> NadMealPlanTemplateDays -> GET by id -> PASS -> " + 
						"MPTD Id value:"+
						nadMPTemplateDays.getId() +"\n"+
						"MPTD Day Name value:"+
						nadMPTemplateDays.getTplDays() +"\n"+
						"MPTD Template code value:"+
						nadMPTemplateDays.getTplCode() +"\n"+ 
						"MPTD Template Crse Frequency value:"+
						nadMPTemplateDays.getTpleCrsFre() +"\n"+
						"MPTD Template Availability Status value:"+
						nadMPTemplateDays.getTplAvl() +"\n"+ 
						"MPTD Template Public Status value:"+
						nadMPTemplateDays.getTplPub() +"\n"+ 
						"MPTD Day value:"+
						nadMPTemplateDays.getTplDay() +"\n"+
						"MPTD Template Relationship id value:"+
						nadMPTemplateDays.getNadMPTemplate().getId()+"\n"+
						"MPTD Total Energy value:"+
						nadMPTemplateDays.getTplTEn() +"\n"+ 
						"MPTD Total Fat value:"+
						nadMPTemplateDays.getTplTFa() +"\n"+ 
						"MPTD Total Ch value:"+
						nadMPTemplateDays.getTplTCh() +"\n"+ 
						"MPTD Total Protein value:"+
						nadMPTemplateDays.getTplTPr() +"\n"+ 
						"MPTD Total Sodium value:"+
						nadMPTemplateDays.getTplTSod() +"\n"+
						"MPTD Total Sugar value:"+
						nadMPTemplateDays.getTplTSug()+"\n"+
						"DTO Treatment Id Value:"
						/* + nadMPTemplateDays.getTplId()*/ );
				System.out.println("---------------------------------------------- ");
				
				// Custom ModelMapper mapping to include NadMPCategory's id in the DTO
			    NadMPTemplateDaysDto nadMPTemplateDaysDto = modelMapper.map(nadMPTemplateDays, NadMPTemplateDaysDto.class);

			    // Manually map the tplCat from NadMPCategory to NadMPTemplateDto
			    nadMPTemplateDaysDto.setTplId(nadMPTemplateDays.getNadMPTemplate().getId());

			    return nadMPTemplateDaysDto;
			}

			@Override
			public NadMPTemplateDaysDto updateNadMPTemplateDaysById(Long id, NadMPTemplateDaysDto nadMPTemplateDaysDto) 
			{
				//Find the specific MEAL PLAN TEMPLATE from persistence using ID. If not found throw ResourceNotFoundException
				NadMPTemplateDays nadMPTemplateDays = nmp_temp_days_repo.findById(id).orElseThrow(() -> new ResourceNotFoundException("Meal Plan Template Days with id: "+id+" not found in DB !!!"));
			
				System.out.println("---------------------------------------------- ");
				System.out.println("Service Layer -> NadMealPlanTemplateDays -> PUT by id -> PASS -> " + 
				nadMPTemplateDaysDto.getId() +" "+
				nadMPTemplateDaysDto.getTplDays() +" "+
				nadMPTemplateDaysDto.getTplCode() +" "+ 
				nadMPTemplateDaysDto.getTpleCrsFre() +" "+
				nadMPTemplateDaysDto.getTplAvl() +" "+ 
				nadMPTemplateDaysDto.getTplPub() +" "+ 
				nadMPTemplateDaysDto.getTplDay() +" "+
				nadMPTemplateDaysDto.getTplId()+""+
				nadMPTemplateDaysDto.getTplTEn() +" "+ 
				nadMPTemplateDaysDto.getTplTFa() +" "+ 
				nadMPTemplateDaysDto.getTplTCh() +" "+ 
				nadMPTemplateDaysDto.getTplTPr() +" "+ 
				nadMPTemplateDaysDto.getTplTSod() +" "+
				nadMPTemplateDaysDto.getTplTSug() );
				System.out.println("---------------------------------------------- ");
				
				nadMPTemplateDays.setId(nadMPTemplateDaysDto.getId());
				nadMPTemplateDays.setTplDays(nadMPTemplateDaysDto.getTplDays());
				nadMPTemplateDays.setTplCode(nadMPTemplateDaysDto.getTplCode());
				nadMPTemplateDays.setTpleCrsFre(nadMPTemplateDaysDto.getTpleCrsFre());
				nadMPTemplateDays.setTplAvl(nadMPTemplateDaysDto.getTplAvl());
				nadMPTemplateDays.setTplPub(nadMPTemplateDaysDto.getTplPub());
				nadMPTemplateDays.setTplDay(nadMPTemplateDaysDto.getTplDay());				
				nadMPTemplateDays.setTplTEn(nadMPTemplateDaysDto.getTplTEn());
				nadMPTemplateDays.setTplTFa(nadMPTemplateDaysDto.getTplTFa());
				nadMPTemplateDays.setTplTCh(nadMPTemplateDaysDto.getTplTCh());
				nadMPTemplateDays.setTplTPr(nadMPTemplateDaysDto.getTplTPr());
				nadMPTemplateDays.setTplTSod(nadMPTemplateDaysDto.getTplTSod());
				nadMPTemplateDays.setTplTSug(nadMPTemplateDaysDto.getTplTSug());
				
				
				NadMPTemplate nadMPTemplate=nmp_temp_repo.findById(nadMPTemplateDaysDto.getTplId()).orElseThrow(() -> new ResourceNotFoundException("Meal Plan Template with id: " + nadMPTemplateDaysDto.getTplId() + " not found"));
				nadMPTemplateDays.setNadMPTemplate(nadMPTemplate);
			
				
				NadMPTemplateDays updatenadMPTemplateDays=nmp_temp_days_repo.save(nadMPTemplateDays);
				
				return modelMapper.map(updatenadMPTemplateDays, NadMPTemplateDaysDto.class);
			}

			@Override
			public void deletNadMPTemplateDaysById(Long id)
			{
				//Find the specific MEAL PLAN TEMPLATE from persistence using ID. If not found throw ResourceNotFoundException
				NadMPTemplateDays nadMPTemplateDays = nmp_temp_days_repo.findById(id).orElseThrow(() -> new ResourceNotFoundException("Meal Plan Template with id: "+id+" not found in DB !!!"));
				nmp_temp_days_repo.delete(nadMPTemplateDays);
				
			}

			@Override
			public List<NadMPTemplateDaysDto> getNadMPTemplateDaysListAll()
			{
				//Find all the MEAL PLAN TEMPLATE from PERSISTENCE
				List<NadMPTemplateDays> nadMPTemplateDaysList = nmp_temp_days_repo.findAllByOrderByIdDesc();
				
				// Configure ModelMapper for the custom mapping
			    modelMapper.typeMap(NadMPTemplateDays.class, NadMPTemplateDaysDto.class)
			               .addMappings(mapper -> mapper.map(src -> src.getNadMPTemplate().getId(), NadMPTemplateDaysDto::setTplId));
				
				//Transfer ENTITY to DTO
				return nadMPTemplateDaysList.stream().map(n -> modelMapper.map(n, NadMPTemplateDaysDto.class)).collect(Collectors.toList());
			}

			@Override
			public List<NadMPTemplateDaysDto> getNadMPTemplateDaysListSpecificById(Long id)
			{
				System.out.println("Meal Plan Days Template List: "+id);
				
				//findById return -> Optional. so If you want list create ur custom method
				 
				//Find the MEAL PLAN TEMPLATE by id from PERSISTENCE
				List<NadMPTemplateDays> nadMPTemplateDayList_byId = (List<NadMPTemplateDays>) nmp_temp_days_repo.findBy_Id(id);
				System.out.println("Service Layer -> Meal Plan Days Template List-> GET -> List details by id: "+nadMPTemplateDayList_byId);
				
				
				//If null.Compare directly since `n.getId()` is a primitive long. Long cannot be null so no need to compare.
				if(nadMPTemplateDayList_byId
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
					return nadMPTemplateDayList_byId
							.stream()
							.map(n ->{
									NadMPTemplateDaysDto dto=modelMapper.map(n, NadMPTemplateDaysDto.class);
									dto.setTplId(n.getNadMPTemplate().getId());
									return dto;
								})
							.collect(Collectors.toList());
				}
			
			}

			
			
			
			

}
