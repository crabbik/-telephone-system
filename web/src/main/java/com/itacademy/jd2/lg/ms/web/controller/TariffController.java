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

import com.itacademy.jd2.lg.ms.dao.dbmodel.Tariff;
import com.itacademy.jd2.lg.ms.dao.dbmodel.Tariff_;
import com.itacademy.jd2.lg.ms.dao.filter.TariffFilter;
import com.itacademy.jd2.lg.ms.services.ITariffService;
import com.itacademy.jd2.lg.ms.web.converter.TariffFromDTOConverter;
import com.itacademy.jd2.lg.ms.web.converter.TariffToDTOConverter;
import com.itacademy.jd2.lg.ms.web.dto.TariffDTO;
import com.itacademy.jd2.lg.ms.web.util.ListModel;
import com.itacademy.jd2.lg.ms.web.util.SortModel;

@Controller
@RequestMapping(value = "/tariff")
public class TariffController {

	private static final String LOCAL_LIST_MODEL_NAME = "tariffListModel";

	@Autowired
	private ITariffService tariffService;

	@Autowired
	private TariffToDTOConverter toDTOConverter;

	@Autowired
	private TariffFromDTOConverter fromDTOConverter;

	@RequestMapping(method = RequestMethod.GET)
	public ModelAndView viewList(final HttpServletRequest req,
			@RequestParam(name = "sort", required = false) final String sort,
			@RequestParam(name = "page", required = false) final Integer pageNumber) {

		ListModel<TariffDTO> listModel;
		if (req.getSession().getAttribute(LOCAL_LIST_MODEL_NAME) == null) {
			listModel = new ListModel<>();
			listModel.setSort(new SortModel("id"));
			req.getSession().setAttribute(LOCAL_LIST_MODEL_NAME, listModel);
		} else {
			listModel = (ListModel<TariffDTO>) req.getSession().getAttribute(LOCAL_LIST_MODEL_NAME);
		}
		req.getSession().setAttribute(ListModel.SESSION_ATTR_NAME, listModel);

		listModel.setSort(sort);
		listModel.setPage(pageNumber);

		final TariffFilter tariffFilter = buildFilter(listModel);

		final List<Tariff> currentPageList = tariffService.getAll(tariffFilter);
		listModel.setList(currentPageList.stream().map(toDTOConverter).collect(Collectors.toList()));
		listModel.setTotalCount(tariffService.getCount(tariffFilter));

		final ModelAndView mv = new ModelAndView("tariff.list");
		return mv;
	}

	private TariffFilter buildFilter(ListModel<TariffDTO> listModel) {

		final SortModel sortModel = listModel.getSort();
		final int offset = listModel.getItemsPerPage() * (listModel.getPage() - 1);

		final TariffFilter tariffFilter = new TariffFilter();
		tariffFilter.setLimit(listModel.getItemsPerPage());
		tariffFilter.setOffset(offset);
		tariffFilter.setSortOrder(sortModel.isAscending());

		SingularAttribute sortAttribute;
		switch (sortModel.getColumn()) {
		case "id":

			sortAttribute = Tariff_.id;
			break;
		case "name":
			sortAttribute = Tariff_.name;
			break;
		case "deleted":
			sortAttribute = Tariff_.deleted;
			break;
		case "created":
			sortAttribute = Tariff_.created;
			break;
		case "modified":
			sortAttribute = Tariff_.modified;
			break;
		default:
			throw new IllegalArgumentException("unsupported sort property:" + sortModel.getColumn());
		}
		tariffFilter.setSortProperty(sortAttribute);
		return tariffFilter;
	}

	@RequestMapping(value = "/add", method = RequestMethod.GET)
	public ModelAndView showForm() {
		return new ModelAndView("tariff.edit", "tariffForm", new TariffDTO());
	}

	@RequestMapping(method = RequestMethod.POST)
	public String save(@Valid @ModelAttribute("tariffForm") final TariffDTO tariffForm, final BindingResult result) {
		if (result.hasErrors()) {
			return "tariff.edit";
		} else {
			final Tariff tariff = fromDTOConverter.apply(tariffForm);
			tariffService.save(tariff);
			return "redirect:/tariff";
		}
	}

	@RequestMapping(value = "/{id}/delete", method = RequestMethod.GET)
	public String delete(@PathVariable(name = "id", required = true) final Integer id) {
		tariffService.remove(id);
		return "redirect:/tariff";
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public ModelAndView viewDetails(@PathVariable(name = "id", required = true) final Integer id) {
		final TariffDTO dto = toDTOConverter.apply(tariffService.get(id));
		final HashMap<String, Object> hashMap = new HashMap<>();
		hashMap.put("tariffForm", dto);
		hashMap.put("readonly", true);
		return new ModelAndView("tariff.edit", hashMap);
	}

	@RequestMapping(value = "/{id}/edit", method = RequestMethod.GET)
	public ModelAndView edit(@PathVariable(name = "id", required = true) final Integer id) {
		final TariffDTO dto = toDTOConverter.apply(tariffService.get(id));
		return new ModelAndView("tariff.edit", "tariffForm", dto);
	}
}