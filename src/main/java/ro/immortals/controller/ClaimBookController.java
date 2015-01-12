package ro.immortals.controller;

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
import org.springframework.web.servlet.ModelAndView;

import ro.immortals.model.Cemetery;
import ro.immortals.model.ClaimBook;
import ro.immortals.model.Dead;

@Controller
@RequestMapping("/claim")
public class ClaimBookController extends MainController {

	@Autowired
	@Qualifier(CLAIM_VALIDATOR)
	private Validator claimBookValidator;

	@InitBinder(CLAIM)
	private void initBinder(WebDataBinder binder) {
		binder.setValidator(claimBookValidator);
	}

	@RequestMapping(value = "/reclamatii/{page}", method = RequestMethod.GET)
	public ModelAndView claimRegister(@PathVariable Integer page, @RequestParam(value = ORDER, required = false) String order,
			@RequestParam(value = SEARCH, required = false) String search, HttpServletRequest request) {
		ModelAndView modelAndView = new ModelAndView(CLAIMS_REGISTER_JSP);
		order = getOrder(order, request);
		search = getSearch(search, request);
		Integer recordsPerPage = DEFAULT_NR_OF_RECORDS;
		Integer nrOfRecords = claimService.getAllSearchBySize(search);
		Integer nrOfPages = (int) Math.ceil(nrOfRecords * 1.0 / recordsPerPage);
		page = setPagination(modelAndView, page, nrOfPages);
		request.getSession(false).setAttribute(SELECT_NR_OF_RECORDS, recordsPerPage);
		modelAndView.addObject(ORDER, order);
		modelAndView.addObject(SEARCH, search);
		modelAndView.addObject(CLAIMS,
				claimService.getAllByPageOrderBySearch(order, search, (page - 1) * recordsPerPage, recordsPerPage));
		return modelAndView;
	}

	@RequestMapping(value = "/add", method = RequestMethod.GET)
	public ModelAndView add(@ModelAttribute ClaimBook claim) {

		ModelAndView modelAndView = new ModelAndView(ADD_CLAIM_JSP);
		if (claim == null) {
			modelAndView.addObject(CLAIM, new ClaimBook());
		} else {
			modelAndView.addObject(CLAIM, claim);
		}
		return modelAndView;
	}

	@RequestMapping(value = "/add", method = RequestMethod.POST)
	public ModelAndView doAdd(@ModelAttribute @Validated ClaimBook claim, BindingResult bindingResult, HttpServletRequest request) {
		if (bindingResult.hasErrors()) {
			return add(claim);
		}
		String username = request.getUserPrincipal().getName();
		claimService.add(claim, username);

		return claimRegister(1, null, null, request);

	}

}
