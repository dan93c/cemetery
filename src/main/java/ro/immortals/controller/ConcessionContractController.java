package ro.immortals.controller;

import java.util.List;
import java.util.Locale;

import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import ro.immortals.model.ConcessionContract;

@Controller
@RequestMapping("/contract")
public class ConcessionContractController extends MainController {

	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public ModelAndView list() {
		ModelAndView modelAndView = new ModelAndView(LIST_CONTRACTS_JSP);
		List<ConcessionContract> contracts = contractService.getAll();
		modelAndView.addObject(CONTRACTS, contracts);
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
	public ModelAndView doAdd(@ModelAttribute @Validated ConcessionContract contract, BindingResult bindingResult,
			@RequestParam(value = "cemeterySelect", required = false) Integer cemeteryId,
			@RequestParam(value = "plotSelect", required = false) Integer plotId) {
		if (bindingResult.hasErrors()) {
			return add(contract);
		}
		if (!graveService.checkGraveExistence(contract.getGrave(), plotId, cemeteryId)) {
			ModelAndView modelAndView = new ModelAndView(ADD_CONTRACT_JSP);
			modelAndView.addObject(ERROR_MESSAGE, messageSource.getMessage("message.grave.not.exists",
					new Object[] { contract.getGrave().getId() }, Locale.getDefault()));
			modelAndView.addObject(CONTRACT, contract);
			modelAndView.addObject(CEMETERIES, cemeteryService.getAll());
			modelAndView.addObject(PLOTS, plotService.getAll());
			modelAndView.addObject(GRAVES, graveService.getAll());
			return modelAndView;
		}
		int errorCode=contractService.add(contract);
		if(errorCode==1){
			ModelAndView modelAndView = new ModelAndView(ADD_CONTRACT_JSP);
			modelAndView.addObject(ERROR_MESSAGE, messageSource.getMessage("message.contract.already.exists",
					new Object[] { contract.getReceiptNr() }, Locale.getDefault()));
			modelAndView.addObject(CONTRACT, contract);
			modelAndView.addObject(CEMETERIES, cemeteryService.getAll());
			modelAndView.addObject(PLOTS, plotService.getAll());
			modelAndView.addObject(GRAVES, graveService.getAll());
			return modelAndView;
		}
		return list();
	}

	@RequestMapping(value = "/edit/{id}", method = RequestMethod.GET)
	public ModelAndView edit(@PathVariable Integer id) {
		ModelAndView modelAndView = new ModelAndView(EDIT_CONTRACT_JSP);
		modelAndView.addObject(CONTRACT, contractService.getById(id));
		modelAndView.addObject(CEMETERIES, cemeteryService.getAll());
		modelAndView.addObject(PLOTS, plotService.getAll());
		modelAndView.addObject(GRAVES, graveService.getAll());
		return modelAndView;
	}

	@RequestMapping(value = "/edit", method = RequestMethod.POST)
	public ModelAndView doEdit(@ModelAttribute @Validated ConcessionContract contract, BindingResult bindingResult,
			@RequestParam(value = "cemeterySelect", required = false) Integer cemeteryId,
			@RequestParam(value = "plotSelect", required = false) Integer plotId) {
		if (bindingResult.hasErrors()) {
			return edit(contract.getId());
		}

		if (!graveService.checkGraveExistence(contract.getGrave(), plotId, cemeteryId)) {
			ModelAndView modelAndView = new ModelAndView(EDIT_CONTRACT_JSP);
			modelAndView.addObject(ERROR_MESSAGE, messageSource.getMessage("message.grave.not.exists",
					new Object[] { contract.getGrave().getId() }, Locale.getDefault()));
			modelAndView.addObject(CONTRACT, contract);
			modelAndView.addObject(CEMETERIES, cemeteryService.getAll());
			modelAndView.addObject(PLOTS, plotService.getAll());
			modelAndView.addObject(GRAVES, graveService.getAll());
			return modelAndView;
		}
		contractService.update(contract);
		return list();
	}
}
