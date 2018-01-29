package com.itacademy.jd2.lg.ms.web.controller;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.support.PagedListHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.itacademy.jd2.lg.ms.dao.dbmodel.Operator;
import com.itacademy.jd2.lg.ms.services.IOperatorService;
import com.itacademy.jd2.lg.ms.web.converter.OperatorFromDTOConverter;
import com.itacademy.jd2.lg.ms.web.converter.OperatorToDTOConverter;
import com.itacademy.jd2.lg.ms.web.dto.OperatorDTO;

@Controller
@RequestMapping(value = "/operator")
public class OperatorController {
	@Autowired
	private IOperatorService operatorService;

	@Autowired
	private OperatorToDTOConverter toDTOConverter;

	@Autowired
	private OperatorFromDTOConverter fromDTOConverter;

	@RequestMapping(value = { "/all/{type}", "/all", "" }, method = RequestMethod.GET)
	public ModelAndView viewList(@PathVariable Map<String, String> pathVariablesMap, HttpServletRequest req) {
		/*
		 * final Map<String, List<CoverDTO>> viewModel = new HashMap<String,
		 * List<CoverDTO>>(); final List<Cover> dbModels = coverService.getAll(); final
		 * List<CoverDTO> coverDTOs =
		 * dbModels.stream().map(toDTOConverter).collect(Collectors.toList());
		 * viewModel.put("covers", coverDTOs); return new ModelAndView("cover.list",
		 * viewModel);
		 */

		PagedListHolder<OperatorDTO> listHolder = null;

		String type = pathVariablesMap.get("type");

		if (null == type) {
			// First Request, Return first set of list
			List<OperatorDTO> operatorsList = operatorService.getAll().stream().map(toDTOConverter)
					.collect(Collectors.toList());

			listHolder = new PagedListHolder<OperatorDTO>();
			listHolder.setSource(operatorsList);
			listHolder.setPageSize(2);

			req.getSession().setAttribute("operators", listHolder);

		} else if ("next".equals(type)) {
			// Return next set of list
			listHolder = (PagedListHolder<OperatorDTO>) req.getSession().getAttribute("operators");

			listHolder.nextPage();

		} else if ("prev".equals(type)) {
			// Return previous set of list
			listHolder = (PagedListHolder<OperatorDTO>) req.getSession().getAttribute("operators");

			listHolder.previousPage();

		} else {
			// Return specific index set of list
			System.out.println("type:" + type);

			listHolder = (PagedListHolder<OperatorDTO>) req.getSession().getAttribute("operators");

			int pageNum = Integer.parseInt(type);

			listHolder.setPage(pageNum);
		}

		ModelAndView mv = new ModelAndView("operator.list");

		return mv;
	}

	@RequestMapping(value = "/add", method = RequestMethod.GET)
	public ModelAndView showForm() {
		return new ModelAndView("operator.edit", "operatorForm", new OperatorDTO());
	}

	@RequestMapping(method = RequestMethod.POST)
	public String save(@ModelAttribute("operatorForm") final OperatorDTO dto, final ModelMap model) {
		final Operator account = fromDTOConverter.apply(dto);
		operatorService.save(account);
		return "redirect:/operator";
	}

	@RequestMapping(value = "/{id}/delete", method = RequestMethod.GET)
	public String delete(@PathVariable(name = "id", required = true) final Integer id) {
		operatorService.remove(id);
		return "redirect:/operator";
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public ModelAndView viewDetails(@PathVariable(name = "id", required = true) final Integer id) {
		final OperatorDTO dto = toDTOConverter.apply(operatorService.get(id));
		return new ModelAndView("operator.details", "operator", dto);
	}

	@RequestMapping(value = "/{id}/edit", method = RequestMethod.GET)
	public ModelAndView edit(@PathVariable(name = "id", required = true) final Integer id) {
		final OperatorDTO dto = toDTOConverter.apply(operatorService.get(id));
		return new ModelAndView("operator.edit", "operatorForm", dto);
	}
}