package kw.kng.nad.controller;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import kw.kng.nad.dto.NadClinicDto;
import kw.kng.nad.dto.NadMPCategoryDto;
import kw.kng.nad.dto.NadMPTemplateDaysDto;
import kw.kng.nad.dto.NadMPTemplateDto;
import kw.kng.nad.errorhandler.ValidationErrorHandler;
import kw.kng.nad.exceptions.ResourceNotFoundException;
import kw.kng.nad.service.NadMPCategoryService;
import kw.kng.nad.service.NadMPTemplateDaysService;
import kw.kng.nad.service.NadMPTemplateService;

@Controller
@RequestMapping("/nadMPTemplateDetails/{id}")
public class NadMPTemplateDaysController 
{

	// ----------------------------------------------------------------------------------------------------------
		// Constructor Injection
	private NadMPTemplateService nad_mp_templ_s; //Meal Plan Template service
	private NadMPCategoryService nad_mp_cat_s;	//Meal Plan Category service
	private NadMPTemplateDaysService nad_mp_templ_days_s; //Meal Plan Template Day Service
	private ValidationErrorHandler vh;
	
	public NadMPTemplateDaysController(NadMPTemplateService nad_mp_templ_s,NadMPCategoryService nad_mp_cat_s, NadMPTemplateDaysService nad_mp_templ_days_s, ValidationErrorHandler vh) 
	{
	this.nad_mp_templ_s = nad_mp_templ_s;
	this.nad_mp_templ_days_s = nad_mp_templ_days_s;
	this.nad_mp_cat_s=nad_mp_cat_s;
	this.vh=vh;
	}
	// ----------------------------------------------------------------------------------------------------------
	
	
	@Value("${flashy.mpt}")
	private String flashyMpt;
	
	@Value("${flashy.mpds}")
	private String flashyMpds;


	// ----------------------------------------------------------------------------------------------------------

	// List the `Day Schedule` + `Meal Plan Template` of the assocated Meal Plan Template
	@GetMapping
	public String findNadMPDailyScheduleDtoById(@PathVariable("id") Long parentId,
										   Model model)
	{
		
		System.out.println("Parent id="+parentId);
		System.out.println("Hi there ->0");
		List<NadMPTemplateDto> nadMealPlanTemplateDetails_byId = nad_mp_templ_s.getNadMealPlanTemplateListSpecificById(parentId);
		List<NadMPTemplateDaysDto> nadMealPlanTemplateDaysList_byId=nad_mp_templ_days_s.getNadMPTemplateDaysListSpecificById(parentId);
		List<NadMPCategoryDto> nadMealPlanCategoryList=nad_mp_cat_s.getNadClinicListAll_nameAsc();
		System.out.println("Hi there ->0.5");
		
		if(nadMealPlanTemplateDetails_byId == null || nadMealPlanTemplateDetails_byId.isEmpty())
		{
			System.out.println("Hi there -> Meal Plan Template List ONLY is either NULL or EMPTY.");
			//model.addAttribute("nadMPTemplateDto", new NadMPTemplateDto());
			model.addAttribute("nadMealPlanTemplateDetails_byId",new ArrayList<>());
		}
		
		if(nadMealPlanTemplateDaysList_byId == null || nadMealPlanTemplateDaysList_byId.isEmpty())
		{
			System.out.println("Hi there -> Meal Plan Template Days List ONLY is either NULL or EMPTY.");
			//model.addAttribute("nadMPTemplateDaysDto", new NadMPTemplateDaysDto());
			model.addAttribute("nadMealPlanTemplateDaysList_byId",new ArrayList<>());
		}
			System.out.println("Hi there -> Passing the parent ID into the CONTROLLER");
			model.addAttribute("parentId",parentId);
			model.addAttribute("nadMPTemplateDto", new NadMPTemplateDto());
			model.addAttribute("nadMPTemplateDaysDto", new NadMPTemplateDaysDto());
			model.addAttribute("nadMealPlanTemplateDetails_byId",nadMealPlanTemplateDetails_byId);
			model.addAttribute("nadMealPlanTemplateDaysList_byId",nadMealPlanTemplateDaysList_byId);
			System.out.println("Day schedule List: "+nadMealPlanTemplateDaysList_byId);
			model.addAttribute("nadMealPlanCategoryList",nadMealPlanCategoryList);
		
		return "nadMPTemplate_Details";
		
	}

	@GetMapping("/editById")
	@ResponseBody
	public ResponseEntity<NadMPTemplateDaysDto> editNadMPTemplateDaysDtoById(
														 @PathVariable("id") Long parentId,
														 @RequestParam("childId") Long childId, 
														  RedirectAttributes ra)
	{
		 try 
		 {
			 System.out.println("Controller Layer -> NadMPTemplateDaySchedule ->EDIT-> GET by id-> SUCCESS");
			 NadMPTemplateDaysDto nadMPTemplateDaysDto = nad_mp_templ_days_s.getNadMPTemplateDaysById(childId);
		     return ResponseEntity.ok(nadMPTemplateDaysDto);
		 }
		catch(ResourceNotFoundException ex)
		{
			System.out.println("Controller Layer -> NadMPTemplateDaySchedule ->EDIT-> GET by id-> FAIL");
			ra.addFlashAttribute("message", ex.getMessage());
			return ResponseEntity.notFound().build();
		}
	}
	
	
	
	
	
	
	// Create the `Day Schedule` of the assocated Meal plan template
	@PostMapping("/create")
	public String createNadMealPlanDailyScheduleById(@Valid @ModelAttribute("nadMPTemplateDaysDto") NadMPTemplateDaysDto nadMPTemplateDaysDto,
			 BindingResult result,
			 RedirectAttributes ra,
			 @PathVariable("id") Long parentId,
			 Model model)
	{
		if(result.hasErrors())
		{
			//NadMPTemplateDaysDto nadMPDaysDto=nad_mp_templ_days_s.createNadMPTemplateDays(nadMPTemplateDaysDto);
			List<NadMPTemplateDto> nadMealPlanTemplateDetails_byId = nad_mp_templ_s.getNadMealPlanTemplateListSpecificById(parentId);
			System.out.println("---------------------------------------------- ");
			System.out.println("Controller Layer -> NadMPTemplateDaySchedule -> POST -> FAIL ->"+ 
					nadMPTemplateDaysDto.getId() +"\n"+
				"Day Schedule Name: "+	
				nadMPTemplateDaysDto.getTplDays() +"\n"+ 
				"Day Schedule Day Value:"+
				nadMPTemplateDaysDto.getTplDay()+"\n"+
				"Day Schedule Code:"+
				nadMPTemplateDaysDto.getTplCode()+"\n"+
				"Day Schedule Course Frequency:"+
				nadMPTemplateDaysDto.getTpleCrsFre()+"\n"+
				"Day Schedule Avail:"+
				nadMPTemplateDaysDto.getTplAvl()+"\n"+
				"Day Schhedule Public:"+
				nadMPTemplateDaysDto.getTplPub()+"\n"+
				"Total Energy: "+
				nadMPTemplateDaysDto.getTplTEn() +"\n"+ 
				"Total Fat: "+
				nadMPTemplateDaysDto.getTplTFa() +"\n"+ 
				"Total Ch: "+
				nadMPTemplateDaysDto.getTplTCh() +"\n"+ 
				"Total Protein: "+
				nadMPTemplateDaysDto.getTplTPr() +"\n"+ 
				"Total Sodium: "+
				nadMPTemplateDaysDto.getTplTSod() +"\n"+
				"Total Sugar: "+
				nadMPTemplateDaysDto.getTplTSug() +"\n"+
				"MPT Relation ID: "+
				nadMPTemplateDaysDto.getTplId() +"\n"
				 );				
			System.out.println("---------------------------------------------- ");

			vh.handleErrors(result);
			model.addAttribute("nadMPTemplateDaysDto", nadMPTemplateDaysDto);
			model.addAttribute("nadMealPlanTemplateDetails_byId",nadMealPlanTemplateDetails_byId);
			model.addAttribute("parentId",parentId);
			model.addAttribute("showNadDayScheduleCreateModal", true); // Add flag to show the modal
			return "nadMPTemplate_Details";
		}
		
		System.out.println("---------------------------------------------- ");
		System.out.println("Controller Layer -> NadMPTemplateDaySchedule -> POST -> PASS ->"+ 
				nadMPTemplateDaysDto.getId() +"\n"+
				"Day Schedule Name: "+	
				nadMPTemplateDaysDto.getTplDays() +"\n"+ 
				"Day Schedule Day Value:"+
				nadMPTemplateDaysDto.getTplDay()+"\n"+
				"Day Schedule Code:"+
				nadMPTemplateDaysDto.getTplCode()+"\n"+
				"Day Schedule Course Frequency:"+
				nadMPTemplateDaysDto.getTpleCrsFre()+"\n"+
				"Day Schedule Avail:"+
				nadMPTemplateDaysDto.getTplAvl()+"\n"+
				"Day Schhedule Public:"+
				nadMPTemplateDaysDto.getTplPub()+"\n"+
				"Total Energy: "+
				nadMPTemplateDaysDto.getTplTEn() +"\n"+ 
				"Total Fat: "+
				nadMPTemplateDaysDto.getTplTFa() +"\n"+ 
				"Total Ch: "+
				nadMPTemplateDaysDto.getTplTCh() +"\n"+ 
				"Total Protein: "+
				nadMPTemplateDaysDto.getTplTPr() +"\n"+ 
				"Total Sodium: "+
				nadMPTemplateDaysDto.getTplTSod() +"\n"+
				"Total Sugar: "+
				nadMPTemplateDaysDto.getTplTSug() +"\n"+
				"MPT Relation ID: "+
				nadMPTemplateDaysDto.getTplId() +"\n"
			 );				
		System.out.println("---------------------------------------------- ");
		
		nad_mp_templ_days_s.createNadMPTemplateDays(nadMPTemplateDaysDto);
		model.addAttribute("id", parentId);
		ra.addFlashAttribute("message", flashyMpds+" details saved successfully !!!");
		return "redirect:/nadMPTemplateDetails/"+parentId;
	}
	
	
	//MODAL -> UPDATE -> FORM -> PUT -> Update KNG NAD Meal Plan Template by id
	@PutMapping("/updateById")
	public String updateNadMPTemplateDtoDetails(@RequestParam("id") Long childid,
										 @PathVariable("id") Long parentId,
										 @Valid @ModelAttribute("nadMPTemplateDaysDto") NadMPTemplateDaysDto nadMPTemplateDaysDto,
										 BindingResult result,
										 RedirectAttributes ra,
										 Model model)
	{
		if(result.hasErrors())
		{
			//NadMPTemplateDaysDto nadMPDaysDto=nad_mp_templ_days_s.createNadMPTemplateDays(nadMPTemplateDaysDto);
			List<NadMPTemplateDto> nadMealPlanTemplateDetails_byId = nad_mp_templ_s.getNadMealPlanTemplateListSpecificById(parentId);
			System.out.println("---------------------------------------------- ");
			System.out.println("Controller Layer -> NadMPTemplateDaySchedule -> POST -> FAIL ->"+ 
					nadMPTemplateDaysDto.getId() +"\n"+
				"Day Schedule Name: "+	
				nadMPTemplateDaysDto.getTplDays() +"\n"+ 
				"Day Schedule Day Value:"+
				nadMPTemplateDaysDto.getTplDay()+"\n"+
				"Day Schedule Code:"+
				nadMPTemplateDaysDto.getTplCode()+"\n"+
				"Day Schedule Course Frequency:"+
				nadMPTemplateDaysDto.getTpleCrsFre()+"\n"+
				"Day Schedule Avail:"+
				nadMPTemplateDaysDto.getTplAvl()+"\n"+
				"Day Schhedule Public:"+
				nadMPTemplateDaysDto.getTplPub()+"\n"+
				"Total Energy: "+
				nadMPTemplateDaysDto.getTplTEn() +"\n"+ 
				"Total Fat: "+
				nadMPTemplateDaysDto.getTplTFa() +"\n"+ 
				"Total Ch: "+
				nadMPTemplateDaysDto.getTplTCh() +"\n"+ 
				"Total Protein: "+
				nadMPTemplateDaysDto.getTplTPr() +"\n"+ 
				"Total Sodium: "+
				nadMPTemplateDaysDto.getTplTSod() +"\n"+
				"Total Sugar: "+
				nadMPTemplateDaysDto.getTplTSug() +"\n"+
				"MPT Relation ID: "+
				nadMPTemplateDaysDto.getTplId() +"\n"
				 );				
			System.out.println("---------------------------------------------- ");

			vh.handleErrors(result);
			model.addAttribute("nadMPTemplateDaysDto", nadMPTemplateDaysDto);
			model.addAttribute("nadMealPlanTemplateDetails_byId",nadMealPlanTemplateDetails_byId);
			model.addAttribute("parentId",parentId);
			model.addAttribute("showNadDayScheduleEditModal", true); // Add flag to show the modal
			return "nadMPTemplate_Details";
		}

		System.out.println("---------------------------------------------- ");
		System.out.println("Controller Layer -> NadMPTemplateDaySchedule -> PUT -> FAIL ->"+ 
				nadMPTemplateDaysDto.getId() +" "+
			"Day Schedule Name: "+	
			nadMPTemplateDaysDto.getTplDays() +" "+ 
			"Day Schedule Day Value:"+
			nadMPTemplateDaysDto.getTplDay()+" "+
			"Day Schedule Code:"+
			nadMPTemplateDaysDto.getTplCode()+" "+
			"Day Schedule Course Frequency:"+
			nadMPTemplateDaysDto.getTpleCrsFre()+" "+
			"Day Schedule Avail:"+
			nadMPTemplateDaysDto.getTplAvl()+" "+
			"Day Schhedule Public:"+
			nadMPTemplateDaysDto.getTplPub()+" "+
			"Total Energy: "+
			nadMPTemplateDaysDto.getTplTEn() +" "+ 
			"Total Fat: "+
			nadMPTemplateDaysDto.getTplTFa() +" "+ 
			"Total Ch: "+
			nadMPTemplateDaysDto.getTplTCh() +" "+ 
			"Total Protein: "+
			nadMPTemplateDaysDto.getTplTPr() +" "+ 
			"Total Sodium: "+
			nadMPTemplateDaysDto.getTplTSod() +" "+
			"Total Sugar: "+
			nadMPTemplateDaysDto.getTplTSug() +" "
			 );				
		System.out.println("---------------------------------------------- ");
		
		nad_mp_templ_days_s.updateNadMPTemplateDaysById(childid,nadMPTemplateDaysDto);
		ra.addFlashAttribute("message", flashyMpds+" details with CHILD ID="+childid+",PARENT ID="+parentId+"  updated successfully !!!");
		return "redirect:/nadMPTemplateDetails/"+parentId;
	}
	
	
	//Delete the KNG Nad Day Schedule by id
	@GetMapping("/deleteById")
	public String deleteNadMealPlanDailyScheduleById(@RequestParam("id") Long childId,
													@PathVariable("id") Long parentId,
													RedirectAttributes ra)
	{
		try 
		{
			nad_mp_templ_days_s.deletNadMPTemplateDaysById(childId);
			ra.addFlashAttribute("message",flashyMpds+" Day Schedule details with CHILD ID="+childId+", PARENT ID="+parentId+"  deleted sucessfully.");
		}
		catch(ResourceNotFoundException ex)
		{
			ra.addFlashAttribute("message", ex.getMessage());
		}
		return "redirect:/nadMPTemplateDetails/"+parentId;	
	}
	
	
	
	
	
}
