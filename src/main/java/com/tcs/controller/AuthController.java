package com.tcs.controller;

import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.tcs.model.Login;
import com.tcs.repository.LoginRepository;

@Controller
@Profile("DEV")
public class AuthController {
	@Autowired
	LoginValidator validator;
	@Autowired
	LoginRepository loginRepository;
	
	@RequestMapping(value = "/", method=RequestMethod.GET)
	public ModelAndView getLoginPage() {
		return new ModelAndView("login", "login", new Login());
	}
	
	@InitBinder
	private void initBinder(WebDataBinder binder) {
		binder.setValidator(validator);
	}
	@PostMapping("mvcProj/auth/login.html")
	public ModelAndView validateLogin(@ModelAttribute("login") @Validated Login login,BindingResult result) {
		ModelAndView modelAndView = new ModelAndView();
		if(result.hasErrors()) {
			result.getFieldErrors().forEach(e->{
				modelAndView.addObject(e.getField(), e.getDefaultMessage());
				System.out.println(e.getDefaultMessage() + " "+ e.getField());
				
			});
			modelAndView.setViewName("login");
			return modelAndView;
		}
		try {
			if(login.equals(loginRepository.findById(login.getUserName()).get())) {
				System.out.println("success");
			}
			else {
				System.out.println("fail");
			}
		}catch(NoSuchElementException e) {
			System.out.println("fail, username not found");
			e.printStackTrace();
		}
		modelAndView.setViewName("redirect:/");
		return modelAndView;
	}
}
