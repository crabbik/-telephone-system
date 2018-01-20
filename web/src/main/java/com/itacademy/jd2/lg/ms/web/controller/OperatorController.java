package com.itacademy.jd2.lg.ms.web.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.itacademy.jd2.lg.ms.dao.dbmodel.Operator;
import com.itacademy.jd2.lg.ms.services.IOperatorService;

@Controller
public class OperatorController {
	@Autowired
	private IOperatorService operatorService;

	@RequestMapping(value = "viewOperator")
	public ModelAndView viewOperator(Model model) {
		Map<String, List<Operator>> viewModel = new HashMap<String, List<Operator>>();
		viewModel.put("operators", operatorService.getAll());
		return new ModelAndView("operators.list", viewModel);
	}

}
