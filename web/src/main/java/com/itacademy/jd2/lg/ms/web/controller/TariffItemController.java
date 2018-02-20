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

import com.itacademy.jd2.lg.ms.dao.dbmodel.TariffItem;
import com.itacademy.jd2.lg.ms.dao.dbmodel.TariffItem_;
import com.itacademy.jd2.lg.ms.dao.filter.TariffItemFilter;
import com.itacademy.jd2.lg.ms.services.ITariffItemService;
import com.itacademy.jd2.lg.ms.web.converter.TariffItemFromDTOConverter;
import com.itacademy.jd2.lg.ms.web.converter.TariffItemToDTOConverter;
import com.itacademy.jd2.lg.ms.web.dto.TariffItemDTO;
import com.itacademy.jd2.lg.ms.web.util.ListModel;
import com.itacademy.jd2.lg.ms.web.util.SortModel;

@Controller
@RequestMapping(value = "/tariffItem")
public class TariffItemController {

	private static final String LOCAL_LIST_MODEL_NAME = "tariffItemListModel";

	@Autowired
	private ITariffItemService tariffItemService;

	@Autowired
	private TariffItemToDTOConverter toDTOConverter;

	@Autowired
	private TariffItemFromDTOConverter fromDTOConverter;

	@RequestMapping(method = RequestMethod.GET)
	public ModelAndView viewList(final HttpServletRequest req,
			@RequestParam(name = "sort", required = false) final String sort,
			@RequestParam(name = "page", required = false) final Integer pageNumber) {

		ListModel<TariffItemDTO> listModel;
		if (req.getSession().getAttribute(LOCAL_LIST_MODEL_NAME) == null) {
			listModel = new ListModel<>();
			listModel.setSort(new SortModel("id"));
			req.getSession().setAttribute(LOCAL_LIST_MODEL_NAME, listModel);
		} else {
			listModel = (ListModel<TariffItemDTO>) req.getSession().getAttribute(LOCAL_LIST_MODEL_NAME);
		}
		req.getSession().setAttribute(ListModel.SESSION_ATTR_NAME, listModel);

		listModel.setSort(sort);
		listModel.setPage(pageNumber);

		final TariffItemFilter tariffItemFilter = buildFilter(listModel);

		final List<TariffItem> currentPageList = tariffItemService.getAll(tariffItemFilter);
		listModel.setList(currentPageList.stream().map(toDTOConverter).collect(Collectors.toList()));
		listModel.setTotalCount(tariffItemService.getCount(tariffItemFilter));

		final ModelAndView mv = new ModelAndView("tariffItem.list");
		return mv;
	}

	private TariffItemFilter buildFilter(ListModel<TariffItemDTO> listModel) {

		final SortModel sortModel = listModel.getSort();
		final int offset = listModel.getItemsPerPage() * (listModel.getPage() - 1);

		final TariffItemFilter tariffItemFilter = new TariffItemFilter();
		tariffItemFilter.setLimit(listModel.getItemsPerPage());
		tariffItemFilter.setOffset(offset);
		tariffItemFilter.setSortOrder(sortModel.isAscending());

		SingularAttribute sortAttribute;
		switch (sortModel.getColumn()) {
		case "id":

			sortAttribute = TariffItem_.id;
			break;
		case "cost":
			sortAttribute = TariffItem_.cost;
			break;
		case "deleted":
			sortAttribute = TariffItem_.deleted;
			break;
		case "created":
			sortAttribute = TariffItem_.created;
			break;
		case "modified":
			sortAttribute = TariffItem_.modified;
			break;
		default:
			throw new IllegalArgumentException("unsupported sort property:" + sortModel.getColumn());
		}
		tariffItemFilter.setSortProperty(sortAttribute);
		return tariffItemFilter;
	}

	@RequestMapping(value = "/add", method = RequestMethod.GET)
	public ModelAndView showForm() {
		return new ModelAndView("tariffItem.edit", "tariffItemForm", new TariffItemDTO());
	}

	@RequestMapping(method = RequestMethod.POST)
	public String save(@Valid @ModelAttribute("tariffItemForm") final TariffItemDTO tariffItemForm,
			final BindingResult result) {
		if (result.hasErrors()) {
			return "tariffItem.edit";
		} else {
			final TariffItem tariffItem = fromDTOConverter.apply(tariffItemForm);
			tariffItemService.save(tariffItem);
			return "redirect:/tariffItem";
		}
	}

	@RequestMapping(value = "/{id}/delete", method = RequestMethod.GET)
	public String delete(@PathVariable(name = "id", required = true) final Integer id) {
		tariffItemService.remove(id);
		return "redirect:/tariffItem";
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public ModelAndView viewDetails(@PathVariable(name = "id", required = true) final Integer id) {
		final TariffItemDTO dto = toDTOConverter.apply(tariffItemService.get(id));
		final HashMap<String, Object> hashMap = new HashMap<>();
		hashMap.put("tariffItemForm", dto);
		hashMap.put("readonly", true);
		return new ModelAndView("tariffItem.edit", hashMap);
	}

	@RequestMapping(value = "/{id}/edit", method = RequestMethod.GET)
	public ModelAndView edit(@PathVariable(name = "id", required = true) final Integer id) {
		final TariffItemDTO dto = toDTOConverter.apply(tariffItemService.get(id));
		return new ModelAndView("tariffItem.edit", "tariffItemForm", dto);
	}
}