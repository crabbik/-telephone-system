package com.itacademy.jd2.lg.ms.web.controller;

import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.itacademy.jd2.lg.ms.dao.dbmodel.Account;
import com.itacademy.jd2.lg.ms.services.IAccountService;
import com.itacademy.jd2.lg.ms.web.converter.AccountFromDTOConverter;
import com.itacademy.jd2.lg.ms.web.converter.AccountToDTOConverter;
import com.itacademy.jd2.lg.ms.web.dto.AccountDTO;
import com.itacademy.jd2.lg.ms.web.util.ListModel;
import com.itacademy.jd2.lg.ms.web.util.SortModel;

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
	public ModelAndView viewList(final HttpServletRequest req,
			@RequestParam(name = "sort", required = false) final String sort,
			@RequestParam(name = "page", required = false) final Integer pageNumber) {

		ListModel<AccountDTO> listModel;
		if (req.getSession().getAttribute(ListModel.SESSION_ATTR_NAME) == null) {
			listModel = new ListModel<>();
			listModel.setSort(new SortModel("id"));
			req.getSession().setAttribute(ListModel.SESSION_ATTR_NAME, listModel);
		} else {
			listModel = (ListModel<AccountDTO>) req.getSession().getAttribute(ListModel.SESSION_ATTR_NAME);
		}

		listModel.setSort(sort);
		listModel.setPage(pageNumber);

		final int offset = listModel.getItemsPerPage() * (listModel.getPage() - 1);
		final SortModel sortModel = listModel.getSort();
		final List<Account> currentPageList = accountService.getAll(sortModel.getColumn(), sortModel.isAscending(),
				listModel.getItemsPerPage(), offset);
		listModel.setList(currentPageList.stream().map(toDTOConverter).collect(Collectors.toList()));
		listModel.setTotalCount(accountService.getCount());

		final ModelAndView mv = new ModelAndView("account.list");
		return mv;
	}

	@RequestMapping(value = "/add", method = RequestMethod.GET)
	public ModelAndView showForm() {
		return new ModelAndView("account.edit", "accountForm", new AccountDTO());
	}

	@RequestMapping(method = RequestMethod.POST)
	public String save(@Valid @ModelAttribute("accountForm") final AccountDTO coverForm, final BindingResult result) {
		if (result.hasErrors()) {
			return "account.edit";
		} else {
			final Account account = fromDTOConverter.apply(coverForm);
			accountService.save(account);
			return "redirect:/account";
		}
	}

	@RequestMapping(value = "/{id}/delete", method = RequestMethod.GET)
	public String delete(@PathVariable(name = "id", required = true) final Integer id) {
		accountService.remove(id);
		return "redirect:/account";
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public ModelAndView viewDetails(@PathVariable(name = "id", required = true) final Integer id) {
		final AccountDTO dto = toDTOConverter.apply(accountService.get(id));
		final HashMap<String, Object> hashMap = new HashMap<>();
		hashMap.put("accountForm", dto);
		hashMap.put("readonly", true);
		return new ModelAndView("account.edit", hashMap);
	}

	@RequestMapping(value = "/{id}/edit", method = RequestMethod.GET)
	public ModelAndView edit(@PathVariable(name = "id", required = true) final Integer id) {
		final AccountDTO dto = toDTOConverter.apply(accountService.get(id));
		return new ModelAndView("account.edit", "accountForm", dto);
	}
}