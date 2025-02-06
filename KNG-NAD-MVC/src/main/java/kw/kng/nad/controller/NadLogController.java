package kw.kng.nad.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import kw.kng.nad.dto.NadLogDto;
import kw.kng.nad.errorhandler.ValidationErrorHandler;
import kw.kng.nad.service.NadLogService;

@Controller
@RequestMapping("/nadLogs")
public class NadLogController
{
	// ----------------------------------------------------------------------------------------------------------
			// Constructor Injection
		private NadLogService nls;
		private ValidationErrorHandler vh;
		
		public NadLogController(NadLogService nls,ValidationErrorHandler vh) 
		{
		this.nls = nls;
		this.vh=vh;
		}
// ----------------------------------------------------------------------------------------------------------

		//Get the entire NAD Clinic List
		@GetMapping("/list")
		public String getLogList(Model model)
		{
			List<NadLogDto> nadLogList=nls.getNadLogListAll();
			
			
			model.addAttribute("nadLogDto", new NadLogDto());
			model.addAttribute("nadLogList",nadLogList);
			return "nadLog";
		}
		
}

