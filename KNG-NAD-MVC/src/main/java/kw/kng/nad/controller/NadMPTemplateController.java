package kw.kng.nad.controller;

import java.util.List;
import java.util.stream.Collectors;

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

import kw.kng.nad.dto.NadMPCategoryDto;
import kw.kng.nad.dto.NadMPTemplateDaysDto;
import kw.kng.nad.dto.NadMPTemplateDto;
import kw.kng.nad.errorhandler.ValidationErrorHandler;
import kw.kng.nad.exceptions.ResourceNotFoundException;
import kw.kng.nad.service.NadMPCategoryService;
import kw.kng.nad.service.NadMPTemplateDaysService;
import kw.kng.nad.service.NadMPTemplateService;

@Controller
@RequestMapping("/nadMPTemplate")
public class NadMPTemplateController 
{
	// ----------------------------------------------------------------------------------------------------------
				// Constructor Injection
		private NadMPTemplateService nad_mp_templ_s; //Meal Plan Template service
		private NadMPCategoryService nad_mp_cat_s;	//Meal Plan Category service
		private NadMPTemplateDaysService nad_mp_templ_days_s; //Meal Plan Template Day Service
		private ValidationErrorHandler vh;

		public NadMPTemplateController(NadMPTemplateService nad_mp_templ_s,NadMPCategoryService nad_mp_cat_s, NadMPTemplateDaysService nad_mp_templ_days_s, ValidationErrorHandler vh) 
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


		//Get the entire NAD Meal Plan Template List
		@GetMapping("/list")
		public String getNadMealPlanTemplateList(Model model)
		{
			List<NadMPTemplateDto> nadMealPlanTemplateList = nad_mp_templ_s.getNadMealPlanTemplateListAll();
			List<NadMPCategoryDto> nadMealPlanCategoryList=nad_mp_cat_s.getNadClinicListAll_nameAsc();
			List<Long> s= nadMealPlanTemplateList.stream().map(NadMPTemplateDto::getTplCat).collect(Collectors.toList());
			System.out.println("Catgeory value: " + s);
			
			model.addAttribute("nadMPTemplateDto", new NadMPTemplateDto());
			model.addAttribute("nadMealPlanTemplateList",nadMealPlanTemplateList);
			model.addAttribute("nadMealPlanCategoryList",nadMealPlanCategoryList);
			return "nadMPTemplate";
		}
		
		
		//MODAL -> EDIT -> GET -> the KNG NAD Meal Plan Template details based on the generated id
		@GetMapping("/editById")
		@ResponseBody
		public ResponseEntity<NadMPTemplateDto> editNadMealPlanTemplateById(@RequestParam Long id, 
															  RedirectAttributes ra)
		{
			 try 
			 {
				 NadMPTemplateDto nadMPTemplateDto = nad_mp_templ_s.getNadMealPlanTemplateById(id);
				 System.out.println("---------------------------------------------- ");
				 System.out.println("Controller Layer -> NadMPTemplate get by id-> GET -> PASS -> "+
							nadMPTemplateDto.getId() +" "+
							"Meal Template Name: "+	
							nadMPTemplateDto.getTplName() +" "+ 
							"Meal Template Code: "+
							nadMPTemplateDto.getTplCode() +" "+ 
							"Number of days: "+
							nadMPTemplateDto.getTpleCrsFre() +" "+ 
							"Availability: "+
							nadMPTemplateDto.getTplAvl() +" "+ 
							"Public: "+
							nadMPTemplateDto.getTplPub() +" "+ 
							"Objectives: "+
							nadMPTemplateDto.getTplObj() +" "+
							"How to achieve: "+
							nadMPTemplateDto.getTplPlan() +" "+
							"Category: "+
							nadMPTemplateDto.getTplCat()+" "+
							"Total Energy: "+
							nadMPTemplateDto.getTplTEn() +" "+ 
							"Total Fat: "+
							nadMPTemplateDto.getTplTFa() +" "+ 
							"Total Ch: "+
							nadMPTemplateDto.getTplTCh() +" "+ 
							"Total Protein: "+
							nadMPTemplateDto.getTplTPr() +" "+ 
							"Total Sodium: "+
							nadMPTemplateDto.getTplTSod() +" "+
							"Total Sugar: "+
							nadMPTemplateDto.getTplTSug() +" "
							 ); 
				 System.out.println("---------------------------------------------- ");

			     return ResponseEntity.ok(nadMPTemplateDto);
			 }
			catch(ResourceNotFoundException ex)
			{
				ra.addFlashAttribute("message", ex.getMessage());
				return ResponseEntity.notFound().build();
			}
		}
		
		//MODAL -> CREATE -> FORM -> POST -> Create a new KNG NAD Meal Plan Template
		@PostMapping("/create")
		public String createNadMealPlanTemplateDetails(@Valid @ModelAttribute("nadMPTemplateDto") NadMPTemplateDto nadMPTemplateDto,
											 BindingResult result,
											 RedirectAttributes ra,
											 Model model)
		{
			if(result.hasErrors())
			{
				List<NadMPCategoryDto> nadMealPlanCategoryList=nad_mp_cat_s.getNadClinicListAll_nameAsc();
				System.out.println("---------------------------------------------- ");
				System.out.println("Controller Layer -> NadMPTemplate -> POST -> FAIL ->"+ 
					nadMPTemplateDto.getId() +" "+
					"Meal Template Name: "+	
					nadMPTemplateDto.getTplName() +" "+ 
					"Meal Template Code: "+
					nadMPTemplateDto.getTplCode() +" "+ 
					"Number of days: "+
					nadMPTemplateDto.getTpleCrsFre() +" "+ 
					"Availability: "+
					nadMPTemplateDto.getTplAvl() +" "+ 
					"Public: "+
					nadMPTemplateDto.getTplPub() +" "+ 
					"Objectives: "+
					nadMPTemplateDto.getTplObj() +" "+
					"How to achieve: "+
					nadMPTemplateDto.getTplPlan() +" "+
					"Category: "+
					nadMPTemplateDto.getTplCat()+" "+
					"Total Energy: "+
					nadMPTemplateDto.getTplTEn() +" "+ 
					"Total Fat: "+
					nadMPTemplateDto.getTplTFa() +" "+ 
					"Total Ch: "+
					nadMPTemplateDto.getTplTCh() +" "+ 
					"Total Protein: "+
					nadMPTemplateDto.getTplTPr() +" "+ 
					"Total Sodium: "+
					nadMPTemplateDto.getTplTSod() +" "+
					"Total Sugar: "+
					nadMPTemplateDto.getTplTSug() +" "
					 );				
				System.out.println("---------------------------------------------- ");

				vh.handleErrors(result);
				model.addAttribute("nadMPTemplateDto", nadMPTemplateDto);
				model.addAttribute("nadMealPlanCategoryList",nadMealPlanCategoryList);
				model.addAttribute("showNadMealPlanTemplateCreateModal", true); // Add flag to show the modal
				return "nadMPTemplate";
			}
			
			System.out.println("---------------------------------------------- ");
			System.out.println("Controller Layer -> NadMPTemplate -> POST -> PASS -> "+
				nadMPTemplateDto.getId() +" "+nadMPTemplateDto.getTplName() +" "+ nadMPTemplateDto.getTplCode() +" "+ 
				nadMPTemplateDto.getTpleCrsFre() +" "+ nadMPTemplateDto.getTplAvl() +" "+ nadMPTemplateDto.getTplPub() +" "+ nadMPTemplateDto.getTplObj() +" "+ nadMPTemplateDto.getTplPlan() +" "+
				nadMPTemplateDto.getTplCat()+" "+
				nadMPTemplateDto.getTplTEn() +" "+ nadMPTemplateDto.getTplTFa() +" "+ nadMPTemplateDto.getTplTCh() +" "+ nadMPTemplateDto.getTplTPr() +" "+ nadMPTemplateDto.getTplTSod() +" "+
				nadMPTemplateDto.getTplTSug());
			System.out.println("---------------------------------------------- ");
			
			nad_mp_templ_s.createNadMealPlanTemplate(nadMPTemplateDto);
			ra.addFlashAttribute("message", flashyMpt+" details saved successfully !!!");
			return "redirect:/nadMPTemplate/list";
		}
		
		//MODAL -> UPDATE -> FORM -> PUT -> Update KNG NAD Meal Plan Template by id
		@PutMapping("/updateById")
		public String updateNadMPTemplateDtoDetails(@RequestParam("id") Long id,
											 @Valid @ModelAttribute("nadMPTemplateDto") NadMPTemplateDto nadMPTemplateDto,
											 BindingResult result,
											 RedirectAttributes ra,
											 Model model)
		{
			if(result.hasErrors())
			{
				List<NadMPCategoryDto> nadMealPlanCategoryList=nad_mp_cat_s.getNadClinicListAll_nameAsc();
				System.out.println("---------------------------------------------- ");
				System.out.println("Controller Layer -> NadMPTemplate -> PUT -> FAIL ->" + 
						nadMPTemplateDto.getId() +" "+
						"Meal Template Name: "+	
						nadMPTemplateDto.getTplName() +" "+ 
						"Meal Template Code: "+
						nadMPTemplateDto.getTplCode() +" "+ 
						"Number of days: "+
						nadMPTemplateDto.getTpleCrsFre() +" "+ 
						"Availability: "+
						nadMPTemplateDto.getTplAvl() +" "+ 
						"Public: "+
						nadMPTemplateDto.getTplPub() +" "+ 
						"Objectives: "+
						nadMPTemplateDto.getTplObj() +" "+
						"How to achieve: "+
						nadMPTemplateDto.getTplPlan() +" "+
						"Category: "+
						nadMPTemplateDto.getTplCat()+" "+
						"Total Energy: "+
						nadMPTemplateDto.getTplTEn() +" "+ 
						"Total Fat: "+
						nadMPTemplateDto.getTplTFa() +" "+ 
						"Total Ch: "+
						nadMPTemplateDto.getTplTCh() +" "+ 
						"Total Protein: "+
						nadMPTemplateDto.getTplTPr() +" "+ 
						"Total Sodium: "+
						nadMPTemplateDto.getTplTSod() +" "+
						"Total Sugar: "+
						nadMPTemplateDto.getTplTSug()
						);
				System.out.println("---------------------------------------------- ");
				
				vh.handleErrors(result);
				model.addAttribute("nadMPTemplateDto", nadMPTemplateDto);
				model.addAttribute("showNadMealPlanTemplateEditModal", true); // Add flag to show the modal
				model.addAttribute("nadMealPlanCategoryList",nadMealPlanCategoryList);
				return "nadMPTemplate";
			}

			System.out.println("---------------------------------------------- ");
			System.out.println("Controller Layer -> NadMPTemplate -> PUT -> PASS -> " +
					nadMPTemplateDto.getId() +" "+
					"Meal Template Name: "+	
					nadMPTemplateDto.getTplName() +" "+ 
					"Meal Template Code: "+
					nadMPTemplateDto.getTplCode() +" "+ 
					"Number of days: "+
					nadMPTemplateDto.getTpleCrsFre() +" "+ 
					"Availability: "+
					nadMPTemplateDto.getTplAvl() +" "+ 
					"Public: "+
					nadMPTemplateDto.getTplPub() +" "+ 
					"Objectives: "+
					nadMPTemplateDto.getTplObj() +" "+
					"How to achieve: "+
					nadMPTemplateDto.getTplPlan() +" "+
					"Category: "+
					nadMPTemplateDto.getTplCat()+" "+
					"Total Energy: "+
					nadMPTemplateDto.getTplTEn() +" "+ 
					"Total Fat: "+
					nadMPTemplateDto.getTplTFa() +" "+ 
					"Total Ch: "+
					nadMPTemplateDto.getTplTCh() +" "+ 
					"Total Protein: "+
					nadMPTemplateDto.getTplTPr() +" "+ 
					"Total Sodium: "+
					nadMPTemplateDto.getTplTSod() +" "+
					"Total Sugar: "+
					nadMPTemplateDto.getTplTSug());
			System.out.println("---------------------------------------------- ");
			
			nad_mp_templ_s.updateNadMealPlanTemplateById(id,nadMPTemplateDto);
			ra.addFlashAttribute("message", flashyMpt+" details with id="+id+" updated successfully !!!");
			return "redirect:/nadMPTemplate/list";
		}
		
		//Delete the KNG Nad Meal Plan Template by id
		@GetMapping("/deleteById")
		public String deleteNadMPTemplateDtoById(@RequestParam("id") Long id,RedirectAttributes ra)
		{
			try 
			{
				nad_mp_templ_s.deletNadMealPlanTemplateDtoById(id);
				ra.addFlashAttribute("message",flashyMpt+" details with id="+id+" deleted sucessfully.");
			}
			catch(ResourceNotFoundException ex)
			{
				ra.addFlashAttribute("message", ex.getMessage());
			}
			return "redirect:/nadMPTemplate/list";
			
		}
			
		
		
		

}
