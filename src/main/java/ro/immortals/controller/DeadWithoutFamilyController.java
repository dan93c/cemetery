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

import ro.immortals.model.DeadWithoutFamily;

@Controller
@RequestMapping("/dead2")
public class DeadWithoutFamilyController extends MainController {

	@Autowired
	@Qualifier(DEAD_WITHOUT_FAMILY_VALIDATOR)
	private Validator deadWithoutFamilyValidator;

	@InitBinder(DEAD_WITHOUT_FAMILY)
	private void initBinder(WebDataBinder binder) {
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
		modelAndView.addObject(DEADS,
				deadWithoutFamilyService.getAllByPageOrderBySearch(order, search, (page - 1) * recordsPerPage, recordsPerPage));
		return modelAndView;
	}

	@RequestMapping(value = "/add", method = RequestMethod.GET)
	public ModelAndView add(@ModelAttribute DeadWithoutFamily deadWithoutFamily) {

		ModelAndView modelAndView = new ModelAndView(ADD_DEAD_WITHOUT_FAMILY_JSP);
		if (deadWithoutFamily == null) {
			modelAndView.addObject(DEAD_WITHOUT_FAMILY, new DeadWithoutFamily());
		} else {
			modelAndView.addObject(DEAD_WITHOUT_FAMILY, deadWithoutFamily);
		}
		modelAndView.addObject(CEMETERIES, cemeteryService.getAll());
		modelAndView.addObject(PLOTS, plotService.getAll());
		modelAndView.addObject(GRAVES, graveService.getAll());
		return modelAndView;
	}

	@RequestMapping(value = "/add", method = RequestMethod.POST)
	public ModelAndView doAdd(@ModelAttribute @Validated DeadWithoutFamily deadWithoutFamily, BindingResult bindingResult,
			@RequestParam(value = "cemeterySelect", required = false) Integer cemeteryId,
			@RequestParam(value = "plotSelect", required = false) Integer plotId, HttpServletRequest request) {
		if (bindingResult.hasErrors()) {
			return add(deadWithoutFamily);
		}
		if (!graveService.checkGraveExistence(deadWithoutFamily.getGrave(), plotId, cemeteryId)) {
			ModelAndView modelAndView = new ModelAndView(ADD_DEAD_WITHOUT_FAMILY_JSP);
			modelAndView.addObject(ERROR_MESSAGE, messageSource.getMessage("message.grave.not.exists",
					new Object[] { deadWithoutFamily.getGrave().getId() }, Locale.getDefault()));
			modelAndView.addObject(DEAD_WITHOUT_FAMILY, deadWithoutFamily);
			modelAndView.addObject(CEMETERIES, cemeteryService.getAll());
			modelAndView.addObject(PLOTS, plotService.getAll());
			modelAndView.addObject(GRAVES, graveService.getAll());
			return modelAndView;
		}
		String username = request.getUserPrincipal().getName();
		deadWithoutFamilyService.add(deadWithoutFamily, username);
		return list(1, null, null, request);
	}

	@RequestMapping(value = "/edit/{id}", method = RequestMethod.GET)
	public ModelAndView edit(@PathVariable Integer id) {
		ModelAndView modelAndView = new ModelAndView(EDIT_DEAD_WITHOUT_FAMILY_JSP);
		modelAndView.addObject(DEAD_WITHOUT_FAMILY, deadWithoutFamilyService.getById(id));
		modelAndView.addObject(CEMETERIES, cemeteryService.getAll());
		modelAndView.addObject(PLOTS, plotService.getAll());
		modelAndView.addObject(GRAVES, graveService.getAll());
		return modelAndView;
	}

	@RequestMapping(value = "/edit", method = RequestMethod.POST)
	public ModelAndView doEdit(@ModelAttribute @Validated DeadWithoutFamily deadWithoutFamily, BindingResult bindingResult,
			@RequestParam(value = "cemeterySelect", required = false) Integer cemeteryId,
			@RequestParam(value = "plotSelect", required = false) Integer plotId, HttpServletRequest request) {
		if (bindingResult.hasErrors()) {
			return edit(deadWithoutFamily.getId());
		}
		if (!graveService.checkGraveExistence(deadWithoutFamily.getGrave(), plotId, cemeteryId)) {
			ModelAndView modelAndView = new ModelAndView(EDIT_DEAD_WITHOUT_FAMILY_JSP);
			modelAndView.addObject(ERROR_MESSAGE, messageSource.getMessage("message.grave.not.exists",
					new Object[] { deadWithoutFamily.getGrave().getId() }, Locale.getDefault()));
			modelAndView.addObject(DEAD_WITHOUT_FAMILY, deadWithoutFamily);
			modelAndView.addObject(CEMETERIES, cemeteryService.getAll());
			modelAndView.addObject(PLOTS, plotService.getAll());
			modelAndView.addObject(GRAVES, graveService.getAll());
			return modelAndView;
		}
		String username = request.getUserPrincipal().getName();
		deadWithoutFamilyService.update(deadWithoutFamily, username);
		return list(1, null, null, request);
	}
}
