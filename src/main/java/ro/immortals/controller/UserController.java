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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import ro.immortals.model.User;
import ro.immortals.service.HistoryService;
import ro.immortals.service.UserService;

@Controller
@RequestMapping("/")
public class UserController extends MainController {

	@Autowired
	private UserService userService;

	@Autowired
	private HistoryService historyService;

	@Autowired
	@Qualifier("userValidator")
	private Validator userValidator;

	@InitBinder(USER)
	private void initBinder(WebDataBinder binder) {
		binder.addValidators(userValidator);
	}

	private static final String USER = "user";
	private static final String USERS = "users";
	public static final String ACTION_SELECT = "action";

	@RequestMapping(value = USERS, method = RequestMethod.GET)
	public ModelAndView list(String errorMessage) {
		ModelAndView modelAndView = new ModelAndView(LIST_USERS_JSP);
		modelAndView.addObject(USER, new User());
		modelAndView.addObject(USERS, userService.getAll());
		return modelAndView;
	}

	@RequestMapping(value = "users/add", method = RequestMethod.POST)
	public ModelAndView add(@ModelAttribute(USER) @Validated User user,
			BindingResult bindingResult, HttpServletRequest request) {
		ModelAndView modelAndView = new ModelAndView(LIST_USERS_JSP);
		if (!bindingResult.hasErrors()) {
			String username = request.getUserPrincipal().getName();
			userService.add(user, username);
			modelAndView.addObject(MESSAGE, messageSource.getMessage(
					"message.user.added.succes",
					new Object[] { user.getUsername() }, Locale.getDefault()));
			user = new User();
		}
		modelAndView.addObject(USER, user);
		modelAndView.addObject(USERS, userService.getAll());
		return modelAndView;

	}

	@RequestMapping(value = "users/remove", method = RequestMethod.GET)
	public ModelAndView delete(@RequestParam(value = USERNAME) String username,
			HttpServletRequest request) {
		String u = request.getUserPrincipal().getName();
		userService.delete(username, u);
		return new ModelAndView("redirect:/users");
	}

	@RequestMapping(value = "users/getUsers", method = RequestMethod.GET, headers = "Accept=application/json")
	public @ResponseBody List<User> getUsers(
			@RequestParam(value = "term") String username) {

		return userService.simulateSearchResult(username);
	}

	@RequestMapping(value = { "history/{page}" }, method = RequestMethod.GET)
	public ModelAndView getHistory(
			@PathVariable Integer page,
			@RequestParam(value = ACTION_SELECT, required = false) String actionSelect,
			@RequestParam(value = SEARCH, required = false) String historySearch,
			HttpServletRequest request) {
		ModelAndView modelAndView = new ModelAndView(HISTORY_JSP);
		if (actionSelect == null) {
			if (request.getSession(false).getAttribute(ACTION_SELECT) == null) {
				actionSelect = "0";
			} else {
				actionSelect = request.getSession(false)
						.getAttribute(ACTION_SELECT).toString();
			}
		}
		historySearch = getSearch(historySearch, request);
		Integer recordsPerPage = DEFAULT_NR_OF_RECORDS;
		Integer nrOfRecords = historyService.getAllSizeFilterBySearch(
				actionSelect, historySearch);
		Integer nrOfPages = (int) Math.ceil(nrOfRecords * 1.0 / recordsPerPage);
		page = setPagination(modelAndView, page, nrOfPages);
		request.getSession(false).setAttribute(ACTION_SELECT, actionSelect);
		request.getSession(false).setAttribute(SELECT_NR_OF_RECORDS,
				recordsPerPage);
		modelAndView.addObject(ACTION_SELECT, actionSelect);
		modelAndView.addObject(SEARCH, historySearch);
		modelAndView.addObject(HISTORY, historyService.getByPageFilterBySearch(
				actionSelect, historySearch, (page - 1) * recordsPerPage,
				recordsPerPage));
		return modelAndView;

	}

}
