package com.itacademy.jd2.lg.ms.web.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.itacademy.jd2.lg.ms.dao.dbmodel.Account;
import com.itacademy.jd2.lg.ms.services.IAccountService;

@Controller
public class AccountController {
	@Autowired
	private IAccountService accountService;

	@RequestMapping(value = "viewAccount")
	public ModelAndView viewOperator(Model model) {
		Map<String, List<Account>> viewModel = new HashMap<String, List<Account>>();
		viewModel.put("accounts", accountService.getAll());
		return new ModelAndView("accounts.list", viewModel);
	}

}