package com.app.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller // mandatory
@RequestMapping("/vendor") // optional
public class VendorController {
	public VendorController() {
		System.out.println("in ctor of " + getClass().getName());
	}
	//add a req handling method to forward the clnt to the vendor details page
	@GetMapping("/details")
	public String showVendorDetails()
	{
		System.out.println("in show vndr dtls");
		return "/vendor/details";//Actual view name : /WEB-INF/views/vendor/details.jsp
	}
}
