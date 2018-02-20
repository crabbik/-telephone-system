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

import com.itacademy.jd2.lg.ms.dao.dbmodel.PhoneNumber;
import com.itacademy.jd2.lg.ms.dao.dbmodel.PhoneNumber_;
import com.itacademy.jd2.lg.ms.dao.filter.PhoneNumberFilter;
import com.itacademy.jd2.lg.ms.services.IPhoneNumberService;
import com.itacademy.jd2.lg.ms.web.converter.PhoneNumberFromDTOConverter;
import com.itacademy.jd2.lg.ms.web.converter.PhoneNumberToDTOConverter;
import com.itacademy.jd2.lg.ms.web.dto.PhoneNumberDTO;
import com.itacademy.jd2.lg.ms.web.util.ListModel;
import com.itacademy.jd2.lg.ms.web.util.SortModel;

@Controller
@RequestMapping(value = "/phoneNumber")
public class PhoneNumberController {

	private static final String LOCAL_LIST_MODEL_NAME = "phoneNumberListModel";

	@Autowired
	private IPhoneNumberService phoneNumberService;

	@Autowired
	private PhoneNumberToDTOConverter toDTOConverter;

	@Autowired
	private PhoneNumberFromDTOConverter fromDTOConverter;

	@RequestMapping(method = RequestMethod.GET)
	public ModelAndView viewList(final HttpServletRequest req,
			@RequestParam(name = "sort", required = false) final String sort,
			@RequestParam(name = "page", required = false) final Integer pageNumber) {

		ListModel<PhoneNumberDTO> listModel;
		if (req.getSession().getAttribute(LOCAL_LIST_MODEL_NAME) == null) {
			listModel = new ListModel<>();
			listModel.setSort(new SortModel("id"));
			req.getSession().setAttribute(LOCAL_LIST_MODEL_NAME, listModel);
		} else {
			listModel = (ListModel<PhoneNumberDTO>) req.getSession().getAttribute(LOCAL_LIST_MODEL_NAME);
		}
		req.getSession().setAttribute(ListModel.SESSION_ATTR_NAME, listModel);

		listModel.setSort(sort);
		listModel.setPage(pageNumber);

		final PhoneNumberFilter phoneNumberFilter = buildFilter(listModel);

		final List<PhoneNumber> currentPageList = phoneNumberService.getAll(phoneNumberFilter);
		listModel.setList(currentPageList.stream().map(toDTOConverter).collect(Collectors.toList()));
		listModel.setTotalCount(phoneNumberService.getCount(phoneNumberFilter));

		final ModelAndView mv = new ModelAndView("phoneNumber.list");
		return mv;
	}

	private PhoneNumberFilter buildFilter(ListModel<PhoneNumberDTO> listModel) {

		final SortModel sortModel = listModel.getSort();
		final int offset = listModel.getItemsPerPage() * (listModel.getPage() - 1);

		final PhoneNumberFilter phoneNumberFilter = new PhoneNumberFilter();
		phoneNumberFilter.setLimit(listModel.getItemsPerPage());
		phoneNumberFilter.setOffset(offset);
		phoneNumberFilter.setSortOrder(sortModel.isAscending());

		SingularAttribute sortAttribute;
		switch (sortModel.getColumn()) {
		case "id":

			sortAttribute = PhoneNumber_.id;
			break;
		case "number":
			sortAttribute = PhoneNumber_.number;
			break;
		case "created":
			sortAttribute = PhoneNumber_.created;
			break;
		case "modified":
			sortAttribute = PhoneNumber_.modified;
			break;
		default:
			throw new IllegalArgumentException("unsupported sort property:" + sortModel.getColumn());
		}
		phoneNumberFilter.setSortProperty(sortAttribute);
		return phoneNumberFilter;
	}

	@RequestMapping(value = "/add", method = RequestMethod.GET)
	public ModelAndView showForm() {
		return new ModelAndView("phoneNumber.edit", "phoneNumberForm", new PhoneNumberDTO());
	}

	@RequestMapping(method = RequestMethod.POST)
	public String save(@Valid @ModelAttribute("phoneNumber") final PhoneNumberDTO phoneNumberForm,
			final BindingResult result) {
		if (result.hasErrors()) {
			return "phoneNumber.edit";
		} else {
			final PhoneNumber phoneNumber = fromDTOConverter.apply(phoneNumberForm);
			phoneNumberService.save(phoneNumber);
			return "redirect:/phoneNumber";
		}
	}

	@RequestMapping(value = "/{id}/delete", method = RequestMethod.GET)
	public String delete(@PathVariable(name = "id", required = true) final Integer id) {
		phoneNumberService.remove(id);
		return "redirect:/phoneNumber";
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public ModelAndView viewDetails(@PathVariable(name = "id", required = true) final Integer id) {
		final PhoneNumberDTO dto = toDTOConverter.apply(phoneNumberService.get(id));
		final HashMap<String, Object> hashMap = new HashMap<>();
		hashMap.put("phoneNumberForm", dto);
		hashMap.put("readonly", true);
		return new ModelAndView("phoneNumber.edit", hashMap);
	}

	@RequestMapping(value = "/{id}/edit", method = RequestMethod.GET)
	public ModelAndView edit(@PathVariable(name = "id", required = true) final Integer id) {
		final PhoneNumberDTO dto = toDTOConverter.apply(phoneNumberService.get(id));
		return new ModelAndView("phoneNumber.edit", "phoneNumberForm", dto);
	}
}