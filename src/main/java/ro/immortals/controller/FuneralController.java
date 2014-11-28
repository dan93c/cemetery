package ro.immortals.controller;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.propertyeditors.CustomDateEditor;
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

import ro.immortals.model.FuneralFile;

@Controller
@RequestMapping("/funeral")
public class FuneralController extends MainController {

	@Autowired
	@Qualifier(FUNERAL_FILE_VALIDATOR)
	private Validator funeralFileValidator;

	@InitBinder(FUNERAL_FILE)
	private void initBinder(HttpServletRequest request, WebDataBinder binder) throws Exception {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        CustomDateEditor editor = new CustomDateEditor(dateFormat, true);
        binder.registerCustomEditor(Date.class, editor);
        binder.setValidator(funeralFileValidator);
	}
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public ModelAndView list() {
		ModelAndView modelAndView = new ModelAndView(LIST_FUNERAL_FILES_JSP);
		List<FuneralFile> funeralFiles = funeralFileService.getAll();
		modelAndView.addObject(FUNERAL_FILES, funeralFiles);
		return modelAndView;
	}

	@RequestMapping(value = "/add", method = RequestMethod.GET)
	public ModelAndView add(@ModelAttribute FuneralFile funeralFile) {

		ModelAndView modelAndView = new ModelAndView(ADD_FUNERAL_FILE_JSP);
		if (funeralFile == null) {
			modelAndView.addObject(FUNERAL_FILE, new FuneralFile());
		} else {
			modelAndView.addObject(FUNERAL_FILE, funeralFile);
		}
		modelAndView.addObject(CEMETERIES, cemeteryService.getAll());
		modelAndView.addObject(PLOTS, plotService.getAll());
		modelAndView.addObject(GRAVES, graveService.getAll());
		return modelAndView;
	}

	@RequestMapping(value = "/add", method = RequestMethod.POST)
	public ModelAndView doAdd(@ModelAttribute @Validated FuneralFile funeralFile, BindingResult bindingResult,
	        @RequestParam(value = "cemeterySelect", required = false) Integer cemeteryId,
	        @RequestParam(value = "plotSelect", required = false) Integer plotId) {
		if (bindingResult.hasErrors()) {
			return add(funeralFile);
		}
		if (!graveService.checkGraveExistence(funeralFile.getGrave(), plotId, cemeteryId)) {
			ModelAndView modelAndView = new ModelAndView(ADD_FUNERAL_FILE_JSP);
			modelAndView.addObject(ERROR_MESSAGE, messageSource.getMessage("message.grave.not.exists",
			        new Object[] { funeralFile.getGrave().getId() }, Locale.getDefault()));
			modelAndView.addObject(FUNERAL_FILE, funeralFile);
			modelAndView.addObject(CEMETERIES, cemeteryService.getAll());
			modelAndView.addObject(PLOTS, plotService.getAll());
			modelAndView.addObject(GRAVES, graveService.getAll());
			return modelAndView;
		}
		funeralFileService.add(funeralFile);
		return list();
	}

	@RequestMapping(value = "/edit/{id}", method = RequestMethod.GET)
	public ModelAndView edit(@PathVariable Integer id) {
		ModelAndView modelAndView = new ModelAndView(EDIT_FUNERAL_FILE_JSP);
		modelAndView.addObject(FUNERAL_FILE, funeralFileService.getById(id));
		modelAndView.addObject(CEMETERIES, cemeteryService.getAll());
		modelAndView.addObject(PLOTS, plotService.getAll());
		modelAndView.addObject(GRAVES, graveService.getAll());
		return modelAndView;
	}

	@RequestMapping(value = "/edit", method = RequestMethod.POST)
	public ModelAndView doEdit(@ModelAttribute @Validated FuneralFile funeralFile, BindingResult bindingResult,
	        @RequestParam(value = "cemeterySelect", required = false) Integer cemeteryId,
	        @RequestParam(value = "plotSelect", required = false) Integer plotId) {
		if (bindingResult.hasErrors()) {
			return edit(funeralFile.getId());
		}

		if (!graveService.checkGraveExistence(funeralFile.getGrave(), plotId, cemeteryId)) {
			ModelAndView modelAndView = new ModelAndView(EDIT_FUNERAL_FILE_JSP);
			modelAndView.addObject(ERROR_MESSAGE, messageSource.getMessage("message.grave.not.exists",
			        new Object[] { funeralFile.getGrave().getId() }, Locale.getDefault()));
			modelAndView.addObject(FUNERAL_FILE, funeralFile);
			modelAndView.addObject(CEMETERIES, cemeteryService.getAll());
			modelAndView.addObject(PLOTS, plotService.getAll());
			modelAndView.addObject(GRAVES, graveService.getAll());
			return modelAndView;
		}
		funeralFileService.update(funeralFile);
		return list();
	}
}
