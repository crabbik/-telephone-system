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

import com.itacademy.jd2.lg.ms.dao.dbmodel.AccountDetails;
import com.itacademy.jd2.lg.ms.dao.dbmodel.AccountDetails_;
import com.itacademy.jd2.lg.ms.dao.filter.AccountDetailsFilter;
import com.itacademy.jd2.lg.ms.services.IAccountDetailsService;
import com.itacademy.jd2.lg.ms.web.converter.AccountDetailsFromDTOConverter;
import com.itacademy.jd2.lg.ms.web.converter.AccountDetailsToDTOConverter;
import com.itacademy.jd2.lg.ms.web.dto.AccountDetailsDTO;
import com.itacademy.jd2.lg.ms.web.util.ListModel;
import com.itacademy.jd2.lg.ms.web.util.SortModel;

@Controller
@RequestMapping(value = "/accountDetails")
public class AccountDetailsController {

	private static final String LOCAL_LIST_MODEL_NAME = "accountDetailsListModel";

	@Autowired
	private IAccountDetailsService accountDetailsService;

	@Autowired
	private AccountDetailsToDTOConverter toDTOConverter;

	@Autowired
	private AccountDetailsFromDTOConverter fromDTOConverter;

	@RequestMapping(method = RequestMethod.GET)
	public ModelAndView viewList(final HttpServletRequest req,
			@RequestParam(name = "sort", required = false) final String sort,
			@RequestParam(name = "page", required = false) final Integer pageNumber) {

		ListModel<AccountDetailsDTO> listModel;
		if (req.getSession().getAttribute(LOCAL_LIST_MODEL_NAME) == null) {
			listModel = new ListModel<>();
			listModel.setSort(new SortModel("id"));
			req.getSession().setAttribute(LOCAL_LIST_MODEL_NAME, listModel);
		} else {
			listModel = (ListModel<AccountDetailsDTO>) req.getSession().getAttribute(LOCAL_LIST_MODEL_NAME);
		}
		req.getSession().setAttribute(ListModel.SESSION_ATTR_NAME, listModel);

		listModel.setSort(sort);
		listModel.setPage(pageNumber);

		final AccountDetailsFilter accountDetailsFilter = buildFilter(listModel);

		final List<AccountDetails> currentPageList = accountDetailsService.getAll(accountDetailsFilter);
		listModel.setList(currentPageList.stream().map(toDTOConverter).collect(Collectors.toList()));
		listModel.setTotalCount(accountDetailsService.getCount(accountDetailsFilter));

		final ModelAndView mv = new ModelAndView("accountDetails.list");
		return mv;
	}

	private AccountDetailsFilter buildFilter(ListModel<AccountDetailsDTO> listModel) {

		final SortModel sortModel = listModel.getSort();
		final int offset = listModel.getItemsPerPage() * (listModel.getPage() - 1);

		final AccountDetailsFilter accountDetailsFilter = new AccountDetailsFilter();
		accountDetailsFilter.setLimit(listModel.getItemsPerPage());
		accountDetailsFilter.setOffset(offset);
		accountDetailsFilter.setSortOrder(sortModel.isAscending());

		SingularAttribute sortAttribute;
		switch (sortModel.getColumn()) {
		case "id":

			sortAttribute = AccountDetails_.id;
			break;
		case "created":
			sortAttribute = AccountDetails_.created;
			break;
		case "modified":
			sortAttribute = AccountDetails_.modified;
			break;
		case "firstName":
			sortAttribute = AccountDetails_.firstName;
			break;
		case "lastName":
			sortAttribute = AccountDetails_.lastName;
			break;
		default:
			throw new IllegalArgumentException("unsupported sort property:" + sortModel.getColumn());
		}
		accountDetailsFilter.setSortProperty(sortAttribute);
		return accountDetailsFilter;
	}

	@RequestMapping(value = "/add", method = RequestMethod.GET)
	public ModelAndView showForm() {
		return new ModelAndView("accountDetails.edit", "accountDetailsForm", new AccountDetailsDTO());
	}

	@RequestMapping(method = RequestMethod.POST)
	public String save(@Valid @ModelAttribute("accountDetailsForm") final AccountDetailsDTO accountDetailsForm,
			final BindingResult result) {
		if (result.hasErrors()) {
			return "accountDetails.edit";
		} else {
			final AccountDetails accountDetails = fromDTOConverter.apply(accountDetailsForm);
			accountDetailsService.save(accountDetails);
			return "redirect:/accountDetails";
		}
	}

	@RequestMapping(value = "/{id}/delete", method = RequestMethod.GET)
	public String delete(@PathVariable(name = "id", required = true) final Integer id) {
		accountDetailsService.remove(id);
		return "redirect:/accountDetails";
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public ModelAndView viewDetails(@PathVariable(name = "id", required = true) final Integer id) {
		final AccountDetailsDTO dto = toDTOConverter.apply(accountDetailsService.get(id));
		final HashMap<String, Object> hashMap = new HashMap<>();
		hashMap.put("accountDetailsForm", dto);
		hashMap.put("readonly", true);
		return new ModelAndView("accountDetails.edit", hashMap);
	}

	@RequestMapping(value = "/{id}/edit", method = RequestMethod.GET)
	public ModelAndView edit(@PathVariable(name = "id", required = true) final Integer id) {
		final AccountDetailsDTO dto = toDTOConverter.apply(accountDetailsService.get(id));
		return new ModelAndView("accountDetails.edit", "accountDetailsForm", dto);
	}
}