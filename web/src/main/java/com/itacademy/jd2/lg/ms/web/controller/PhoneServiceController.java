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

import com.itacademy.jd2.lg.ms.dao.dbmodel.PhoneService;
import com.itacademy.jd2.lg.ms.dao.dbmodel.PhoneService_;
import com.itacademy.jd2.lg.ms.dao.filter.ServiceFilter;
import com.itacademy.jd2.lg.ms.services.IPhoneServiceService;
import com.itacademy.jd2.lg.ms.web.converter.PhoneServiceFromDTOConverter;
import com.itacademy.jd2.lg.ms.web.converter.PhoneServiceToDTOConverter;
import com.itacademy.jd2.lg.ms.web.dto.PhoneServiceDTO;
import com.itacademy.jd2.lg.ms.web.util.ListModel;
import com.itacademy.jd2.lg.ms.web.util.SortModel;

@Controller
@RequestMapping(value = "/service")
public class PhoneServiceController {

	private static final String LOCAL_LIST_MODEL_NAME = "serviceListModel";

	@Autowired
	private IPhoneServiceService phoneServiceService;

	@Autowired
	private PhoneServiceToDTOConverter toDTOConverter;

	@Autowired
	private PhoneServiceFromDTOConverter fromDTOConverter;

	@RequestMapping(method = RequestMethod.GET)
	public ModelAndView viewList(final HttpServletRequest req,
			@RequestParam(name = "sort", required = false) final String sort,
			@RequestParam(name = "page", required = false) final Integer pageNumber) {

		ListModel<PhoneServiceDTO> listModel;
		if (req.getSession().getAttribute(LOCAL_LIST_MODEL_NAME) == null) {
			listModel = new ListModel<>();
			listModel.setSort(new SortModel("id"));
			req.getSession().setAttribute(LOCAL_LIST_MODEL_NAME, listModel);
		} else {
			listModel = (ListModel<PhoneServiceDTO>) req.getSession().getAttribute(LOCAL_LIST_MODEL_NAME);
		}
		req.getSession().setAttribute(ListModel.SESSION_ATTR_NAME, listModel);

		listModel.setSort(sort);
		listModel.setPage(pageNumber);

		final ServiceFilter serviceFilter = buildFilter(listModel);

		final List<PhoneService> currentPageList = phoneServiceService.getAll(serviceFilter);
		listModel.setList(currentPageList.stream().map(toDTOConverter).collect(Collectors.toList()));
		listModel.setTotalCount(phoneServiceService.getCount(serviceFilter));

		final ModelAndView mv = new ModelAndView("service.list");
		return mv;
	}

	private ServiceFilter buildFilter(ListModel<PhoneServiceDTO> listModel) {

		final SortModel sortModel = listModel.getSort();
		final int offset = listModel.getItemsPerPage() * (listModel.getPage() - 1);

		final ServiceFilter serviceFilter = new ServiceFilter();
		serviceFilter.setLimit(listModel.getItemsPerPage());
		serviceFilter.setOffset(offset);
		serviceFilter.setSortOrder(sortModel.isAscending());

		SingularAttribute sortAttribute;
		switch (sortModel.getColumn()) {
		case "id":

			sortAttribute = PhoneService_.id;
			break;
		case "type":
			sortAttribute = PhoneService_.type;
			break;
		case "unit":
			sortAttribute = PhoneService_.unit;
			break;
		case "deleted":
			sortAttribute = PhoneService_.deleted;
			break;
		case "created":
			sortAttribute = PhoneService_.created;
			break;
		case "modified":
			sortAttribute = PhoneService_.modified;
			break;
		default:
			throw new IllegalArgumentException("unsupported sort property:" + sortModel.getColumn());
		}
		serviceFilter.setSortProperty(sortAttribute);
		return serviceFilter;
	}

	@RequestMapping(value = "/add", method = RequestMethod.GET)
	public ModelAndView showForm() {
		return new ModelAndView("service.edit", "serviceForm", new PhoneServiceDTO());
	}

	@RequestMapping(method = RequestMethod.POST)
	public String save(@Valid @ModelAttribute("serviceForm") final PhoneServiceDTO serviceForm, final BindingResult result) {
		if (result.hasErrors()) {
			return "service.edit";
		} else {
			final PhoneService service = fromDTOConverter.apply(serviceForm);
			phoneServiceService.save(service);
			return "redirect:/service";
		}
	}

	@RequestMapping(value = "/{id}/delete", method = RequestMethod.GET)
	public String delete(@PathVariable(name = "id", required = true) final Integer id) {
		phoneServiceService.remove(id);
		return "redirect:/service";
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public ModelAndView viewDetails(@PathVariable(name = "id", required = true) final Integer id) {
		final PhoneServiceDTO dto = toDTOConverter.apply(phoneServiceService.get(id));
		final HashMap<String, Object> hashMap = new HashMap<>();
		hashMap.put("serviceForm", dto);
		hashMap.put("readonly", true);
		return new ModelAndView("service.edit", hashMap);
	}

	@RequestMapping(value = "/{id}/edit", method = RequestMethod.GET)
	public ModelAndView edit(@PathVariable(name = "id", required = true) final Integer id) {
		final PhoneServiceDTO dto = toDTOConverter.apply(phoneServiceService.get(id));
		return new ModelAndView("service.edit", "serviceForm", dto);
	}
}