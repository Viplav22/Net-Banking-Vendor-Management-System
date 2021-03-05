package com.app.controller;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.app.pojos.Role;
import com.app.pojos.Vendor;
import com.app.service.IVendorService;

@Controller // mandatory
@RequestMapping("/user") // optional
public class UserController {
	// dependency
	@Autowired // autowire=byType
	private IVendorService vendorService;

	public UserController() {
		System.out.println("in ctor of " + getClass().getName() + " " + vendorService);

	}

	@PostConstruct
	public void anyInit() {
		System.out.println("in init of  " + getClass().getName() + " " + vendorService);
	}

	// add request handling method for : showing the login form
	@GetMapping("/login")
	// Key : /user/login: method=get
	// Value : com.app.controller.UserController.showLoginForm
	public String showLoginForm() {
		System.out.println("in show login form ");
		return "/user/login";// Actual view name : /WEB-INF/views/user/login.jsp

	}

	// add a req handling method to process the form
	// Key : /user/login: method=post
	// Value : com.app.controller.UserController.processLoginForm
	@PostMapping("/login") // @PostMapping => @RequestMapping: method=post
	public String processLoginForm(@RequestParam String email, @RequestParam String password, Model modelMap,
			HttpSession hs) {
		System.out.println("in process login form " + email + " " + password);
		try {
			// invoke service layer method for B.L
			Vendor user = vendorService.authenticateUser(email, password);

			// => valid login
			// save validated user details under session scope
			hs.setAttribute("user_details", user);
			hs.setAttribute("message", user.getUserRole() + " ,  " + user.getName() + " logged in successfully...");
			// , check user's role
			if (user.getUserRole().equals(Role.ADMIN)) // admin has logged in
				return "redirect:/admin/list";

			if (user.getUserRole().equals(Role.VENDOR)) // vendor has logged in
				return "redirect:/vendor/details";
			// What will be the next request URL : in case of vendor log in n no cookies
			// http://host:port/day15_spring_boot/vendor/details;jsessionid=4654fhfsfg ,
			// method=GET

		} catch (RuntimeException e) {
			System.out.println("err in controller " + e);// javax.persistence.NoResultException
			// add err mesg as the model attribute
			modelMap.addAttribute("message", "Invalid Login ,Please retry!!!!");
			// => invalid login
			return "/user/login";// V.R --> /WEB-INF/views/user/login.jsp
		}

		return "/vendor/details";// Actual view name : /WEB-INF/views/vendor/details.jsp
	}

	// add request handling method for user's logout
	@GetMapping("/logout")
	public String userLogout(HttpSession session, Model modelMap, HttpServletRequest request,
			HttpServletResponse resp) {
		System.out.println("in user logout ");
		// get user details from session scope n add them under model attr map
		modelMap.addAttribute("details", session.getAttribute("user_details"));
		// invalidate session
		session.invalidate();
		// set refresh header to navigate the clnt to home page(index.jsp) after some
		// delay
		resp.setHeader("refresh", "5;url="+request.getContextPath());
		return "/user/logout";// Actual view name : /WEB-INF/views/user/logout.jsp

	}

}
