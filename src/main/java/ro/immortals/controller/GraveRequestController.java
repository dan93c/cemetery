package ro.immortals.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Validator;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/grave")
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
	        @RequestParam(value = SEARCH, required = false) String search, HttpServletRequest request) {
		ModelAndView modelAndView = new ModelAndView(GRAVE_REQUEST_REGISTER_JSP);
		order = getOrder(order, request);
		search = getSearch(search, request);
		Integer recordsPerPage = DEFAULT_NR_OF_RECORDS;
		Integer nrOfRecords = graveRequestService.getAllSearchBySize(search);
		Integer nrOfPages = (int) Math.ceil(nrOfRecords * 1.0 / recordsPerPage);
		page = setPagination(modelAndView, page, nrOfPages);
		request.getSession(false).setAttribute(SELECT_NR_OF_RECORDS, recordsPerPage);
		modelAndView.addObject(ORDER, order);
		modelAndView.addObject(SEARCH, search);
		modelAndView.addObject(GRAVES, graveRequestService.getAllByPageOrderBySearch(order, search, (page - 1)
		        * recordsPerPage, recordsPerPage));
		return modelAndView;

	}
	
}
