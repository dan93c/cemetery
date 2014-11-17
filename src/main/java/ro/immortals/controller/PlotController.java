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
import org.springframework.web.servlet.ModelAndView;

import ro.immortals.model.Cemetery;
import ro.immortals.model.Plot;
import ro.immortals.service.CemeteryService;
import ro.immortals.service.PlotService;

@Controller
@RequestMapping("/plot")
public class PlotController extends MainController {
	@Autowired
	private PlotService plotService;
	
	@Autowired
	@Qualifier("plotValidator")
	private Validator plotValidator;

	@InitBinder(PLOT)
	private void initBinder(WebDataBinder binder) {
		binder.setValidator(plotValidator);
	}

	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public ModelAndView list() {
		ModelAndView modelAndView = new ModelAndView(LIST_PLOTS_JSP);
		List<Plot> plots = plotService.getAll();
		modelAndView.addObject(PLOTS, plots);
		return modelAndView;
	}

	@RequestMapping(value = "/add", method = RequestMethod.GET)
	public ModelAndView add(@ModelAttribute Plot plot) {

		ModelAndView modelAndView = new ModelAndView(ADD_PLOT_JSP);
		if (plot == null) {
			modelAndView.addObject(PLOT, new Plot());
		} else {
			modelAndView.addObject(PLOT, plot);
		}
		List<Cemetery> cemeteries = cemeteryService.getAll();
		modelAndView.addObject(CEMETERIES, cemeteries);
		return modelAndView;
	}

	@RequestMapping(value = "/add", method = RequestMethod.POST)
	public ModelAndView doAdd(@ModelAttribute @Validated Plot plot,
			BindingResult bindingResult) {
		if (bindingResult.hasErrors()) {
			return add(plot);
		}
		Integer errorCode = plotService.add(plot);
		if (errorCode == 1) {
			ModelAndView modelAndView = new ModelAndView(ADD_PLOT_JSP);
			modelAndView.addObject(PLOT, plot);
			modelAndView.addObject(ERROR_MESSAGE, messageSource.getMessage(
					"message.plot.already.exists",
					new Object[] { plot.getId() }, Locale.getDefault()));
			return modelAndView;
		}
		return list();
	}

	@RequestMapping(value = "/edit/{id}", method = RequestMethod.GET)
	public ModelAndView edit(@PathVariable Integer id) {
		ModelAndView modelAndView = new ModelAndView(EDIT_PLOT_JSP);
		modelAndView.addObject(PLOT, plotService.getById(id));
		return modelAndView;
	}

	@RequestMapping(value = "/edit", method = RequestMethod.POST)
	public ModelAndView doEdit(@ModelAttribute @Validated Plot plot,
			BindingResult bindingResult) {
		if (bindingResult.hasErrors()) {
			ModelAndView modelAndView = new ModelAndView(EDIT_PLOT_JSP);
			modelAndView.addObject(PLOT, plot);
			return modelAndView;
		}
		plotService.update(plot);
		return list();
	}

	@RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
	public ModelAndView delete(@PathVariable Integer id,
			HttpServletRequest request) {
		plotService.delete(id);
		return list();
	}
}
