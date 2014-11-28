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

@Controller
@RequestMapping("/cemetery")
public class CemeteryController extends MainController {

	@Autowired
	@Qualifier("cemeteryValidator")
	private Validator cemeteryValidator;

	@InitBinder(CEMETERY)
	private void initBinder(WebDataBinder binder) {
		binder.setValidator(cemeteryValidator);
	}

	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public ModelAndView list() {
		ModelAndView modelAndView = new ModelAndView(LIST_CEMETERIES_JSP);
		List<Cemetery> cemeteries = cemeteryService.getAll();
		modelAndView.addObject(CEMETERIES, cemeteries);
		return modelAndView;
	}

	@RequestMapping(value = "/add", method = RequestMethod.GET)
	public ModelAndView add(@ModelAttribute Cemetery cemetery) {

		ModelAndView modelAndView = new ModelAndView(ADD_CEMETERY_JSP);
		if (cemetery == null) {
			modelAndView.addObject(CEMETERY, new Cemetery());
		} else {
			modelAndView.addObject(CEMETERY, cemetery);
		}
		return modelAndView;
	}

	@RequestMapping(value = "/add", method = RequestMethod.POST)
	public ModelAndView doAdd(@ModelAttribute @Validated Cemetery cemetery, BindingResult bindingResult) {
		if (bindingResult.hasErrors()) {
			return add(cemetery);
		}
		Integer errorCode = cemeteryService.add(cemetery);
		if (errorCode == 1) {
			ModelAndView modelAndView = new ModelAndView(ADD_CEMETERY_JSP);
			modelAndView.addObject(CEMETERY, cemetery);
			modelAndView.addObject(ERROR_MESSAGE, messageSource.getMessage("message.cemetery.already.exists",
			        new Object[] { cemetery.getName() }, Locale.getDefault()));
			return modelAndView;
		}
		return list();
	}

	@RequestMapping(value = "/edit/{id}", method = RequestMethod.GET)
	public ModelAndView edit(@PathVariable Integer id) {
		ModelAndView modelAndView = new ModelAndView(EDIT_CEMETERY_JSP);
		modelAndView.addObject(CEMETERY, cemeteryService.getById(id));
		return modelAndView;
	}

	@RequestMapping(value = "/edit", method = RequestMethod.POST)
	public ModelAndView doEdit(@ModelAttribute @Validated Cemetery cemetery, BindingResult bindingResult) {
		if (bindingResult.hasErrors()) {
			ModelAndView modelAndView = new ModelAndView(EDIT_CEMETERY_JSP);
			modelAndView.addObject(CEMETERY, cemetery);
			return modelAndView;
		}
		cemeteryService.update(cemetery);
		return list();
	}

	@RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
	public ModelAndView delete(@PathVariable Integer id, HttpServletRequest request) {
		cemeteryService.delete(id);
		return list();
	}
}
