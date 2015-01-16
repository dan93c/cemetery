package ro.immortals.controller;

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

import ro.immortals.model.GraveRequest;

@Controller
@RequestMapping("/graveRequest")
public class GraveRequestController extends MainController {

	@Autowired
	@Qualifier(GRAVE_REQUEST_VALIDATOR)
	private Validator graveRequestValidator;

	@InitBinder(GRAVE_REQUEST)
	private void initBinder(WebDataBinder binder) {
		binder.setValidator(graveRequestValidator);
	}

	@RequestMapping(value = { "/cereri/{page}" }, method = RequestMethod.GET)
	public ModelAndView graveRequestsRegister(@PathVariable Integer page,
			@RequestParam(value = ORDER, required = false) String order,
			@RequestParam(value = SEARCH, required = false) String search,
			HttpServletRequest request) {
		ModelAndView modelAndView = new ModelAndView(GRAVE_REQUEST_REGISTER_JSP);
		order = getOrder(order, request);
		search = getSearch(search, request);
		Integer recordsPerPage = DEFAULT_NR_OF_RECORDS;
		Integer nrOfRecords = graveRequestService.getAllSearchBySize(search);
		Integer nrOfPages = (int) Math.ceil(nrOfRecords * 1.0 / recordsPerPage);
		page = setPagination(modelAndView, page, nrOfPages);
		request.getSession(false).setAttribute(SELECT_NR_OF_RECORDS,
				recordsPerPage);
		modelAndView.addObject(ORDER, order);
		modelAndView.addObject(SEARCH, search);
		modelAndView.addObject(GRAVE_REQUESTS, graveRequestService
				.getAllByPageOrderBySearch(order, search, (page - 1)
						* recordsPerPage, recordsPerPage));
		return modelAndView;

	}

	@RequestMapping(value = "/add", method = RequestMethod.GET)
	public ModelAndView add(@ModelAttribute GraveRequest graveRequest) {

		ModelAndView modelAndView = new ModelAndView(ADD_GRAVE_REQUEST_JSP);
		if (graveRequest == null) {
			modelAndView.addObject(GRAVE_REQUEST, new GraveRequest());
		} else {
			modelAndView.addObject(GRAVE_REQUEST, graveRequest);
		}
		return modelAndView;
	}

	@RequestMapping(value = "/add", method = RequestMethod.POST)
	public ModelAndView doAdd(
			@ModelAttribute @Validated GraveRequest graveRequest,
			BindingResult bindingResult, HttpServletRequest request) {
		if (bindingResult.hasErrors()) {
			return add(graveRequest);
		}
		String username = request.getUserPrincipal().getName();
		Integer errorCode = graveRequestService.add(graveRequest, username);
		if (errorCode == 1) {
			ModelAndView modelAndView = new ModelAndView(ADD_GRAVE_REQUEST_JSP);
			modelAndView.addObject(GRAVE_REQUEST, graveRequest);

			modelAndView.addObject(ERROR_MESSAGE, messageSource.getMessage(
					"message.graveRequest.already.exists",
					new Object[] { graveRequest.getNrInfocet() },
					Locale.getDefault()));

			return modelAndView;
		}
		return graveRequestsRegister(1, null, null, request);
	}

	@RequestMapping(value = "/edit/{id}", method = RequestMethod.GET)
	public ModelAndView edit(@PathVariable Integer id) {
		ModelAndView modelAndView = new ModelAndView(EDIT_GRAVE_REQUEST_JSP);
		modelAndView.addObject(GRAVE_REQUEST, graveRequestService.getById(id));
		return modelAndView;
	}

	@RequestMapping(value = "/edit", method = RequestMethod.POST)
	public ModelAndView doEdit(
			@ModelAttribute @Validated GraveRequest graveRequest,
			BindingResult bindingResult, HttpServletRequest request) {
		if (bindingResult.hasErrors()) {
			ModelAndView modelAndView = new ModelAndView(EDIT_GRAVE_REQUEST_JSP);
			modelAndView.addObject(GRAVE_REQUEST, graveRequest);
			return modelAndView;
		}
		String username = request.getUserPrincipal().getName();
		graveRequestService.update(graveRequest, username);
		return graveRequestsRegister(1, null, null, request);
	}
}
