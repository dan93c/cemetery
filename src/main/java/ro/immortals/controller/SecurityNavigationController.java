package ro.immortals.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class SecurityNavigationController extends MainController {

	@RequestMapping(value = "/default", method = RequestMethod.GET)
	public ModelAndView defaultAfterLogin(HttpServletRequest request) {
		if (request.isUserInRole("ROLE_ADMIN")) {
			request.getSession().setAttribute(USERNAME, request.getUserPrincipal().getName());
			return new ModelAndView("redirect:/cemetery/list");
		}
		return invalidLogin();
	}

	@RequestMapping(value = { "/", "/login" }, method = RequestMethod.GET)
	public ModelAndView loginForm() {
		return new ModelAndView(LOGIN_JSP);
	}

	@RequestMapping(value = "/error-login", method = RequestMethod.GET)
	public ModelAndView invalidLogin() {
		ModelAndView modelAndView = new ModelAndView(LOGIN_JSP);
		modelAndView.addObject(ERROR_MESSAGE, true);
		return modelAndView;
	}

	@RequestMapping(value = { "/home" }, method = RequestMethod.GET)
	public String successLogin(HttpServletRequest request) {
		return "redirect:/cemetery/list";
	}

}
