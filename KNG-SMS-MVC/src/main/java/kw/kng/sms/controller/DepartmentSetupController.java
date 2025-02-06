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
import kw.kng.sms.dto.DepartmentSetupDto;
import kw.kng.sms.errorhandler.ValidationErrorHandler;
import kw.kng.sms.exceptions.ResourceNotFoundException;
import kw.kng.sms.service.DepartmentService;

@Controller
@RequestMapping("/departmentSetup")
public class DepartmentSetupController 
{
	// ----------------------------------------------------------------------------------------------------------
						// Constructor Injection
	
	private DepartmentService ds;
	private ValidationErrorHandler vh;

	public DepartmentSetupController(DepartmentService ds,ValidationErrorHandler vh) 
	{
		this.ds = ds;
		this.vh = vh;
	}
	
	// ----------------------------------------------------------------------------------------------------------

		@Value("${flashy.department}")
		private String flashyDepartment;

	// ----------------------------------------------------------------------------------------------------------

		
		//Get the entire KNG DEPARTMENT List
		@GetMapping("/list")
		public String getDepartmentList(Model model)
		{
			List<DepartmentSetupDto> departmentList=ds.getDepartmentListAll();
			
			System.out.println("Controller Layer -> Department List ->" + departmentList);
			
			model.addAttribute("departmentDto", new DepartmentSetupDto());
			model.addAttribute("departmentList",departmentList);
			return "departmentList";
		}
		
		
		//MODAL -> EDIT -> GET -> the KNG Department details based on the generated id
		@GetMapping("/editById")
		@ResponseBody
		public ResponseEntity<DepartmentSetupDto> editNadClinicById(@RequestParam Long id, 
															  RedirectAttributes ra)
		{
			 try 
			 {
				 DepartmentSetupDto departmentSetupDto = ds.getDepartmentById(id);
			     return ResponseEntity.ok(departmentSetupDto);
			 }
			catch(ResourceNotFoundException ex)
			{
				ra.addFlashAttribute("message", ex.getMessage());
				return ResponseEntity.notFound().build();
			}
		}
		
		//MODAL -> CREATE -> FORM -> POST -> Create a new KNG Department
		@PostMapping("/create")
		public String createClinicDetails(@Valid @ModelAttribute("clinicDto") DepartmentSetupDto departmentSetupDto,
											 BindingResult result,
											 RedirectAttributes ra,
											 Model model)
		{
			if(result.hasErrors())
			{
				System.out.println("---------------------------------------------- ");
				System.out.println("Controller Layer -> DepartmentSetup -> POST -> FAIL ->" + 
						departmentSetupDto.getId() +" "+ 
						departmentSetupDto.getDname() +" "+ 
						departmentSetupDto.getDnamea() +" "+
						departmentSetupDto.getStat()
				);
				System.out.println("---------------------------------------------- ");

				model.addAttribute("departmentSetupDto", departmentSetupDto);
				model.addAttribute("showDepartmentSetupCreateModal", true); // Add flag to show the modal
				vh.handleErrors(result);
				return "clinicSetup";
			}
			
			System.out.println("---------------------------------------------- ");
			System.out.println("Controller Layer -> DepartmentSetup -> POST -> PASS -> " + 
					departmentSetupDto.getId() +" "+ 
					departmentSetupDto.getDname() +" "+ 
					departmentSetupDto.getDnamea() +" "+
					departmentSetupDto.getStat()
			);
			System.out.println("---------------------------------------------- ");
			
			ds.createDepartment(departmentSetupDto);
			ra.addFlashAttribute("message", flashyDepartment+" details saved successfully !!!");
			return "redirect:/departmentSetup/list";
		}
		
		//MODAL -> UPDATE -> FORM -> PUT -> Update KNG Department by id
		@PutMapping("/updateById")
		public String updateSmsClinicDetails(@RequestParam("id") Long id,
											 @Valid @ModelAttribute("departmentDto") DepartmentSetupDto departmentSetupDto,
											 BindingResult result,
											 RedirectAttributes ra,
											 Model model)
		{
			if(result.hasErrors())
			{
				System.out.println("---------------------------------------------- ");
				System.out.println("Controller Layer -> DepartmentSetup -> PUT -> FAIL ->" + 
						departmentSetupDto.getId() +" "+ 
						departmentSetupDto.getDname() +" "+ 
						departmentSetupDto.getDnamea() +" "+
						departmentSetupDto.getStat()
				);
				System.out.println("---------------------------------------------- ");
				
				model.addAttribute("departmentSetupDto", departmentSetupDto);
				model.addAttribute("showDepartmentSetupEditModal", true); // Add flag to show the modal
				vh.handleErrors(result);
				return "clinicSetup";
			}

			System.out.println("---------------------------------------------- ");
			System.out.println("Controller Layer -> DepartmentSetup -> PUT -> PASS -> " + 
					departmentSetupDto.getId() +" "+ 
					departmentSetupDto.getDname() +" "+ 
					departmentSetupDto.getDnamea() +" "+
					departmentSetupDto.getStat()
			);
			System.out.println("---------------------------------------------- ");
			
			ds.updateDepartmentById(id,departmentSetupDto);
			ra.addFlashAttribute("message", flashyDepartment+" details with id="+id+" updated successfully !!!");
			return "redirect:/departmentSetup/list";
		}
		
		//Delete the KNG DEPARTMENT by id
		@GetMapping("/deleteById")
		public String deleteDepartmentById(@RequestParam("id") Long id,RedirectAttributes ra)
		{
			try 
			{
				ds.deletDepartmentById(id);
				ra.addFlashAttribute("message",flashyDepartment+" details with id="+id+" deleted sucessfully.");
			}
			catch(ResourceNotFoundException ex)
			{
				ra.addFlashAttribute("message", ex.getMessage());
			}
			return "redirect:/departmentSetup/list";
			
		}	
		
		
		
		
		
		
		
		
		
		

}
