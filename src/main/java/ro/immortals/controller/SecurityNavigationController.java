package ro.immortals.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class SecurityNavigationController extends MainController{

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public ModelAndView loginForm() {
		return new ModelAndView(MainController.LOGIN_JSP);
	}

	@RequestMapping(value = "/error-login", method = RequestMethod.GET)
	public ModelAndView invalidLogin() {
		ModelAndView modelAndView = new ModelAndView(MainController.LOGIN_JSP);
		modelAndView.addObject(ERROR_MESSAGE, true);
		return modelAndView;
	}

	@RequestMapping(value = {"/home"}, method = RequestMethod.GET)
	public String successLogin(HttpServletRequest request) {
		request.getSession().setAttribute(USERNAME, request.getUserPrincipal().getName());
		return "redirect:/cemetery/list";
	}

}