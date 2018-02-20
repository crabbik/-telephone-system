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

import com.itacademy.jd2.lg.ms.dao.dbmodel.ServiceHistory;
import com.itacademy.jd2.lg.ms.dao.dbmodel.ServiceHistory_;
import com.itacademy.jd2.lg.ms.dao.filter.ServiceHistoryFilter;
import com.itacademy.jd2.lg.ms.services.IServiceHistoryService;
import com.itacademy.jd2.lg.ms.web.converter.ServiceHistoryFromDTOConverter;
import com.itacademy.jd2.lg.ms.web.converter.ServiceHistoryToDTOConverter;
import com.itacademy.jd2.lg.ms.web.dto.ServiceHistoryDTO;
import com.itacademy.jd2.lg.ms.web.util.ListModel;
import com.itacademy.jd2.lg.ms.web.util.SortModel;

@Controller
@RequestMapping(value = "/serviceHistory")
public class ServiceHistoryController {

	private static final String LOCAL_LIST_MODEL_NAME = "serviceHistoryListModel";

	@Autowired
	private IServiceHistoryService serviceHistoryService;

	@Autowired
	private ServiceHistoryToDTOConverter toDTOConverter;

	@Autowired
	private ServiceHistoryFromDTOConverter fromDTOConverter;

	@RequestMapping(method = RequestMethod.GET)
	public ModelAndView viewList(final HttpServletRequest req,
			@RequestParam(name = "sort", required = false) final String sort,
			@RequestParam(name = "page", required = false) final Integer pageNumber) {

		ListModel<ServiceHistoryDTO> listModel;
		if (req.getSession().getAttribute(LOCAL_LIST_MODEL_NAME) == null) {
			listModel = new ListModel<>();
			listModel.setSort(new SortModel("id"));
			req.getSession().setAttribute(LOCAL_LIST_MODEL_NAME, listModel);
		} else {
			listModel = (ListModel<ServiceHistoryDTO>) req.getSession().getAttribute(LOCAL_LIST_MODEL_NAME);
		}
		req.getSession().setAttribute(ListModel.SESSION_ATTR_NAME, listModel);

		listModel.setSort(sort);
		listModel.setPage(pageNumber);

		final ServiceHistoryFilter serviceHistoryFilter = buildFilter(listModel);

		final List<ServiceHistory> currentPageList = serviceHistoryService.getAll(serviceHistoryFilter);
		listModel.setList(currentPageList.stream().map(toDTOConverter).collect(Collectors.toList()));
		listModel.setTotalCount(serviceHistoryService.getCount(serviceHistoryFilter));

		final ModelAndView mv = new ModelAndView("serviceHistory.list");
		return mv;
	}

	private ServiceHistoryFilter buildFilter(ListModel<ServiceHistoryDTO> listModel) {

		final SortModel sortModel = listModel.getSort();
		final int offset = listModel.getItemsPerPage() * (listModel.getPage() - 1);

		final ServiceHistoryFilter serviceHistoryFilter = new ServiceHistoryFilter();
		serviceHistoryFilter.setLimit(listModel.getItemsPerPage());
		serviceHistoryFilter.setOffset(offset);
		serviceHistoryFilter.setSortOrder(sortModel.isAscending());

		SingularAttribute sortAttribute;
		switch (sortModel.getColumn()) {
		case "id":

			sortAttribute = ServiceHistory_.id;
			break;
		case "data":
			sortAttribute = ServiceHistory_.data;
			break;
		case "quantity":
			sortAttribute = ServiceHistory_.quantity;
			break;
		case "sum":
			sortAttribute = ServiceHistory_.sum;
			break;
		case "created":
			sortAttribute = ServiceHistory_.created;
			break;
		case "modified":
			sortAttribute = ServiceHistory_.modified;
			break;
		default:
			throw new IllegalArgumentException("unsupported sort property:" + sortModel.getColumn());
		}
		serviceHistoryFilter.setSortProperty(sortAttribute);
		return serviceHistoryFilter;
	}

	@RequestMapping(value = "/add", method = RequestMethod.GET)
	public ModelAndView showForm() {
		return new ModelAndView("serviceHistory.edit", "serviceHistoryForm", new ServiceHistoryDTO());
	}

	@RequestMapping(method = RequestMethod.POST)
	public String save(@Valid @ModelAttribute("serviceHistoryForm") final ServiceHistoryDTO serviceHistoryForm,
			final BindingResult result) {
		if (result.hasErrors()) {
			return "serviceHistory.edit";
		} else {
			final ServiceHistory serviceHistory = fromDTOConverter.apply(serviceHistoryForm);
			serviceHistoryService.save(serviceHistory);
			return "redirect:/serviceHistory";
		}
	}

	@RequestMapping(value = "/{id}/delete", method = RequestMethod.GET)
	public String delete(@PathVariable(name = "id", required = true) final Integer id) {
		serviceHistoryService.remove(id);
		return "redirect:/serviceHistory";
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public ModelAndView viewDetails(@PathVariable(name = "id", required = true) final Integer id) {
		final ServiceHistoryDTO dto = toDTOConverter.apply(serviceHistoryService.get(id));
		final HashMap<String, Object> hashMap = new HashMap<>();
		hashMap.put("serviceHistoryForm", dto);
		hashMap.put("readonly", true);
		return new ModelAndView("serviceHistory.edit", hashMap);
	}

	@RequestMapping(value = "/{id}/edit", method = RequestMethod.GET)
	public ModelAndView edit(@PathVariable(name = "id", required = true) final Integer id) {
		final ServiceHistoryDTO dto = toDTOConverter.apply(serviceHistoryService.get(id));
		return new ModelAndView("serviceHistory.edit", "serviceHistoryForm", dto);
	}
}