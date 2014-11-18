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

import ro.immortals.model.Cemetery;
import ro.immortals.model.Dead;
import ro.immortals.service.DeadService;

@Controller
@RequestMapping("/dead")
public class DeadController extends MainController {
	@Autowired
	private DeadService deadService;

	@Autowired
	@Qualifier("deadValidator")
	private Validator deadValidator;

	@InitBinder(PLOT)
	private void initBinder(WebDataBinder binder) {
		binder.setValidator(deadValidator);
	}

	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public ModelAndView list() {
		ModelAndView modelAndView = new ModelAndView(LIST_DEADS_JSP);
		List<Dead> deads = deadService.getAll();
		modelAndView.addObject(DEADS, deads);
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

		return modelAndView;
	}

	@RequestMapping(value = "/add", method = RequestMethod.POST)
	public ModelAndView doAdd(@ModelAttribute @Validated Dead dead,
			BindingResult bindingResult) {
		if (bindingResult.hasErrors()) {
			return add(dead);
		}
		Integer errorCode = deadService.add(dead);
		if (errorCode == 1) {
			ModelAndView modelAndView = new ModelAndView(ADD_DEAD_JSP);
			modelAndView.addObject(DEAD, dead);
			modelAndView.addObject(ERROR_MESSAGE, messageSource.getMessage(
					"message.dead.already.exists",
					new Object[] { dead.getId() }, Locale.getDefault()));
			return modelAndView;
		}
		return list();
	}

	@RequestMapping(value = "/edit/{id}", method = RequestMethod.GET)
	public ModelAndView edit(@PathVariable Integer id) {
		ModelAndView modelAndView = new ModelAndView(EDIT_DEAD_JSP);
		modelAndView.addObject(DEAD, deadService.getById(id));
		return modelAndView;
	}

	@RequestMapping(value = "/edit", method = RequestMethod.POST)
	public ModelAndView doEdit(@ModelAttribute @Validated Dead dead,
			BindingResult bindingResult) {
		if (bindingResult.hasErrors()) {
			ModelAndView modelAndView = new ModelAndView(EDIT_PLOT_JSP);
			modelAndView.addObject(PLOT, dead);
			return modelAndView;
		}
		deadService.update(dead);
		return list();
	}

	@RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
	public ModelAndView delete(@PathVariable Integer id,
			HttpServletRequest request) {
		deadService.delete(id);
		return list();
	}
}
