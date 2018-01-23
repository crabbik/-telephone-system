package com.itacademy.jd2.lg.ms.web.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.itacademy.jd2.lg.ms.dao.dbmodel.Account;
import com.itacademy.jd2.lg.ms.services.IAccountService;
import com.itacademy.jd2.lg.ms.web.converter.AccountFromDTOConverter;
import com.itacademy.jd2.lg.ms.web.converter.AccountToDTOConverter;
import com.itacademy.jd2.lg.ms.web.dto.AccountDTO;

@Controller
@RequestMapping(value = "/account")
public class AccountController {
	@Autowired
	private IAccountService accountService;

	@Autowired
	private AccountToDTOConverter toDTOConverter;

	@Autowired
	private AccountFromDTOConverter fromDTOConverter;

	@RequestMapping(method = RequestMethod.GET)
	public ModelAndView viewList() {
		final Map<String, List<AccountDTO>> viewModel = new HashMap<String, List<AccountDTO>>();
		final List<Account> dbModels = accountService.getAll();
		final List<AccountDTO> coverDTOs = dbModels.stream().map(toDTOConverter).collect(Collectors.toList());
		viewModel.put("accounts", coverDTOs);
		return new ModelAndView("account.list", viewModel);
	}

	@RequestMapping(value = "/add", method = RequestMethod.GET)
	public ModelAndView showForm() {
		return new ModelAndView("account.edit", "accountForm", new AccountDTO());
	}

	@RequestMapping(method = RequestMethod.POST)
	public String save(@ModelAttribute("accountForm") final AccountDTO dto, final ModelMap model) {
		final Account account = fromDTOConverter.apply(dto);
		accountService.save(account);
		return "redirect:/account";
	}

	@RequestMapping(value = "/{id}/delete", method = RequestMethod.GET)
	public String delete(@PathVariable(name = "id", required = true) final Integer id) {
		accountService.remove(id);
		return "redirect:/account";
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public ModelAndView viewDetails(@PathVariable(name = "id", required = true) final Integer id) {
		final AccountDTO dto = toDTOConverter.apply(accountService.get(id));
		return new ModelAndView("account.details", "account", dto);
	}

	@RequestMapping(value = "/{id}/edit", method = RequestMethod.GET)
	public ModelAndView edit(@PathVariable(name = "id", required = true) final Integer id) {
		final AccountDTO dto = toDTOConverter.apply(accountService.get(id));
		return new ModelAndView("account.edit", "accountForm", dto);
	}
}