package kw.kng.nad.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import kw.kng.nad.dto.NadMPCategoryDto;
import kw.kng.nad.errorhandler.ValidationErrorHandler;
import kw.kng.nad.exceptions.ResourceNotFoundException;
import kw.kng.nad.service.NadMPCategoryService;

@Controller
@RequestMapping("/nadMPCategory")
public class NadMPCategoryController 
{

	// ----------------------------------------------------------------------------------------------------------
				// Constructor Injection
			private NadMPCategoryService nad_mpc_s;
			private ValidationErrorHandler vh;
			
			public NadMPCategoryController(NadMPCategoryService nad_mpc_s,ValidationErrorHandler vh) 
			{
				this.nad_mpc_s = nad_mpc_s;
				this.vh=vh;
			}
	// ----------------------------------------------------------------------------------------------------------

			@Value("${flashy.mpc}")
			private String flashyMpc;

	// ----------------------------------------------------------------------------------------------------------

			//Get the entire NAD Meal Plan Category List
			@GetMapping("/list")
			public String getNadMealPlanCetgoryList(Model model)
			{
				List<NadMPCategoryDto> nadMealPlanCategoryList = nad_mpc_s.getNadMealPlanCategoryListAll();
				model.addAttribute("nadMPCategoryDto", new NadMPCategoryDto());
				model.addAttribute("nadMealPlanCategoryList",nadMealPlanCategoryList);
				return "nadMPCategory";
			}
			
			
			//MODAL -> EDIT -> GET -> the KNG NAD Meal Plan Category details based on the generated id
			@GetMapping("/editById")
			@ResponseBody
			public ResponseEntity<NadMPCategoryDto> editNadMealPlanCategoryById(@RequestParam Long id, 
																  RedirectAttributes ra)
			{
				 try 
				 {
					 NadMPCategoryDto nadMPCategoryDto = nad_mpc_s.getNadMealPlanCategoryById(id);
					 System.out.println("---------------------------------------------- ");
					 System.out.println("Controller Layer -> NadMpCategory get by id-> GET -> PASS -> "+ nadMPCategoryDto.getId() +" "+ nadMPCategoryDto.getCtg() +" "+ nadMPCategoryDto.getCtga() +" "+ nadMPCategoryDto.getStat());
					 System.out.println("---------------------------------------------- ");

				     return ResponseEntity.ok(nadMPCategoryDto);
				 }
				catch(ResourceNotFoundException ex)
				{
					ra.addFlashAttribute("message", ex.getMessage());
					return ResponseEntity.notFound().build();
				}
			}
			
			//MODAL -> CREATE -> FORM -> POST -> Create a new KNG NAD Meal Plan Category
			@PostMapping("/create")
			public String createNadMealPlanCategoryDetails(@Valid @ModelAttribute("nadMPCategoryDto") NadMPCategoryDto nadMPCategoryDto,
												 BindingResult result,
												 RedirectAttributes ra,
												 Model model)
			{
				if(result.hasErrors())
				{
					System.out.println("---------------------------------------------- ");
					System.out.println("Controller Layer -> NadMpCategory -> POST -> FAIL ->"+ nadMPCategoryDto.getCtg() +" "+ nadMPCategoryDto.getCtga() +" "+ nadMPCategoryDto.getStat());
					System.out.println("---------------------------------------------- ");

					model.addAttribute("nadMPCategoryDto", nadMPCategoryDto);
					model.addAttribute("showNadMealPlanCategoryCreateModal", true); // Add flag to show the modal
					vh.handleErrors(result);
					return "nadMPCategory";
				}
				
				System.out.println("---------------------------------------------- ");
				System.out.println("Controller Layer -> NadMpCategory -> POST -> PASS -> "+ nadMPCategoryDto.getCtg() +" "+ nadMPCategoryDto.getCtga() +" "+ nadMPCategoryDto.getStat());
				System.out.println("---------------------------------------------- ");
				
				nad_mpc_s.createNadMealPlanCategory(nadMPCategoryDto);
				ra.addFlashAttribute("message", flashyMpc+" details saved successfully !!!");
				return "redirect:/nadMPCategory/list";
			}
			
			//MODAL -> UPDATE -> FORM -> PUT -> Update KNG NAD Meal Plan Category by id
			@PutMapping("/updateById")
			public String updateNadMPCategoryDtoDetails(@RequestParam("id") Long id,
												 @Valid @ModelAttribute("nadMPCategoryDto") NadMPCategoryDto nadMPCategoryDto,
												 BindingResult result,
												 RedirectAttributes ra,
												 Model model)
			{
				if(result.hasErrors())
				{
					System.out.println("---------------------------------------------- ");
					System.out.println("Controller Layer -> NadMpCategory -> PUT -> FAIL ->" + nadMPCategoryDto.getId() +" "+ nadMPCategoryDto.getCtg() +" "+ nadMPCategoryDto.getCtga() +" "+ nadMPCategoryDto.getStat());
					System.out.println("---------------------------------------------- ");
					
					model.addAttribute("nadMPCategoryDto", nadMPCategoryDto);
					model.addAttribute("showNadMealPlanCategoryEditModal", true); // Add flag to show the modal
					vh.handleErrors(result);
					return "nadMPCategory";
				}

				System.out.println("---------------------------------------------- ");
				System.out.println("Controller Layer -> NadMpCategory -> PUT -> PASS -> " + nadMPCategoryDto.getId() +" "+ nadMPCategoryDto.getCtg() +" "+ nadMPCategoryDto.getCtga() +" "+ nadMPCategoryDto.getStat());
				System.out.println("---------------------------------------------- ");
				
				nad_mpc_s.updateNadMealPlanCategoryById(id,nadMPCategoryDto);
				ra.addFlashAttribute("message", flashyMpc+" details with id="+id+" updated successfully !!!");
				return "redirect:/nadMPCategory/list";
			}
			
			//Delete the KNG Nad Meal Plan Category by id
			@GetMapping("/deleteById")
			public String deleteNadMPCategoryDtoById(@RequestParam("id") Long id, RedirectAttributes ra)
			{
				try 
				{
					nad_mpc_s.deletNadMealPlanCategoryDtoById(id);
					ra.addFlashAttribute("message",flashyMpc+" details with id="+id+" deleted sucessfully.");
				}
				catch(ResourceNotFoundException ex)
				{
					ra.addFlashAttribute("message", ex.getMessage());
					
				}
				return "redirect:/nadMPCategory/list";
			}
			
	
	
}
