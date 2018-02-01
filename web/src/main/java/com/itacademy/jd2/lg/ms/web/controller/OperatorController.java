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

import com.itacademy.jd2.lg.ms.dao.dbmodel.Operator;
import com.itacademy.jd2.lg.ms.dao.dbmodel.Operator_;
import com.itacademy.jd2.lg.ms.dao.filter.OperatorFilter;
import com.itacademy.jd2.lg.ms.services.IOperatorService;
import com.itacademy.jd2.lg.ms.web.converter.OperatorFromDTOConverter;
import com.itacademy.jd2.lg.ms.web.converter.OperatorToDTOConverter;
import com.itacademy.jd2.lg.ms.web.dto.OperatorDTO;
import com.itacademy.jd2.lg.ms.web.util.ListModel;
import com.itacademy.jd2.lg.ms.web.util.SortModel;

@Controller
@RequestMapping(value = "/operator")
public class OperatorController {

	private static final String LOCAL_LIST_MODEL_NAME = "operatorListModel";
	@Autowired
	private IOperatorService operatorService;

	@Autowired
	private OperatorToDTOConverter toDTOConverter;

	@Autowired
	private OperatorFromDTOConverter fromDTOConverter;

	@RequestMapping(method = RequestMethod.GET)
	public ModelAndView viewList(final HttpServletRequest req,
			@RequestParam(name = "sort", required = false) final String sort,
			@RequestParam(name = "page", required = false) final Integer pageNumber) {

		ListModel<OperatorDTO> listModel;
		if (req.getSession().getAttribute(LOCAL_LIST_MODEL_NAME) == null) {
			listModel = new ListModel<>();
			listModel.setSort(new SortModel("id"));
			req.getSession().setAttribute(LOCAL_LIST_MODEL_NAME, listModel);
		} else {
			listModel = (ListModel<OperatorDTO>) req.getSession().getAttribute(LOCAL_LIST_MODEL_NAME);
		}
		req.getSession().setAttribute(ListModel.SESSION_ATTR_NAME, listModel);

		listModel.setSort(sort);
		listModel.setPage(pageNumber);

		OperatorFilter operatorFilter = buildFilter(listModel);

		final List<Operator> currentPageList = operatorService.getAll(operatorFilter);
		listModel.setList(currentPageList.stream().map(toDTOConverter).collect(Collectors.toList()));
		listModel.setTotalCount(operatorService.getCount(operatorFilter));

		final ModelAndView mv = new ModelAndView("operator.list");
		return mv;
	}

	private OperatorFilter buildFilter(ListModel<OperatorDTO> listModel) {

		SortModel sortModel = listModel.getSort();
		final int offset = listModel.getItemsPerPage() * (listModel.getPage() - 1);

		OperatorFilter operatorFilter = new OperatorFilter();
		operatorFilter.setLimit(listModel.getItemsPerPage());
		operatorFilter.setOffset(offset);
		operatorFilter.setSortOrder(sortModel.isAscending());

		SingularAttribute sortAttribute;
		switch (sortModel.getColumn()) {
		case "id":

			sortAttribute = Operator_.id;
			break;
		case "title":
			sortAttribute = Operator_.title;
			break;
		case "deleted":
			sortAttribute = Operator_.deleted;
			break;
		case "created":
			sortAttribute = Operator_.created;
			break;
		case "modified":
			sortAttribute = Operator_.modified;
			break;
		default:
			throw new IllegalArgumentException("unsupported sort property:" + sortModel.getColumn());
		}
		operatorFilter.setSortProperty(sortAttribute);
		return operatorFilter;
	}

	@RequestMapping(value = "/add", method = RequestMethod.GET)
	public ModelAndView showForm() {
		return new ModelAndView("operator.edit", "operatorForm", new OperatorDTO());
	}

	@RequestMapping(method = RequestMethod.POST)
	public String save(@Valid @ModelAttribute("operatorForm") final OperatorDTO operatorForm,
			final BindingResult result) {
		if (result.hasErrors()) {
			return "operator.edit";
		} else {
			final Operator operator = fromDTOConverter.apply(operatorForm);
			operatorService.save(operator);
			return "redirect:/operator";
		}
	}

	@RequestMapping(value = "/{id}/delete", method = RequestMethod.GET)
	public String delete(@PathVariable(name = "id", required = true) final Integer id) {
		operatorService.remove(id);
		return "redirect:/operator";
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public ModelAndView viewDetails(@PathVariable(name = "id", required = true) final Integer id) {
		final OperatorDTO dto = toDTOConverter.apply(operatorService.get(id));
		final HashMap<String, Object> hashMap = new HashMap<>();
		hashMap.put("operatorForm", dto);
		hashMap.put("readonly", true);
		return new ModelAndView("operator.edit", hashMap);
	}

	@RequestMapping(value = "/{id}/edit", method = RequestMethod.GET)
	public ModelAndView edit(@PathVariable(name = "id", required = true) final Integer id) {
		final OperatorDTO dto = toDTOConverter.apply(operatorService.get(id));
		return new ModelAndView("operator.edit", "operatorForm", dto);
	}
}