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

import ro.immortals.model.ConcessionContract;
import sun.misc.GC;

@Controller
@RequestMapping("/contract")
public class ConcessionContractController extends MainController {

	/*
	 * @RequestMapping(value = "/list", method = RequestMethod.GET) public
	 * ModelAndView list() { ModelAndView modelAndView = new
	 * ModelAndView(LIST_CONTRACTS_JSP); List<ConcessionContract> contracts =
	 * contractService.getAll(); modelAndView.addObject(CONTRACTS, contracts);
	 * return modelAndView; }
	 */
	@Autowired
	@Qualifier(CONCESSION_CONTRACT_VALIDATOR)
	private Validator contractValidator;

	@InitBinder(CONTRACT)
	private void initBinder(WebDataBinder binder) {
		binder.setValidator(contractValidator);
	}

	@RequestMapping(value = { "/list/{page}" }, method = RequestMethod.GET)
	public ModelAndView contractRegister(@PathVariable Integer page,
			@RequestParam(value = ORDER, required = false) String order,
			@RequestParam(value = SEARCH, required = false) String search,
			HttpServletRequest request) {
		ModelAndView modelAndView = new ModelAndView(LIST_CONTRACTS_JSP);
		order = getOrder(order, request);
		search = getSearch(search, request);
		Integer recordsPerPage = DEFAULT_NR_OF_RECORDS;
		Integer nrOfRecords = contractService.getAllSearchBySize(search);
		Integer nrOfPages = (int) Math.ceil(nrOfRecords * 1.0 / recordsPerPage);
		page = setPagination(modelAndView, page, nrOfPages);
		request.getSession(false).setAttribute(SELECT_NR_OF_RECORDS,
				recordsPerPage);
		modelAndView.addObject(ORDER, order);
		modelAndView.addObject(SEARCH, search);
		modelAndView.addObject(CONTRACTS, contractService
				.getAllByPageOrderBySearch(order, search, (page - 1)
						* recordsPerPage, recordsPerPage));
		return modelAndView;

	}

	@RequestMapping(value = "/add", method = RequestMethod.GET)
	public ModelAndView add(@ModelAttribute ConcessionContract contract) {

		ModelAndView modelAndView = new ModelAndView(ADD_CONTRACT_JSP);
		if (contract == null) {
			modelAndView.addObject(CONTRACT, new ConcessionContract());
		} else {
			modelAndView.addObject(CONTRACT, contract);
		}
		modelAndView.addObject(CEMETERIES, cemeteryService.getAll());
		modelAndView.addObject(PLOTS, plotService.getAll());
		modelAndView.addObject(GRAVES, graveService.getAll());
		return modelAndView;
	}

	@RequestMapping(value = "/add", method = RequestMethod.POST)
	public ModelAndView doAdd(
			@ModelAttribute @Validated ConcessionContract contract,
			BindingResult bindingResult,
			@RequestParam(value = "cemeterySelect", required = false) Integer cemeteryId,
			@RequestParam(value = "plotSelect", required = false) Integer plotId,
			HttpServletRequest request) {
		if (bindingResult.hasErrors()) {
			return add(contract);
		}
		if (!graveService.checkGraveExistence(contract.getGrave(), plotId,
				cemeteryId)) {
			ModelAndView modelAndView = new ModelAndView(ADD_CONTRACT_JSP);
			modelAndView.addObject(ERROR_MESSAGE, messageSource.getMessage(
					"message.grave.not.exists", new Object[] { contract
							.getGrave().getNrGrave() }, Locale.getDefault()));
			modelAndView.addObject(CONTRACT, contract);
			modelAndView.addObject(CEMETERIES, cemeteryService.getAll());
			modelAndView.addObject(PLOTS, plotService.getAll());
			modelAndView.addObject(GRAVES, graveService.getAll());
			return modelAndView;
		}
		String username = request.getUserPrincipal().getName();
		int errorCode = contractService.add(contract, username);
		if (errorCode != 0) {
			ModelAndView modelAndView = new ModelAndView(ADD_CONTRACT_JSP);
			modelAndView.addObject(CONTRACT, contract);
			modelAndView.addObject(CEMETERIES, cemeteryService.getAll());
			modelAndView.addObject(PLOTS, plotService.getAll());
			modelAndView.addObject(GRAVES, graveService.getAll());
			if (errorCode == 1) { // existing contract
				modelAndView.addObject(ERROR_MESSAGE, messageSource.getMessage(
						"message.contract.already.exists",
						new Object[] { contract.getReceiptNr() },
						Locale.getDefault()));
			} else if (errorCode == 2) {// grave is not free
				modelAndView.addObject(ERROR_MESSAGE, messageSource.getMessage(
						"message.contract.not.available", null,
						Locale.getDefault()));
			}
			return modelAndView;
		}

		return contractRegister(1, null, null, request);
	}

	@RequestMapping(value = "/edit/{id}", method = RequestMethod.GET)
	public ModelAndView edit(@PathVariable Integer id) {
		ModelAndView modelAndView = new ModelAndView(EDIT_CONTRACT_JSP);
		ConcessionContract c = contractService.getById(id);
		System.out.println(c.getGrave().getNrGrave() + " ID "
				+ c.getGrave().getId());
		modelAndView.addObject(CONTRACT, c);

		modelAndView.addObject(CEMETERIES, cemeteryService.getAll());
		modelAndView.addObject(PLOTS, plotService.getAll());
		modelAndView.addObject(GRAVES, graveService.getAll());
		return modelAndView;
	}

	@RequestMapping(value = "/edit", method = RequestMethod.POST)
	public ModelAndView doEdit(
			@ModelAttribute @Validated ConcessionContract contract,
			BindingResult bindingResult,
			@RequestParam(value = "r", required = false) String updateDate,
			HttpServletRequest request) {
		if (bindingResult.hasErrors()) {
			return edit(contract.getId());
		}
		if (!graveService.checkGraveExistence(contract.getGrave(), contract
				.getGrave().getPlot().getId(), contract.getGrave().getPlot()
				.getCemetery().getId())) {
			ModelAndView modelAndView = new ModelAndView(EDIT_CONTRACT_JSP);
			modelAndView.addObject(ERROR_MESSAGE, messageSource.getMessage(
					"message.grave.not.exists", new Object[] { contract
							.getGrave().getNrGrave() }, Locale.getDefault()));
			modelAndView.addObject(CONTRACT, contract);
			modelAndView.addObject(CEMETERIES, cemeteryService.getAll());
			modelAndView.addObject(PLOTS, plotService.getAll());
			modelAndView.addObject(GRAVES, graveService.getAll());
			return modelAndView;
		}

		String username = request.getUserPrincipal().getName();
		Integer errorCode = contractService.update(contract, username,
				updateDate);

		if (errorCode != 0) {
			ModelAndView modelAndView = new ModelAndView(EDIT_CONTRACT_JSP);
			modelAndView.addObject(CONTRACT, contract);
			modelAndView.addObject(CEMETERIES, cemeteryService.getAll());
			modelAndView.addObject(PLOTS, plotService.getAll());
			modelAndView.addObject(GRAVES, graveService.getAll());
			if (errorCode == 1) { // existing contract
				modelAndView.addObject(ERROR_MESSAGE, messageSource.getMessage(
						"message.contract.already.exists",
						new Object[] { contract.getReceiptNr() },
						Locale.getDefault()));
			} else if (errorCode == 2) {// grave is not free
				modelAndView.addObject(ERROR_MESSAGE, messageSource.getMessage(
						"message.contract.not.available", null,
						Locale.getDefault()));
			}
			return modelAndView;
		}
		return contractRegister(1, null, null, request);
	}

	@RequestMapping(value = { "/expired/{page}" }, method = RequestMethod.GET)
	public ModelAndView listExpiredGraves(@PathVariable Integer page,
			@RequestParam(value = ORDER, required = false) String order,
			@RequestParam(value = SEARCH, required = false) String search,
			HttpServletRequest request) {
		ModelAndView modelAndView = new ModelAndView(LIST_EXPIRED_GRAVES_JSP);
		order = getOrder(order, request);
		search = getSearch(search, request);
		Integer recordsPerPage = DEFAULT_NR_OF_RECORDS;
		Integer nrOfRecords = contractService
				.getAllGravesExpiredOnYearsSize(search);
		Integer nrOfPages = (int) Math.ceil(nrOfRecords * 1.0 / recordsPerPage);
		page = setPagination(modelAndView, page, nrOfPages);
		request.getSession(false).setAttribute(SELECT_NR_OF_RECORDS,
				recordsPerPage);
		modelAndView.addObject(ORDER, order);
		modelAndView.addObject(SEARCH, search);
		modelAndView.addObject(CONTRACTS, contractService
				.getAllGravesExpiredOnYears(order, search, (page - 1)
						* recordsPerPage, recordsPerPage));
		return modelAndView;

	}

}
