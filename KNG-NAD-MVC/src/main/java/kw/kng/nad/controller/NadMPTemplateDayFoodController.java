package kw.kng.nad.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import kw.kng.nad.dto.NadMPCategoryDto;
import kw.kng.nad.dto.NadMPTemplateDaysDto;
import kw.kng.nad.dto.NadMPTemplateDto;
import kw.kng.nad.errorhandler.ValidationErrorHandler;
import kw.kng.nad.service.NadMPCategoryService;
import kw.kng.nad.service.NadMPTemplateDayPatternService;
import kw.kng.nad.service.NadMPTemplateDaysService;
import kw.kng.nad.service.NadMPTemplateFoodPatternService;
import kw.kng.nad.service.NadMPTemplateService;

@Controller
@RequestMapping("/nadMPTemplateDayFood/{id}")
public class NadMPTemplateDayFoodController 
{
	// ----------------------------------------------------------------------------------------------------------
			// Constructor Injection
	private NadMPTemplateDayPatternService nad_mp_templ_daypattern_s;
	private NadMPTemplateFoodPatternService nad_mp_templ_foodpattern_s;
	private ValidationErrorHandler vh;
	
	
	public NadMPTemplateDayFoodController(NadMPTemplateDayPatternService nad_mp_templ_daypattern_s,NadMPTemplateFoodPatternService nad_mp_templ_foodpattern_s, ValidationErrorHandler vh) 
	{
	this.nad_mp_templ_daypattern_s = nad_mp_templ_daypattern_s;
	this.nad_mp_templ_foodpattern_s = nad_mp_templ_foodpattern_s;
	this.vh=vh;
	}
	
	// ----------------------------------------------------------------------------------------------------------
	
	@Value("${flashy.mpt.daypattern}")
	private String flashyMpt;
	
	@Value("${flashy.mpt.foodpattern}")
	private String flashyMpds;


	// ----------------------------------------------------------------------------------------------------------

	
	@GetMapping
	public String findNadMPDailyDayFoodPatternDtoById(@PathVariable("id") Long parentId,
										   Model model)
	{
		/*
		System.out.println("Parent id="+parentId);
		System.out.println("Hi there ->0");
		List<NadMPTemplateDto> nadMealPlanTemplateDetails_byId = nad_mp_templ_daypattern_s.()
		List<NadMPTemplateDaysDto> nadMealPlanTemplateDaysList_byId=nad_mp_templ_daypattern_s.getNadMPTemplateDaysListSpecificById(parentId);
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
		*/
		return "nadMPTemplate_Details";
		
	}
	
	
	
}
