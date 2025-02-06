package kw.kng.sms.controller;

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

import kw.kng.sms.dto.ClinicSetupDto;
import kw.kng.sms.errorhandler.ValidationErrorHandler;
import kw.kng.sms.exceptions.ResourceNotFoundException;
import kw.kng.sms.service.ClinicService;

@Controller
@RequestMapping("/clinicSetup")
public class ClinicSetupController
{
	// ----------------------------------------------------------------------------------------------------------
											// Constructor Injection
	private ClinicService cs;
	private ValidationErrorHandler vh;

	public ClinicSetupController(ClinicService cs,ValidationErrorHandler vh) 
	{
		this.cs = cs;
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
		List<ClinicSetupDto> clinicList=cs.getClinicListAll();
		
		System.out.println("Controller Layer -> Clinic List ->" +clinicList);
		
		model.addAttribute("clinicSetupDto", new ClinicSetupDto());
		model.addAttribute("clinicList",clinicList);
		return "smsClinics";
	}
	
	
	//MODAL -> EDIT -> GET -> the KNG NAD Clinic details based on the generated id
	@GetMapping("/editById")
	@ResponseBody
	public ResponseEntity<ClinicSetupDto> editNadClinicById(@RequestParam Long id, 
														  RedirectAttributes ra)
	{
		 try 
		 {
			 ClinicSetupDto clinicSetupDto = cs.getClinicById(id);
		     return ResponseEntity.ok(clinicSetupDto);
		 }
		catch(ResourceNotFoundException ex)
		{
			ra.addFlashAttribute("message", ex.getMessage());
			return ResponseEntity.notFound().build();
		}
	}
	
	//MODAL -> CREATE -> FORM -> POST -> Create a new KNG NAD Clinic
	@PostMapping("/create")
	public String createClinicDetails(@Valid @ModelAttribute("clinicDto") ClinicSetupDto clinicSetupDto,
										 BindingResult result,
										 RedirectAttributes ra,
										 Model model)
	{
		if(result.hasErrors())
		{
			System.out.println("---------------------------------------------- ");
			System.out.println("Controller Layer -> ClinicSetup -> POST -> FAIL ->" + clinicSetupDto.getClinicid() +" "+ clinicSetupDto.getCname() +" "+ clinicSetupDto.getCnamea() +" "+ clinicSetupDto.getStat());
			System.out.println("---------------------------------------------- ");

			model.addAttribute("clinicSetupDto", clinicSetupDto);
			model.addAttribute("showClinicCreateModal", true); // Add flag to show the modal
			vh.handleErrors(result);
			return "clinicSetup";
		}
		
		System.out.println("---------------------------------------------- ");
		System.out.println("Controller Layer -> ClinicSetup -> POST -> PASS -> " + clinicSetupDto.getClinicid() +" "+ clinicSetupDto.getCname() +" "+ clinicSetupDto.getCnamea() +" "+ clinicSetupDto.getStat());
		System.out.println("---------------------------------------------- ");
		
		cs.createSmsClinic(clinicSetupDto);
		ra.addFlashAttribute("message", flashyClinic+" details saved successfully !!!");
		return "redirect:/clinicSetup/list";
	}
	
	//MODAL -> UPDATE -> FORM -> PUT -> Update KNG NAD Clinic by id
	@PutMapping("/updateById")
	public String updateSmsClinicDetails(@RequestParam("id") Long id,
										 @Valid @ModelAttribute("clinicDto") ClinicSetupDto clinicSetupDto,
										 BindingResult result,
										 RedirectAttributes ra,
										 Model model)
	{
		if(result.hasErrors())
		{
			System.out.println("---------------------------------------------- ");
			System.out.println("Controller Layer -> ClinicSetup -> PUT -> FAIL ->" + clinicSetupDto.getClinicid() +" "+ clinicSetupDto.getCname() +" "+ clinicSetupDto.getCnamea() +" "+ clinicSetupDto.getStat());
			System.out.println("---------------------------------------------- ");
			
			model.addAttribute("clinicSetupDto", clinicSetupDto);
			model.addAttribute("showClinicEditModal", true); // Add flag to show the modal
			vh.handleErrors(result);
			return "clinicSetup";
		}

		System.out.println("---------------------------------------------- ");
		System.out.println("Controller Layer -> ClinicSetup -> PUT -> PASS -> " + clinicSetupDto.getClinicid() +" "+ clinicSetupDto.getCname() +" "+ clinicSetupDto.getCnamea() +" "+ clinicSetupDto.getStat());
		System.out.println("---------------------------------------------- ");
		
		cs.updateClinicById(id,clinicSetupDto);
		ra.addFlashAttribute("message", flashyClinic+" details with id="+id+" updated successfully !!!");
		return "redirect:/clinicSetup/list";
	}
	
	//Delete the KNG Nad Clinic by id
	@GetMapping("/deleteById")
	public String deleteClinicById(@RequestParam("id") Long id,RedirectAttributes ra)
	{
		try 
		{
			cs.deletClinicDtoById(id);
			ra.addFlashAttribute("message",flashyClinic+" details with id="+id+" deleted sucessfully.");
		}
		catch(ResourceNotFoundException ex)
		{
			ra.addFlashAttribute("message", ex.getMessage());
		}
		return "redirect:/clinicSetup/list";
		
	}
	
	

	
	
	
}
