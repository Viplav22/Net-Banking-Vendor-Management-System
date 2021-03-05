package com.app.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.app.dto.AcctType;
import com.app.dto.BankAccount;
import com.app.service.INetbankingService;

@Controller
@RequestMapping("/banking")
public class NetBankingClientController {
	@Autowired
	private INetbankingService netBankingService;

	// add a request method to show a form for netbanking login
	@GetMapping("/login")
	public String showLoginForm() {
		System.out.println("in show login form");
		return "/banking/login";
	}

	// process login form
	@PostMapping("/login")
	public String processLoginForm(@RequestParam long custId, @RequestParam String password,
			RedirectAttributes flashMap, Model map) {
		System.out.println("in process form" + custId + " " + password);
		List<BankAccount> list = netBankingService.authenticateCustomer(custId, password);
		if (list != null) {
			flashMap.addFlashAttribute("accts", list);
			return "redirect:/banking/acct_list";
		}
		map.addAttribute("message", "Invalid Credentials!!!!!");
		return "/banking/login";
	}

	// show list of vendor accounts
	@GetMapping("/acct_list")
	public String showAccountList() {
		System.out.println("in show acct list");
		return "/banking/acct_list";
	}

	// add a new request handling method to show a/c creation form : form binding
	@GetMapping("/create_acct")
	public String showAcCreationFrom(Model map, BankAccount a) {
		System.out.println("in show a/c create " + a);
		// add acct types in model map
		map.addAttribute("acct_types", AcctType.values());
		return "/banking/create_acct";
		// Actual view name : /WEB-INF/views/banking/create_acct.jsp
	}

	// add a new request handling method to create new acct (by calling REST API of
	// a backend)
	@PostMapping("/create_acct")
	public String processCreateAcForm(BankAccount acct, BindingResult result, RedirectAttributes flashMap, Model map) {
		System.out.println("in process a/c create form " + acct);
		System.out.println(acct.getAcctOwner());

		// chk for P.L errors
		if (result.hasErrors()) {
			System.out.println("P.L errros");
			map.addAttribute("message", "A/C creation failed");
			return "/banking/create_acct";
		}
		
		// => no P.L errs, so proceed to B.L : invoke service layer method
		BankAccount account = netBankingService.createAccount(acct);
		if (account == null) {// => REST call failed
			map.addAttribute("message", "A/C creation failed : REST call failure");
			return "/banking/create_acct";
		}
		
		// REST call success
		flashMap.addFlashAttribute("ac_status", "A/C created : " + account);
		return "redirect:/vendor/details";
	}
}