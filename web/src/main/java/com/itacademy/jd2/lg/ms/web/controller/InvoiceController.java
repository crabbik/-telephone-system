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

import com.itacademy.jd2.lg.ms.dao.dbmodel.Invoice;
import com.itacademy.jd2.lg.ms.dao.dbmodel.Invoice_;
import com.itacademy.jd2.lg.ms.dao.filter.InvoiceFilter;
import com.itacademy.jd2.lg.ms.services.IInvoiceService;
import com.itacademy.jd2.lg.ms.web.converter.InvoiceFromDTOConverter;
import com.itacademy.jd2.lg.ms.web.converter.InvoiceToDTOConverter;
import com.itacademy.jd2.lg.ms.web.dto.InvoiceDTO;
import com.itacademy.jd2.lg.ms.web.util.ListModel;
import com.itacademy.jd2.lg.ms.web.util.SortModel;

@Controller
@RequestMapping(value = "/invoice")
public class InvoiceController {

	private static final String LOCAL_LIST_MODEL_NAME = "invoiceListModel";

	@Autowired
	private IInvoiceService invoiceService;

	@Autowired
	private InvoiceToDTOConverter toDTOConverter;

	@Autowired
	private InvoiceFromDTOConverter fromDTOConverter;

	@RequestMapping(method = RequestMethod.GET)
	public ModelAndView viewList(final HttpServletRequest req,
			@RequestParam(name = "sort", required = false) final String sort,
			@RequestParam(name = "page", required = false) final Integer pageNumber) {

		ListModel<InvoiceDTO> listModel;
		if (req.getSession().getAttribute(LOCAL_LIST_MODEL_NAME) == null) {
			listModel = new ListModel<>();
			listModel.setSort(new SortModel("id"));
			req.getSession().setAttribute(LOCAL_LIST_MODEL_NAME, listModel);
		} else {
			listModel = (ListModel<InvoiceDTO>) req.getSession().getAttribute(LOCAL_LIST_MODEL_NAME);
		}
		req.getSession().setAttribute(ListModel.SESSION_ATTR_NAME, listModel);

		listModel.setSort(sort);
		listModel.setPage(pageNumber);

		final InvoiceFilter invoiceFilter = buildFilter(listModel);

		final List<Invoice> currentPageList = invoiceService.getAll(invoiceFilter);
		listModel.setList(currentPageList.stream().map(toDTOConverter).collect(Collectors.toList()));
		listModel.setTotalCount(invoiceService.getCount(invoiceFilter));

		final ModelAndView mv = new ModelAndView("invoice.list");
		return mv;
	}

	private InvoiceFilter buildFilter(ListModel<InvoiceDTO> listModel) {

		final SortModel sortModel = listModel.getSort();
		final int offset = listModel.getItemsPerPage() * (listModel.getPage() - 1);

		final InvoiceFilter invoiceFilter = new InvoiceFilter();
		invoiceFilter.setLimit(listModel.getItemsPerPage());
		invoiceFilter.setOffset(offset);
		invoiceFilter.setSortOrder(sortModel.isAscending());

		SingularAttribute sortAttribute;
		switch (sortModel.getColumn()) {
		case "id":

			sortAttribute = Invoice_.id;
			break;
		case "type":
			sortAttribute = Invoice_.type;
			break;
		case "quantity":
			sortAttribute = Invoice_.quantity;
			break;
		case "sum":
			sortAttribute = Invoice_.sum;
			break;
		case "month":
			sortAttribute = Invoice_.month;
			break;
		case "year":
			sortAttribute = Invoice_.year;
			break;
		case "created":
			sortAttribute = Invoice_.created;
			break;
		case "modified":
			sortAttribute = Invoice_.modified;
			break;
		default:
			throw new IllegalArgumentException("unsupported sort property:" + sortModel.getColumn());
		}
		invoiceFilter.setSortProperty(sortAttribute);
		return invoiceFilter;
	}

	@RequestMapping(value = "/add", method = RequestMethod.GET)
	public ModelAndView showForm() {
		return new ModelAndView("invoice.edit", "invoiceForm", new InvoiceDTO());
	}

	@RequestMapping(method = RequestMethod.POST)
	public String save(@Valid @ModelAttribute("invoiceForm") final InvoiceDTO invoiceForm, final BindingResult result) {
		if (result.hasErrors()) {
			return "invoice.edit";
		} else {
			final Invoice invoice = fromDTOConverter.apply(invoiceForm);
			invoiceService.save(invoice);
			return "redirect:/invoice";
		}
	}

	@RequestMapping(value = "/{id}/delete", method = RequestMethod.GET)
	public String delete(@PathVariable(name = "id", required = true) final Integer id) {
		invoiceService.remove(id);
		return "redirect:/invoice";
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public ModelAndView viewDetails(@PathVariable(name = "id", required = true) final Integer id) {
		final InvoiceDTO dto = toDTOConverter.apply(invoiceService.get(id));
		final HashMap<String, Object> hashMap = new HashMap<>();
		hashMap.put("invoiceForm", dto);
		hashMap.put("readonly", true);
		return new ModelAndView("invoice.edit", hashMap);
	}

	@RequestMapping(value = "/{id}/edit", method = RequestMethod.GET)
	public ModelAndView edit(@PathVariable(name = "id", required = true) final Integer id) {
		final InvoiceDTO dto = toDTOConverter.apply(invoiceService.get(id));
		return new ModelAndView("invoice.edit", "invoiceForm", dto);
	}
}