package ro.immortals.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;

import ro.immortals.service.CemeteryService;
import ro.immortals.service.ConcessionContractService;
import ro.immortals.service.DeadService;
import ro.immortals.service.FuneralFileService;
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
	protected FuneralFileService funeralFileService;
	
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
	public static final String CONTRACT = "contract";
	public static final String CONTRACTS = "contracts";
	public static final String FUNERAL_FILE = "funeralFile";
	public static final String FUNERAL_FILES = "funeralFiles";
	public static final String FUNERAL_FILE_VALIDATOR = "funeralFileValidator";
	
	/* PAGES */
	public static final String LOGIN_JSP = "login";
	public static final String LIST_USERS_JSP = "listUsers";
	public static final String LIST_CEMETERIES_JSP = "listCemeteries";
	public static final String ADD_CEMETERY_JSP = "addCemetery";
	public static final String EDIT_CEMETERY_JSP = "editCemetery";
	public static final String LIST_PLOTS_JSP = "listPlots";
	public static final String ADD_PLOT_JSP = "addPlot";
	public static final String EDIT_PLOT_JSP = "editPlot";
//	public static final String ADD_DEAD_JSP = "addDead";
//	public static final String EDIT_DEAD_JSP = "editDead";
//	public static final String LIST_DEADS_JSP = "listDeads";
	public static final String ADD_CONTRACT_JSP = "addContract";
	public static final String EDIT_CONTRACT_JSP = "editContract";
	public static final String LIST_CONTRACTS_JSP = "listContracts";
	public static final String ADD_GRAVE_JSP = "addGrave";
	public static final String EDIT_GRAVE_JSP = "editGrave";
	public static final String LIST_GRAVES_JSP = "listGraves";
	public static final String ADD_FUNERAL_FILE_JSP = "addFuneralFile";
	public static final String EDIT_FUNERAL_FILE_JSP = "editFuneralFile";
	public static final String LIST_FUNERAL_FILES_JSP = "listFuneralFiles";
	
}
