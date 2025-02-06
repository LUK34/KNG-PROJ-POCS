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
import kw.kng.nad.errorhandler.ValidationErrorHandler;
import kw.kng.nad.exceptions.ResourceNotFoundException;
import kw.kng.nad.service.NadClinicService;



@Controller
@RequestMapping("/nadClinics")
public class NadClinicController
{
	// ----------------------------------------------------------------------------------------------------------
											// Constructor Injection
	private NadClinicService ns;
	private ValidationErrorHandler vh;

	public NadClinicController(NadClinicService ns,ValidationErrorHandler vh) 
	{
		this.ns = ns;
		this.vh=vh;
	}
	// ----------------------------------------------------------------------------------------------------------
	
	@Value("${flashy.clinic}")
	private String flashyClinic;
	
	
	
	// ----------------------------------------------------------------------------------------------------------
	
	//Get the entire NAD Clinic List
	@GetMapping("/list")
	public String getClinicList(Model model)
	{
		List<NadClinicDto> nadClinicList=ns.getNadClinicListAll();
		
		System.out.println("Controller Layer -> Nad Clinic List ->" +nadClinicList);
		
		model.addAttribute("nadClinicDto", new NadClinicDto());
		model.addAttribute("nadClinicList",nadClinicList);
		return "nadClinics";
	}
	
	
	//MODAL -> EDIT -> GET -> the KNG NAD Clinic details based on the generated id
	@GetMapping("/editById")
	@ResponseBody
	public ResponseEntity<NadClinicDto> editNadClinicById(@RequestParam Long id, 
														  RedirectAttributes ra)
	{
		 try 
		 {
		     NadClinicDto nadClinicDto = ns.getNadClinicById(id);
		     return ResponseEntity.ok(nadClinicDto);
		 }
		catch(ResourceNotFoundException ex)
		{
			ra.addFlashAttribute("message", ex.getMessage());
			return ResponseEntity.notFound().build();
		}
	}
	
	//MODAL -> CREATE -> FORM -> POST -> Create a new KNG NAD Clinic
	@PostMapping("/create")
	public String createNadClinicDetails(@Valid @ModelAttribute("nadClinicDto") NadClinicDto nadClinicDto,
										 BindingResult result,
										 RedirectAttributes ra,
										 Model model)
	{
		if(result.hasErrors())
		{
			System.out.println("---------------------------------------------- ");
			System.out.println("Controller Layer -> NadClinics -> POST -> FAIL ->" + nadClinicDto.getClinicid() +" "+ nadClinicDto.getCname() +" "+ nadClinicDto.getCnamea() +" "+ nadClinicDto.getStat());
			System.out.println("---------------------------------------------- ");

			model.addAttribute("nadClinicDto", nadClinicDto);
			model.addAttribute("showNadClinicCreateModal", true); // Add flag to show the modal
			vh.handleErrors(result);
			return "nadClinics";
		}
		
		System.out.println("---------------------------------------------- ");
		System.out.println("Controller Layer -> NadClinics -> POST -> PASS -> " + nadClinicDto.getClinicid() +" "+ nadClinicDto.getCname() +" "+ nadClinicDto.getCnamea() +" "+ nadClinicDto.getStat());
		System.out.println("---------------------------------------------- ");
		
		ns.createNadClinic(nadClinicDto);
		ra.addFlashAttribute("message", flashyClinic+" details saved successfully !!!");
		return "redirect:/nadClinics/list";
	}
	
	//MODAL -> UPDATE -> FORM -> PUT -> Update KNG NAD Clinic by id
	@PutMapping("/updateById")
	public String updateNadClinicDetails(@RequestParam("id") Long id,
										 @Valid @ModelAttribute("nadClinicDto") NadClinicDto nadClinicDto,
										 BindingResult result,
										 RedirectAttributes ra,
										 Model model)
	{
		if(result.hasErrors())
		{
			System.out.println("---------------------------------------------- ");
			System.out.println("Controller Layer -> NadClinics -> PUT -> FAIL ->" + nadClinicDto.getClinicid() +" "+ nadClinicDto.getCname() +" "+ nadClinicDto.getCnamea() +" "+ nadClinicDto.getStat());
			System.out.println("---------------------------------------------- ");
			
			model.addAttribute("nadClinicDto", nadClinicDto);
			model.addAttribute("showNadClinicEditModal", true); // Add flag to show the modal
			vh.handleErrors(result);
			return "nadClinics";
		}

		System.out.println("---------------------------------------------- ");
		System.out.println("Controller Layer -> NadClinics -> PUT -> PASS -> " + nadClinicDto.getClinicid() +" "+ nadClinicDto.getCname() +" "+ nadClinicDto.getCnamea() +" "+ nadClinicDto.getStat());
		System.out.println("---------------------------------------------- ");
		
		ns.updateNadClinicById(id,nadClinicDto);
		ra.addFlashAttribute("message", flashyClinic+" details with id="+id+" updated successfully !!!");
		return "redirect:/nadClinics/list";
	}
	
	//Delete the KNG Nad Clinic by id
	@GetMapping("/deleteById")
	public String deleteNadClinicById(@RequestParam("id") Long id,RedirectAttributes ra)
	{
		try 
		{
			ns.deletNadClinicDtoById(id);
			ra.addFlashAttribute("message",flashyClinic+" details with id="+id+" deleted sucessfully.");
		}
		catch(ResourceNotFoundException ex)
		{
			ra.addFlashAttribute("message", ex.getMessage());
		}
		return "redirect:/nadClinics/list";
		
	}
	
	

	
	
	
}
