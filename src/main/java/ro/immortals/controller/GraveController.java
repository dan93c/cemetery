package ro.immortals.controller;

import java.util.List;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Validator;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import ro.immortals.model.Grave;

@Controller
@RequestMapping("/grave")
public class GraveController extends MainController {

	@Autowired
	@Qualifier(GRAVE_VALIDATOR)
	private Validator graveValidator;

	@InitBinder(GRAVE)
	private void initBinder(WebDataBinder binder) {
		binder.setValidator(graveValidator);
	}

	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public ModelAndView list() {
		ModelAndView modelAndView = new ModelAndView(LIST_GRAVES_JSP);
		List<Grave> graves = graveService.getAll();
		modelAndView.addObject(GRAVES, graves);
		modelAndView.addObject(CEMETERIES, cemeteryService.getAll());
		modelAndView.addObject(PLOTS, plotService.getAll());
		return modelAndView;
	}

	@RequestMapping(value = { "/morminte/{page}" }, method = RequestMethod.GET)
	public ModelAndView gravesRegister(@PathVariable Integer page,
			@RequestParam(value = ORDER, required = false) String order,
			@RequestParam(value = SEARCH, required = false) String search,
			HttpServletRequest request) {
		ModelAndView modelAndView = new ModelAndView(GRAVE_REGISTER_JSP);
		order = getOrder(order, request);
		search = getSearch(search, request);
		Integer recordsPerPage = DEFAULT_NR_OF_RECORDS;
		Integer nrOfRecords = graveService.getAllSearchBySize(search);
		Integer nrOfPages = (int) Math.ceil(nrOfRecords * 1.0 / recordsPerPage);
		page = setPagination(modelAndView, page, nrOfPages);
		request.getSession(false).setAttribute(SELECT_NR_OF_RECORDS,
				recordsPerPage);
		modelAndView.addObject(ORDER, order);
		modelAndView.addObject(SEARCH, search);
		modelAndView.addObject(GRAVES, graveService.getAllByPageOrderBySearch(
				order, search, (page - 1) * recordsPerPage, recordsPerPage));
		return modelAndView;

	}

	@RequestMapping(value = { "/morminte-monumente/{page}" }, method = RequestMethod.GET)
	public ModelAndView graveMonumentsRegister(@PathVariable Integer page,
			@RequestParam(value = ORDER, required = false) String order,
			@RequestParam(value = SEARCH, required = false) String search,
			HttpServletRequest request) {
		ModelAndView modelAndView = new ModelAndView(
				GRAVE_MONUMENT_REGISTER_JSP);
		order = getOrder(order, request);
		search = getSearch(search, request);
		Integer recordsPerPage = DEFAULT_NR_OF_RECORDS;
		Integer nrOfRecords = graveService.getAllMonumentsSearchBySize(search);
		Integer nrOfPages = (int) Math.ceil(nrOfRecords * 1.0 / recordsPerPage);
		page = setPagination(modelAndView, page, nrOfPages);
		request.getSession(false).setAttribute(SELECT_NR_OF_RECORDS,
				recordsPerPage);
		modelAndView.addObject(ORDER, order);
		modelAndView.addObject(SEARCH, search);
		modelAndView.addObject(GRAVES, graveService
				.getAllMonumentsByPageOrderBySearch(order, search, (page - 1)
						* recordsPerPage, recordsPerPage));
		return modelAndView;

	}

	@RequestMapping(value = "/add", method = RequestMethod.GET)
	public ModelAndView add(@ModelAttribute Grave grave) {

		ModelAndView modelAndView = new ModelAndView(ADD_GRAVE_JSP);
		if (grave == null) {
			modelAndView.addObject(GRAVE, new Grave());
		} else {
			modelAndView.addObject(GRAVE, grave);
		}
		modelAndView.addObject(CEMETERIES, cemeteryService.getAll());
		modelAndView.addObject(PLOTS, plotService.getAll());
		return modelAndView;
	}

	@RequestMapping(value = "/add", method = RequestMethod.POST)
	public ModelAndView doAdd(@ModelAttribute @Validated Grave grave,
			BindingResult bindingResult, HttpServletRequest request) {
		if (bindingResult.hasErrors()) {
			return add(grave);
		}
		String username = request.getUserPrincipal().getName();
		Integer errorCode = graveService.add(grave, username);
		if (errorCode == 1) {
			ModelAndView modelAndView = new ModelAndView(ADD_GRAVE_JSP);
			modelAndView.addObject(GRAVE, grave);
			modelAndView.addObject(CEMETERIES, cemeteryService.getAll());
			modelAndView.addObject(PLOTS, plotService.getAll());
			modelAndView.addObject(ERROR_MESSAGE, messageSource.getMessage(
					"message.grave.already.exists",
					new Object[] { grave.getNrGrave(),
							grave.getPlot().getName() }, Locale.getDefault()));
			return modelAndView;
		}
		return list();
	}

	@RequestMapping(value = "/edit/{id}", method = RequestMethod.GET)
	public ModelAndView edit(@PathVariable Integer id) {
		ModelAndView modelAndView = new ModelAndView(EDIT_GRAVE_JSP);
		modelAndView.addObject(GRAVE, graveService.getById(id));
		modelAndView.addObject(CEMETERIES, cemeteryService.getAll());
		modelAndView.addObject(PLOTS, plotService.getAll());
		return modelAndView;
	}

	@RequestMapping(value = "/edit", method = RequestMethod.POST)
	public ModelAndView doEdit(@ModelAttribute @Validated Grave grave,
			BindingResult bindingResult, HttpServletRequest request) {
		if (bindingResult.hasErrors()) {
			ModelAndView modelAndView = new ModelAndView(EDIT_GRAVE_JSP);
			modelAndView.addObject(GRAVE, grave);
			modelAndView.addObject(CEMETERIES, cemeteryService.getAll());
			modelAndView.addObject(PLOTS, plotService.getAll());
			return modelAndView;
		}
		String username = request.getUserPrincipal().getName();
		graveService.update(grave, username);
		return list();
	}

	@RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
	public ModelAndView delete(@PathVariable Integer id,
			HttpServletRequest request) {
		String username = request.getUserPrincipal().getName();
		graveService.delete(id, username);
		return list();
	}
}
