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
	public ModelAndView doAdd(@ModelAttribute @Validated Grave grave, BindingResult bindingResult) {
		if (bindingResult.hasErrors()) {
			return add(grave);
		}
		Integer errorCode = graveService.add(grave);
		if (errorCode == 1) {
			ModelAndView modelAndView = new ModelAndView(ADD_GRAVE_JSP);
			modelAndView.addObject(GRAVE, grave);
			modelAndView.addObject(CEMETERIES, cemeteryService.getAll());
			modelAndView.addObject(PLOTS, plotService.getAll());
			modelAndView.addObject(ERROR_MESSAGE, messageSource.getMessage("message.grave.already.exists",
			        new Object[] { grave.getNrGrave(), grave.getPlot().getName() }, Locale.getDefault()));
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
	public ModelAndView doEdit(@ModelAttribute @Validated Grave grave, BindingResult bindingResult) {
		if (bindingResult.hasErrors()) {
			ModelAndView modelAndView = new ModelAndView(EDIT_GRAVE_JSP);
			modelAndView.addObject(GRAVE, grave);
			modelAndView.addObject(CEMETERIES, cemeteryService.getAll());
			modelAndView.addObject(PLOTS, plotService.getAll());
			return modelAndView;
		}
		graveService.update(grave);
		return list();
	}

	@RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
	public ModelAndView delete(@PathVariable Integer id, HttpServletRequest request) {
		graveService.delete(id);
		return list();
	}
}
