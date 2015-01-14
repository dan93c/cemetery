package ro.immortals.controller;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.propertyeditors.CustomDateEditor;
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

import ro.immortals.model.Dead;

@Controller
@RequestMapping("/dead")
public class DeadController extends MainController {

	@Autowired
	@Qualifier(DEAD_VALIDATOR)
	private Validator deadValidator;

	@InitBinder(DEAD)
	private void initBinder(HttpServletRequest request, WebDataBinder binder) throws Exception {
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm");
		CustomDateEditor editor = new CustomDateEditor(dateFormat, true);
		binder.registerCustomEditor(Date.class, editor);
		binder.setValidator(deadValidator);
	}

	@RequestMapping(value = "/list/{page}", method = RequestMethod.GET)
	public ModelAndView list(@PathVariable Integer page, @RequestParam(value = ORDER, required = false) String order,
			@RequestParam(value = SEARCH, required = false) String search, HttpServletRequest request) {
		ModelAndView modelAndView = new ModelAndView(DEAD_REGISTER_JSP);
		order = getOrder(order, request);
		search = getSearch(search, request);
		Integer recordsPerPage = DEFAULT_NR_OF_RECORDS;
		Integer nrOfRecords = deadService.getAllSearchBySize(search);
		Integer nrOfPages = (int) Math.ceil(nrOfRecords * 1.0 / recordsPerPage);
		page = setPagination(modelAndView, page, nrOfPages);
		request.getSession(false).setAttribute(SELECT_NR_OF_RECORDS, recordsPerPage);
		modelAndView.addObject(ORDER, order);
		modelAndView.addObject(SEARCH, search);
		modelAndView.addObject(DEADS,
				deadService.getAllByPageOrderBySearch(order, search, (page - 1) * recordsPerPage, recordsPerPage));
		return modelAndView;
	}

	@RequestMapping(value = "/inmormantari/{page}", method = RequestMethod.GET)
	public ModelAndView appointmentRegister(@PathVariable Integer page,
			@RequestParam(value = ORDER, required = false) String order,
			@RequestParam(value = SEARCH, required = false) String search, HttpServletRequest request) {
		ModelAndView modelAndView = new ModelAndView(APPOINTMENT_REGISTER_JSP);
		order = getOrder(order, request);
		search = getSearch(search, request);
		Integer recordsPerPage = DEFAULT_NR_OF_RECORDS;
		Integer nrOfRecords = deadService.getAllSearchBySize(search);
		Integer nrOfPages = (int) Math.ceil(nrOfRecords * 1.0 / recordsPerPage);
		page = setPagination(modelAndView, page, nrOfPages);
		request.getSession(false).setAttribute(SELECT_NR_OF_RECORDS, recordsPerPage);
		modelAndView.addObject(ORDER, order);
		modelAndView.addObject(SEARCH, search);
		modelAndView.addObject(DEADS,
				deadService.getAllByPageOrderBySearch(order, search, (page - 1) * recordsPerPage, recordsPerPage));
		return modelAndView;
	}

	@RequestMapping(value = "/add", method = RequestMethod.GET)
	public ModelAndView add(@ModelAttribute Dead dead) {

		ModelAndView modelAndView = new ModelAndView(ADD_DEAD_JSP);
		if (dead == null) {
			modelAndView.addObject(DEAD, new Dead());
		} else {
			modelAndView.addObject(DEAD, dead);
		}
		modelAndView.addObject(CEMETERIES, cemeteryService.getAll());
		modelAndView.addObject(PLOTS, plotService.getAll());
		modelAndView.addObject(GRAVES, graveService.getAll());
		return modelAndView;
	}

	@RequestMapping(value = "/add", method = RequestMethod.POST)
	public ModelAndView doAdd(@ModelAttribute @Validated Dead dead, BindingResult bindingResult,
			@RequestParam(value = "cemeterySelect", required = false) Integer cemeteryId,
			@RequestParam(value = "plotSelect", required = false) Integer plotId, HttpServletRequest request) {
		if (bindingResult.hasErrors()) {
			return add(dead);
		}
		if (!graveService.checkGraveExistence(dead.getGrave(), plotId, cemeteryId)) {
			ModelAndView modelAndView = new ModelAndView(ADD_DEAD_JSP);
			modelAndView.addObject(
					ERROR_MESSAGE,
					messageSource.getMessage("message.grave.not.exists", new Object[] { dead.getGrave().getId() },
							Locale.getDefault()));
			modelAndView.addObject(DEAD, dead);
			modelAndView.addObject(CEMETERIES, cemeteryService.getAll());
			modelAndView.addObject(PLOTS, plotService.getAll());
			modelAndView.addObject(GRAVES, graveService.getAll());
			return modelAndView;
		}
		String username = request.getUserPrincipal().getName();
		Integer errorCode = deadService.add(dead, username);
		if (errorCode == 1) {
			ModelAndView modelAndView = new ModelAndView(ADD_DEAD_JSP);
			modelAndView.addObject(ERROR_MESSAGE,
					messageSource.getMessage("message.dead.already.exists", new Object[] { dead.getId() }, Locale.getDefault()));
			modelAndView.addObject(DEAD, dead);
			modelAndView.addObject(CEMETERIES, cemeteryService.getAll());
			modelAndView.addObject(PLOTS, plotService.getAll());
			modelAndView.addObject(GRAVES, graveService.getAll());
			return modelAndView;
		}

		return appointmentRegister(1, null, null, request);
	}

	@RequestMapping(value = "/edit/{id}", method = RequestMethod.GET)
	public ModelAndView edit(@PathVariable Integer id) {
		ModelAndView modelAndView = new ModelAndView(EDIT_DEAD_JSP);
		modelAndView.addObject(DEAD, deadService.getById(id));
		modelAndView.addObject(CEMETERIES, cemeteryService.getAll());
		modelAndView.addObject(PLOTS, plotService.getAll());
		modelAndView.addObject(GRAVES, graveService.getAll());
		return modelAndView;
	}

	@RequestMapping(value = "/edit", method = RequestMethod.POST)
	public ModelAndView doEdit(@ModelAttribute @Validated Dead dead, BindingResult bindingResult,
			@RequestParam(value = "cemeterySelect", required = false) Integer cemeteryId,
			@RequestParam(value = "plotSelect", required = false) Integer plotId, HttpServletRequest request) {
		if (bindingResult.hasErrors()) {
			return edit(dead.getId());
		}

		if (!graveService.checkGraveExistence(dead.getGrave(), plotId, cemeteryId)) {
			ModelAndView modelAndView = new ModelAndView(EDIT_DEAD_JSP);
			modelAndView.addObject(
					ERROR_MESSAGE,
					messageSource.getMessage("message.grave.not.exists", new Object[] { dead.getGrave().getId() },
							Locale.getDefault()));
			modelAndView.addObject(DEAD, dead);
			modelAndView.addObject(CEMETERIES, cemeteryService.getAll());
			modelAndView.addObject(PLOTS, plotService.getAll());
			modelAndView.addObject(GRAVES, graveService.getAll());
			return modelAndView;
		}
		String username = request.getUserPrincipal().getName();
		Integer errorCode = deadService.update(dead, username);

		if (errorCode == 1) {
			ModelAndView modelAndView = new ModelAndView(EDIT_DEAD_JSP);
			modelAndView.addObject(ERROR_MESSAGE,
					messageSource.getMessage("message.dead.already.exists", new Object[] { dead.getId() }, Locale.getDefault()));
			modelAndView.addObject(DEAD, dead);
			modelAndView.addObject(CEMETERIES, cemeteryService.getAll());
			modelAndView.addObject(PLOTS, plotService.getAll());
			modelAndView.addObject(GRAVES, graveService.getAll());
			return modelAndView;
		}
		return appointmentRegister(1, null, null, request);
	}
}
