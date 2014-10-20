package ro.immortals.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;


@Controller
public class MainController {


	@RequestMapping(value = "/", method = RequestMethod.GET)
	public ModelAndView login() {
		ModelAndView modelAndView = new ModelAndView("login");
		modelAndView.addObject("message","You're the next one! Ha ha ha Mortzy");
		return modelAndView;
	}
}
