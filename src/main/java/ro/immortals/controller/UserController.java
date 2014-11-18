package ro.immortals.controller;

import java.util.List;
import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Validator;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import ro.immortals.model.User;
import ro.immortals.service.UserService;


@Controller
@RequestMapping("/users")
public class UserController extends MainController {

	@Autowired
	private UserService userService;

	@Autowired
	@Qualifier("userValidator")
	private Validator userValidator;

	@InitBinder(USER)
	private void initBinder(WebDataBinder binder) {
		binder.addValidators(userValidator);
	}

	private static final String USER = "user";
	private static final String USERS = "users";

	@RequestMapping(method = RequestMethod.GET)
	public ModelAndView list(String errorMessage) {
		ModelAndView modelAndView = new ModelAndView(LIST_USERS_JSP);
		modelAndView.addObject(USER, new User());
		modelAndView.addObject(USERS, userService.getAll());
		return modelAndView;
	}

	@RequestMapping(value = "/add", method = RequestMethod.POST)
	public ModelAndView add(@ModelAttribute(USER) @Validated User user, BindingResult bindingResult) {
		ModelAndView modelAndView = new ModelAndView(LIST_USERS_JSP);
		if (!bindingResult.hasErrors()) {
			userService.add(user);
			modelAndView.addObject(
			        MESSAGE,
			        messageSource.getMessage("message.user.added.succes", new Object[] { user.getUsername() },
			                Locale.getDefault()));
			user = new User();
		}
		modelAndView.addObject(USER, user);
		modelAndView.addObject(USERS, userService.getAll());
		return modelAndView;

	}

	@RequestMapping(value = "/remove", method = RequestMethod.GET)
	public ModelAndView delete(@RequestParam(value = USERNAME) String username) {
		userService.delete(username);
		return new ModelAndView("redirect:/users");
	}

	@RequestMapping(value = "/getUsers", method = RequestMethod.GET, headers = "Accept=application/json")
	public @ResponseBody List<User> getUsers(@RequestParam(value = "term") String username) {

		return userService.simulateSearchResult(username);
	}

}
