package kw.kng.nad.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import kw.kng.nad.dto.NadClinicDto;
import kw.kng.nad.dto.NadKngFoodDBDto;
import kw.kng.nad.errorhandler.ValidationErrorHandler;
import kw.kng.nad.exceptions.ResourceNotFoundException;
import kw.kng.nad.service.NadKngFoodDBService;

@Controller
@RequestMapping("/kngFoodDb")
public class NadKngFoodDBController 
{

	// ----------------------------------------------------------------------------------------------------------
	// Constructor Injection
	private NadKngFoodDBService ns;
	private ValidationErrorHandler vh;

	public NadKngFoodDBController(NadKngFoodDBService ns,ValidationErrorHandler vh) 
	{
		this.ns = ns;
		this.vh=vh;
	}
	// ----------------------------------------------------------------------------------------------------------

	@Value("${flashy.kngfdb}")
	private String flashyFoodDb;

	// ----------------------------------------------------------------------------------------------------------

	//Get the entire NAD KNG FOOD DB List
		@GetMapping("/list")
		public String getNadKngFoodDBList(Model model)
		{
			List<NadKngFoodDBDto> nadKngFoodDBList=ns.getNadKngFoodDBListAll();
			
			System.out.println("Controller Layer -> Nad KNG Food DB List ->" +nadKngFoodDBList);
			
			model.addAttribute("nadKngFoodDBDto", new NadKngFoodDBDto());
			model.addAttribute("nadKngFoodDBList",nadKngFoodDBList);
			return "kngFoodDb";
		}
		
		
		//MODAL -> EDIT -> GET -> the KNG FOOD DB details based on the generated id
		@GetMapping("/editById")
		@ResponseBody
		public ResponseEntity<NadKngFoodDBDto> editNadKngFoodDBById(@RequestParam Long id, 
															  RedirectAttributes ra)
		{
			 try 
			 {
				 NadKngFoodDBDto nadKngFoodDBDto = ns.getNadKngFoodDBById(id);
			     return ResponseEntity.ok(nadKngFoodDBDto);
			 }
			catch(ResourceNotFoundException ex)
			{
				ra.addFlashAttribute("message", ex.getMessage());
				return ResponseEntity.notFound().build();
			}
		}
		
		//MODAL -> CREATE -> FORM -> POST -> Create a new KNG FOOD DB
		@PostMapping("/create")
		public String createNadKngFoodDBDetails(@Valid @ModelAttribute("nadKngFoodDBDto") NadKngFoodDBDto nadKngFoodDBDto,
											 BindingResult result,
											 RedirectAttributes ra,
											 Model model)
		{
			if(result.hasErrors())
			{
				model.addAttribute("nadKngFoodDBDto", nadKngFoodDBDto);
				model.addAttribute("showNadKngFoodDbCreateModal", true); // Add flag to show the modal
				vh.handleErrors(result);
				return "kngFoodDb";
			}
		
			
			ns.createNadKngFoodDB(nadKngFoodDBDto);
			ra.addFlashAttribute("message", flashyFoodDb+" details saved successfully !!!");
			return "redirect:/kngFoodDb/list";
		}
		
		//MODAL -> UPDATE -> FORM -> PUT -> Update KNG FOOD DB by id
		@PutMapping("/updateById")
		public String updateNadKngFoodDBDetails(@RequestParam("id") Long id,
											 @Valid @ModelAttribute("nadKngFoodDBDto") NadKngFoodDBDto nadKngFoodDBDto,
											 BindingResult result,
											 RedirectAttributes ra,
											 Model model)
		{
			if(result.hasErrors())
			{
				model.addAttribute("nadKngFoodDBDto", nadKngFoodDBDto);
				model.addAttribute("showNadKngFoodDBDtoEditModal", true); // Add flag to show the modal
				vh.handleErrors(result);
				return "kngFoodDb";
			}

			ns.updateNadKngFoodDBById(id,nadKngFoodDBDto);
			ra.addFlashAttribute("message", flashyFoodDb+" details with id="+id+" updated successfully !!!");
			return "redirect:/kngFoodDb/list";
		}
		
		//Delete the KNG FOOD DB by id
		@GetMapping("/deleteById")
		public String deleteNadKngFoodDBById(@RequestParam("id") Long id,RedirectAttributes ra)
		{
			try 
			{
				ns.deletNadKngFoodDBById(id);
				ra.addFlashAttribute("message",flashyFoodDb+" details with id="+id+" deleted sucessfully.");
			}
			catch(ResourceNotFoundException ex)
			{
				ra.addFlashAttribute("message", ex.getMessage());
			}
			return "redirect:/kngFoodDb/list";
			
		}
	
	
	
	
	
	
	
	
	
	
	
}
