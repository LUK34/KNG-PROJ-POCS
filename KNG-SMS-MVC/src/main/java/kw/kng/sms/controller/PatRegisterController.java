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

import kw.kng.sms.dto.DepartmentSetupDto;
import kw.kng.sms.dto.PatRegisterDto;
import kw.kng.sms.errorhandler.ValidationErrorHandler;
import kw.kng.sms.exceptions.ResourceNotFoundException;
import kw.kng.sms.service.PatRegService;

@Controller
@RequestMapping("/patRegister")
public class PatRegisterController 
{
	// ----------------------------------------------------------------------------------------------------------
	// Constructor Injection

	private PatRegService ps;
	private ValidationErrorHandler vh;

	public PatRegisterController(PatRegService ds,ValidationErrorHandler vh) 
	{
		this.ps = ps;
		this.vh = vh;
	}

	// ----------------------------------------------------------------------------------------------------------

	@Value("${flashy.register}")
	private String flashyRegister;

// ----------------------------------------------------------------------------------------------------------

//Get the entire KNG DEPARTMENT List
	@GetMapping("/list")
	public String getPatientRegisterList(Model model) 
	{
		List<PatRegisterDto> patRegisterList = ps.getPatientListAll();

		System.out.println("Controller Layer -> Department List ->" + patRegisterList);

		model.addAttribute("patRegisterDto", new PatRegisterDto());
		model.addAttribute("patRegisterList", patRegisterList);
		return "patRegisterList";
	}

//MODAL -> EDIT -> GET -> the KNG Department details based on the generated id
	@GetMapping("/editById")
	@ResponseBody
	public ResponseEntity<PatRegisterDto> editPatRegisterById(@RequestParam Long id, RedirectAttributes ra) 
	{
		try
		{
			PatRegisterDto patRegisterDto = ps.getPatientById(id);
			return ResponseEntity.ok(patRegisterDto);
		}
		catch (ResourceNotFoundException ex) 
		{
			ra.addFlashAttribute("message", ex.getMessage());
			return ResponseEntity.notFound().build();
		}
	}

//MODAL -> CREATE -> FORM -> POST -> Create a new KNG Department
	@PostMapping("/create")
	public String createPatRegisterDetails(@Valid @ModelAttribute("patRegisterDto") PatRegisterDto patRegisterDto,
			BindingResult result, RedirectAttributes ra, Model model)
	{
		if (result.hasErrors()) 
		{
			model.addAttribute("patRegisterDto", patRegisterDto);
			model.addAttribute("showPatRegisterCreateModal", true); // Add flag to show the modal
			vh.handleErrors(result);
			return "clinicSetup";
		}


		ps.createPatient(patRegisterDto);
		ra.addFlashAttribute("message", flashyRegister + " details saved successfully !!!");
		return "redirect:/patRegister/list";
	}

//MODAL -> UPDATE -> FORM -> PUT -> Update KNG Department by id
	@PutMapping("/updateById")
	public String updatePatRegisterDetails(@RequestParam("id") Long id,
			@Valid @ModelAttribute("patRegisterDto") PatRegisterDto patRegisterDto, 
			BindingResult result,
			RedirectAttributes ra, Model model) 
	{
		if (result.hasErrors()) 
		{
			model.addAttribute("patRegisterDto", patRegisterDto);
			model.addAttribute("showPatRegisterEditModal", true); // Add flag to show the modal
			vh.handleErrors(result);
			return "clinicSetup";
		}
		
		ps.updatePatientById(id, patRegisterDto);
		ra.addFlashAttribute("message", flashyRegister + " details with id=" + id + " updated successfully !!!");
		return "redirect:/patRegister/list";
	}

//Delete the KNG DEPARTMENT by id
	@GetMapping("/deleteById")
	public String deleteDepartmentById(@RequestParam("id") Long id, RedirectAttributes ra) 
	{
		try 
		{
			ps.deletPatientById(id);
			ra.addFlashAttribute("message", flashyRegister + " details with id=" + id + " deleted sucessfully.");
		} 
		catch (ResourceNotFoundException ex) 
		{
			ra.addFlashAttribute("message", ex.getMessage());
		}
		return "redirect:/patRegister/list";

	}

}
