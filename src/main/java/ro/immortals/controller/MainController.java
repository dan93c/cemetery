package ro.immortals.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class MainController {

	@Autowired
	protected MessageSource messageSource;

	public static final String ERROR_MESSAGE = "errorMessage";
	public static final String MESSAGE = "message";
	public static final String CEMETERY = "cemetery";
	public static final String CEMETERIES = "cemeteries";
	public static final String CEMETERY_CODE = "cemetery_code";

	/* PAGES */
	public static final String LIST_CEMETERIES_JSP = "listCemeteries";
	public static final String ADD_CEMETERY_JSP = "addCemetery";
	public static final String EDIT_CEMETERY_JSP = "editCemetery";

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public ModelAndView index() {

		ModelAndView modelAndView = new ModelAndView("login");
		modelAndView.addObject(MESSAGE, "First page..welcome");
		return modelAndView;
	}
}
