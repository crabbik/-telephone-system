package com.itacademy.jd2.lg.ms.web.controller;

import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

import javax.persistence.metamodel.SingularAttribute;
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
import com.itacademy.jd2.lg.ms.dao.dbmodel.Account_;
import com.itacademy.jd2.lg.ms.dao.filter.AccountFilter;
import com.itacademy.jd2.lg.ms.services.IAccountService;
import com.itacademy.jd2.lg.ms.web.converter.AccountFromDTOConverter;
import com.itacademy.jd2.lg.ms.web.converter.AccountToDTOConverter;
import com.itacademy.jd2.lg.ms.web.dto.AccountDTO;
import com.itacademy.jd2.lg.ms.web.util.ListModel;
import com.itacademy.jd2.lg.ms.web.util.SortModel;

@Controller
@RequestMapping(value = "/account")
public class AccountController {

	private static final String LOCAL_LIST_MODEL_NAME = "accountListModel";

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
		if (req.getSession().getAttribute(LOCAL_LIST_MODEL_NAME) == null) {
			listModel = new ListModel<>();
			listModel.setSort(new SortModel("id"));
			req.getSession().setAttribute(LOCAL_LIST_MODEL_NAME, listModel);
		} else {
			listModel = (ListModel<AccountDTO>) req.getSession().getAttribute(LOCAL_LIST_MODEL_NAME);
		}
		req.getSession().setAttribute(ListModel.SESSION_ATTR_NAME, listModel);

		listModel.setSort(sort);
		listModel.setPage(pageNumber);

		AccountFilter accountFilter = buildFilter(listModel);

		final List<Account> currentPageList = accountService.getAll(accountFilter);
		listModel.setList(currentPageList.stream().map(toDTOConverter).collect(Collectors.toList()));
		listModel.setTotalCount(accountService.getCount(accountFilter));

		final ModelAndView mv = new ModelAndView("account.list");
		return mv;
	}

	private AccountFilter buildFilter(ListModel<AccountDTO> listModel) {

		SortModel sortModel = listModel.getSort();
		final int offset = listModel.getItemsPerPage() * (listModel.getPage() - 1);

		AccountFilter accountFilter = new AccountFilter();
		accountFilter.setLimit(listModel.getItemsPerPage());
		accountFilter.setOffset(offset);
		accountFilter.setSortOrder(sortModel.isAscending());

		SingularAttribute sortAttribute;
		switch (sortModel.getColumn()) {
		case "id":

			sortAttribute = Account_.id;
			break;
		case "email":
			sortAttribute = Account_.email;
			break;
		case "password":
			sortAttribute = Account_.password;
			break;
		case "created":
			sortAttribute = Account_.created;
			break;
		case "modified":
			sortAttribute = Account_.modified;
			break;
		default:
			throw new IllegalArgumentException("unsupported sort property:" + sortModel.getColumn());
		}
		accountFilter.setSortProperty(sortAttribute);
		return accountFilter;
	}

	@RequestMapping(value = "/add", method = RequestMethod.GET)
	public ModelAndView showForm() {
		return new ModelAndView("account.edit", "accountForm", new AccountDTO());
	}

	@RequestMapping(method = RequestMethod.POST)
	public String save(@Valid @ModelAttribute("accountForm") final AccountDTO accountForm, final BindingResult result) {
		if (result.hasErrors()) {
			return "account.edit";
		} else {
			final Account account = fromDTOConverter.apply(accountForm);
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