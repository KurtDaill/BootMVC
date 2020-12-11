package com.tcs.controller;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@Profile("TESTING")
public class GuestController {
	
	@RequestMapping(value = "/guest", method=RequestMethod.GET)
	public String getLoginPage() {
		return "guest";
	}
}
