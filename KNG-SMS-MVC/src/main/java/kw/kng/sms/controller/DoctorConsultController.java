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

import kw.kng.sms.dto.DoctorConsultDto;
import kw.kng.sms.errorhandler.ValidationErrorHandler;
import kw.kng.sms.exceptions.ResourceNotFoundException;
import kw.kng.sms.service.DoctorConsultService;

@Controller
@RequestMapping("/doctorConsult")
public class DoctorConsultController 
{
	
	// ----------------------------------------------------------------------------------------------------------
		// Constructor Injection

		private DoctorConsultService ds;
		private ValidationErrorHandler vh;

		public DoctorConsultController(DoctorConsultService ds,ValidationErrorHandler vh) 
		{
			this.ds = ds;
			this.vh = vh;
		}

		// ----------------------------------------------------------------------------------------------------------

		@Value("${flashy.register}")
		private String flashyRegister;

	// ----------------------------------------------------------------------------------------------------------

	//Get the entire KNG DEPARTMENT List
		@GetMapping("/list")
		public String getDoctorConsultList(Model model) 
		{
			List<DoctorConsultDto> doctorConsultList = ds.getDoctorConsultListAll();

			System.out.println("Controller Layer -> Doctor Consult List ->" + doctorConsultList);

			model.addAttribute("doctorConsultDto", new DoctorConsultDto());
			model.addAttribute("doctorConsultList", doctorConsultList);
			return "doctorConsult";
		}

	//MODAL -> EDIT -> GET -> the KNG Department details based on the generated id
		@GetMapping("/editById")
		@ResponseBody
		public ResponseEntity<DoctorConsultDto> editPatRegisterById(@RequestParam Long id, RedirectAttributes ra) 
		{
			try
			{
				DoctorConsultDto doctorConsultDto = ds.getDoctorConsultById(id);
				return ResponseEntity.ok(doctorConsultDto);
			}
			catch (ResourceNotFoundException ex) 
			{
				ra.addFlashAttribute("message", ex.getMessage());
				return ResponseEntity.notFound().build();
			}
		}

	//MODAL -> CREATE -> FORM -> POST -> Create a new KNG Department
		@PostMapping("/create")
		public String createPatRegisterDetails(@Valid @ModelAttribute("doctorConsultDto") DoctorConsultDto doctorConsultDto,
				BindingResult result, RedirectAttributes ra, Model model)
		{
			if (result.hasErrors()) 
			{
				model.addAttribute("doctorConsultDto", doctorConsultDto);
				model.addAttribute("showDoctorConsultCreateModal", true); // Add flag to show the modal
				vh.handleErrors(result);
				return "doctorConsult";
			}


			ds.createDoctorConsult(doctorConsultDto);
			ra.addFlashAttribute("message", flashyRegister + " details saved successfully !!!");
			return "redirect:/doctorConsult/list";
		}

	//MODAL -> UPDATE -> FORM -> PUT -> Update KNG Department by id
		@PutMapping("/updateById")
		public String updatePatRegisterDetails(@RequestParam("id") Long id,
				@Valid @ModelAttribute("patRegisterDto") DoctorConsultDto doctorConsultDto, 
				BindingResult result,
				RedirectAttributes ra, Model model) 
		{
			if (result.hasErrors()) 
			{
				model.addAttribute("doctorConsultDto", doctorConsultDto);
				model.addAttribute("showDoctorConsultCreateModal", true); // Add flag to show the modal
				vh.handleErrors(result);
				return "doctorConsult";
			}
			
			ds.updateDoctorConsultById(id, doctorConsultDto);
			ra.addFlashAttribute("message", flashyRegister + " details with id=" + id + " updated successfully !!!");
			return "redirect:/doctorConsult/list";
		}

		//Delete the KNG DEPARTMENT by id
		@GetMapping("/deleteById")
		public String deletePatRegisterById(@RequestParam("id") Long id, RedirectAttributes ra) 
		{
			try 
			{
				ds.deletDoctorConsultById(id);
				ra.addFlashAttribute("message", flashyRegister + " details with id=" + id + " deleted sucessfully.");
			} 
			catch (ResourceNotFoundException ex) 
			{
				ra.addFlashAttribute("message", ex.getMessage());
			}
			return "redirect:/patRegister/list";

		}

	

}
