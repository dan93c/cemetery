package ro.immortals.controller;

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

import ro.immortals.model.DeadWithoutFamily;

@Controller
@RequestMapping("/dead2")
public class DeadWithoutFamilyController extends MainController {

	@Autowired
	@Qualifier(DEAD_WITHOUT_FAMILY_VALIDATOR)
	private Validator deadWithoutFamilyValidator;

	@InitBinder(DEAD_WITHOUT_FAMILY)
	private void initBinder(HttpServletRequest request, WebDataBinder binder) throws Exception {
		binder.setValidator(deadWithoutFamilyValidator);
	}

	@RequestMapping(value = "/list/{page}", method = RequestMethod.GET)
	public ModelAndView list(@PathVariable Integer page, @RequestParam(value = ORDER, required = false) String order,
	        @RequestParam(value = SEARCH, required = false) String search, HttpServletRequest request) {
		ModelAndView modelAndView = new ModelAndView(DEAD_WITHOUT_FAMILY_REGISTER_JSP);
		order = getOrder(order, request);
		search = getSearch(search, request);
		Integer recordsPerPage = DEFAULT_NR_OF_RECORDS;
		Integer nrOfRecords = deadWithoutFamilyService.getAllSearchBySize(search);
		Integer nrOfPages = (int) Math.ceil(nrOfRecords * 1.0 / recordsPerPage);
		page = setPagination(modelAndView, page, nrOfPages);
		request.getSession(false).setAttribute(SELECT_NR_OF_RECORDS, recordsPerPage);
		modelAndView.addObject(ORDER, order);
		modelAndView.addObject(SEARCH, search);
		modelAndView.addObject(DEADS, deadWithoutFamilyService.getAllByPageOrderBySearch(order, search, (page - 1)
		        * recordsPerPage, recordsPerPage));
		return modelAndView;
	}

	@RequestMapping(value = "/add", method = RequestMethod.GET)
	public ModelAndView add(@ModelAttribute DeadWithoutFamily dead) {

		ModelAndView modelAndView = new ModelAndView(ADD_DEAD_WITHOUT_FAMILY_JSP);
		if (dead == null) {
			modelAndView.addObject(DEAD_WITHOUT_FAMILY, new DeadWithoutFamily());
		} else {
			modelAndView.addObject(DEAD_WITHOUT_FAMILY, dead);
		}
		return modelAndView;
	}

	@RequestMapping(value = "/add", method = RequestMethod.POST)
	public ModelAndView doAdd(@ModelAttribute @Validated DeadWithoutFamily dead, BindingResult bindingResult,
	        HttpServletRequest request) {
		if (bindingResult.hasErrors()) {
			return add(dead);
		}
		String username = request.getUserPrincipal().getName();
		deadWithoutFamilyService.add(dead, username);
		return list(1, null, null, request);
	}

	@RequestMapping(value = "/edit/{id}", method = RequestMethod.GET)
	public ModelAndView edit(@PathVariable Integer id) {
		ModelAndView modelAndView = new ModelAndView(EDIT_DEAD_WITHOUT_FAMILY_JSP);
		modelAndView.addObject(DEAD_WITHOUT_FAMILY, deadWithoutFamilyService.getById(id));
		return modelAndView;
	}

	@RequestMapping(value = "/edit", method = RequestMethod.POST)
	public ModelAndView doEdit(@ModelAttribute @Validated DeadWithoutFamily dead, BindingResult bindingResult,
	        HttpServletRequest request) {
		if (bindingResult.hasErrors()) {
			return edit(dead.getId());
		}
		String username = request.getUserPrincipal().getName();
		deadWithoutFamilyService.update(dead, username);
		return list(1, null, null, request);
	}
}
