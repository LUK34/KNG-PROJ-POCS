package kw.kng.sms.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainController 
{
	
	@GetMapping("/")
	public String getMainPage()
	{
		return "home";
	}

}
