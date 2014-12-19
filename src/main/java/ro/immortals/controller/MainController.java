package ro.immortals.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.web.servlet.ModelAndView;

import ro.immortals.service.CemeteryService;
import ro.immortals.service.ConcessionContractService;
import ro.immortals.service.DeadService;
import ro.immortals.service.GraveService;
import ro.immortals.service.PlotService;

@Controller
public class MainController {

	@Autowired
	protected MessageSource messageSource;
	
	@Autowired
	protected CemeteryService cemeteryService;
	
	@Autowired
	protected PlotService plotService;
	
	@Autowired
	protected DeadService deadService;
	
	@Autowired
	protected GraveService graveService;
	
	@Autowired
	protected ConcessionContractService contractService;


	public static final String ERROR_MESSAGE = "errorMessage";
	public static final String MESSAGE = "message";
	public static final String CEMETERY = "cemetery";
	public static final String CEMETERIES = "cemeteries";
	public static final String PLOT = "plot";
	public static final String PLOTS = "plots";
	public static final String GRAVES = "graves";
	public static final String GRAVE = "grave";
	public static final String GRAVE_VALIDATOR = "graveValidator";
	public static final String USERNAME = "username";
	public static final String DEAD = "dead";
	public static final String DEADS = "deads";
	public static final String DEAD_VALIDATOR = "deadValidator";
	public static final String CONTRACT = "contract";
	public static final String CONTRACTS = "contracts";
	public static final String HISTORY = "history";
	
	public static final String SELECT_NR_OF_RECORDS = "selectNrOfRecords";
	public static final String CURRENT_PAGE = "currentPage";
	public static final String PAGE = "page";
	public static final String NR_OF_PAGES = "nrOfPages";
	public static final String BEGIN = "begin";
	public static final String END = "end";
	public static final Integer DEFAULT_NR_OF_RECORDS = 10;
	public static final Integer FIRST_PAGE = 1;
	
	/* PAGES */
	public static final String LOGIN_JSP = "login";
	public static final String LIST_USERS_JSP = "listUsers";
	public static final String LIST_CEMETERIES_JSP = "listCemeteries";
	public static final String ADD_CEMETERY_JSP = "addCemetery";
	public static final String EDIT_CEMETERY_JSP = "editCemetery";
	public static final String LIST_PLOTS_JSP = "listPlots";
	public static final String ADD_PLOT_JSP = "addPlot";
	public static final String EDIT_PLOT_JSP = "editPlot";
	public static final String ADD_DEAD_JSP = "addDead";
	public static final String EDIT_DEAD_JSP = "editDead";
	public static final String LIST_DEADS_JSP = "listDeads";
	public static final String ADD_CONTRACT_JSP = "addContract";
	public static final String EDIT_CONTRACT_JSP = "editContract";
	public static final String LIST_CONTRACTS_JSP = "listContracts";
	public static final String ADD_GRAVE_JSP = "addGrave";
	public static final String EDIT_GRAVE_JSP = "editGrave";
	public static final String LIST_GRAVES_JSP = "listGraves";
	public static final String HISTORY_JSP = "history";
	public static final String SEARCH_HISTORY_JSP = "searchHistory";

	
	/**
	 * set the nr of the current page and values of pagination format Pagination
	 * format: show max 7 pages ex: (nrOfPages=9, currentPage=5) << Previous 2 3
	 * 4 5 6 7 8 Next >>
	 * 
	 * @param modelAndView
	 *            the model on which will be set the pagination
	 * @param page
	 *            the current page
	 * @param nrOfPages
	 *            the number of pages
	 * @return the current page
	 */
	public Integer setPagination(ModelAndView modelAndView, Integer page, Integer nrOfPages) {
		if ((page == null) || (page > nrOfPages) || (page < 1)) {
			page = FIRST_PAGE;
		}
		int begin = Math.max(1, page - 3);
		int end = Math.min(begin + 6, nrOfPages);
		modelAndView.addObject(BEGIN, begin);
		modelAndView.addObject(END, end);
		modelAndView.addObject(NR_OF_PAGES, nrOfPages);
		modelAndView.addObject(CURRENT_PAGE, page);
		return page;
	}
}
