package ro.immortals.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.web.servlet.ModelAndView;

import ro.immortals.service.CemeteryService;
import ro.immortals.service.ClaimBookService;
import ro.immortals.service.ConcessionContractService;
import ro.immortals.service.DeadService;
import ro.immortals.service.DeadWithoutFamilyService;
import ro.immortals.service.GraveRequestService;
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
	protected GraveRequestService graveRequestService;

	@Autowired
	protected ConcessionContractService contractService;

	@Autowired
	protected DeadWithoutFamilyService deadWithoutFamilyService;
	
	@Autowired
	protected ClaimBookService claimService;

	public static final String ERROR_MESSAGE = "errorMessage";
	public static final String MESSAGE = "message";
	public static final String CEMETERY = "cemetery";
	public static final String CEMETERIES = "cemeteries";
	public static final String PLOT = "plot";
	public static final String PLOTS = "plots";
	public static final String GRAVES = "graves";
	public static final String GRAVE = "grave";
	public static final String GRAVE_REQUEST = "graveRequest";
	public static final String GRAVE_REQUESTS = "graveRequests";
	public static final String GRAVE_REQUEST_VALIDATOR = "graveRequestValidator";
	public static final String GRAVE_VALIDATOR = "graveValidator";
	public static final String USERNAME = "username";
	public static final String DEAD = "dead";
	public static final String DEADS = "deads";
	public static final String DEAD_VALIDATOR = "deadValidator";
	public static final String DEAD_WITHOUT_FAMILY = "deadWithoutFamily";
	public static final String DEAD_WITHOUT_FAMILY_VALIDATOR = "deadWithoutFamilyValidator";
	public static final String CONTRACT = "contract";
	public static final String CONTRACTS = "contracts";
	public static final String HISTORY = "history";
	public static final String OBJECTS = "objects";
	public static final String CLAIM="claimBook";
	public static final String CLAIMS="claims";
	public static final String CLAIM_VALIDATOR="claimBookValidator";

	public static final String SELECT_NR_OF_RECORDS = "selectNrOfRecords";
	public static final String CURRENT_PAGE = "currentPage";
	public static final String PAGE = "page";
	public static final String NR_OF_PAGES = "nrOfPages";
	public static final String BEGIN = "begin";
	public static final String END = "end";
	public static final Integer DEFAULT_NR_OF_RECORDS = 10;
	public static final Integer FIRST_PAGE = 1;
	public static final String SEARCH = "sch";
	public static final String ORDER = "order";

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
	public static final String ADD_DEAD_WITHOUT_FAMILY_JSP = "addDeadWithoutFamily";
	public static final String EDIT_DEAD_WITHOUT_FAMILY_JSP = "editDeadWithoutFamily";
	/* public static final String LIST_DEADS_JSP = "listDeads"; */
	public static final String ADD_CONTRACT_JSP = "addContract";
	public static final String EDIT_CONTRACT_JSP = "editContract";
	public static final String LIST_CONTRACTS_JSP = "listContracts";
	public static final String ADD_GRAVE_JSP = "addGrave";
	public static final String EDIT_GRAVE_JSP = "editGrave";
	public static final String LIST_GRAVES_JSP = "listGraves";
	public static final String ADD_GRAVE_REQUEST_JSP = "addGraveRequest";
	public static final String EDIT_GRAVE_REQUEST_JSP = "editGraveRequest";
	public static final String HISTORY_JSP = "history";
	public static final String APPOINTMENT_REGISTER_JSP = "appointmentRegister";
	public static final String DEAD_REGISTER_JSP = "deadRegister";
	public static final String DEAD_WITHOUT_FAMILY_REGISTER_JSP = "deadWithoutFamilyRegister";
	public static final String GRAVE_REGISTER_JSP = "graveRegister";
	public static final String GRAVE_MONUMENT_REGISTER_JSP = "graveMonumentRegister";
	public static final String GRAVE_REQUEST_REGISTER_JSP = "graveRequestRegister";
	public static final String ADD_CLAIM_JSP="addClaim";
	public static final String EDIT_CLAIM_JSP="editClaim";
	public static final String CLAIMS_REGISTER_JSP="claimRegister";

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

	public String getOrder(String order, HttpServletRequest request) {
		if (order == null) {
			if (request.getSession().getAttribute(ORDER) == null) {
				order = "0";
			} else {
				order = request.getSession().getAttribute(ORDER).toString();
			}
		}
		request.getSession().setAttribute(ORDER, order);
		return order;
	}

	public String getSearch(String search, HttpServletRequest request) {
		if (search == null) {
			if (request.getSession().getAttribute(SEARCH) != null) {
				search = request.getSession().getAttribute(SEARCH).toString();
			}

		}
		request.getSession().setAttribute(SEARCH, search);
		return search;
	}
}
