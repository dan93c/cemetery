//package ro.immortals.controller;
//
//import java.util.List;
//import java.util.Locale;
//
//import org.springframework.stereotype.Controller;
//import org.springframework.validation.BindingResult;
//import org.springframework.validation.annotation.Validated;
//import org.springframework.web.bind.annotation.ModelAttribute;
//import org.springframework.web.bind.annotation.PathVariable;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestMethod;
//import org.springframework.web.bind.annotation.RequestParam;
//import org.springframework.web.servlet.ModelAndView;
//
//import ro.immortals.model.Dead;
//
//@Controller
//@RequestMapping("/dead")
//public class DeadController extends MainController {
//
//	@RequestMapping(value = "/list", method = RequestMethod.GET)
//	public ModelAndView list() {
//		ModelAndView modelAndView = new ModelAndView(LIST_DEADS_JSP);
//		List<Dead> deads = deadService.getAll();
//		modelAndView.addObject(DEADS, deads);
//		return modelAndView;
//	}
//
//	@RequestMapping(value = "/add", method = RequestMethod.GET)
//	public ModelAndView add(@ModelAttribute Dead dead) {
//
//		ModelAndView modelAndView = new ModelAndView(ADD_DEAD_JSP);
//		if (dead == null) {
//			modelAndView.addObject(DEAD, new Dead());
//		} else {
//			modelAndView.addObject(DEAD, dead);
//		}
//		modelAndView.addObject(CEMETERIES, cemeteryService.getAll());
//		modelAndView.addObject(PLOTS, plotService.getAll());
//		modelAndView.addObject(GRAVES, graveService.getAll());
//		return modelAndView;
//	}
//
//	@RequestMapping(value = "/add", method = RequestMethod.POST)
//	public ModelAndView doAdd(@ModelAttribute @Validated Dead dead, BindingResult bindingResult,
//			@RequestParam(value = "cemeterySelect", required = false) Integer cemeteryId,
//			@RequestParam(value = "plotSelect", required = false) Integer plotId) {
//		if (bindingResult.hasErrors()) {
//			return add(dead);
//		}
//		if (!graveService.checkGraveExistence(dead.getGrave(), plotId, cemeteryId)) {
//			ModelAndView modelAndView = new ModelAndView(ADD_DEAD_JSP);
//			modelAndView.addObject(ERROR_MESSAGE, messageSource.getMessage("message.grave.not.exists",
//					new Object[] { dead.getGrave().getId() }, Locale.getDefault()));
//			modelAndView.addObject(DEAD, dead);
//			modelAndView.addObject(CEMETERIES, cemeteryService.getAll());
//			modelAndView.addObject(PLOTS, plotService.getAll());
//			modelAndView.addObject(GRAVES, graveService.getAll());
//			return modelAndView;
//		}
//		deadService.add(dead);
//		return list();
//	}
//
//	@RequestMapping(value = "/edit/{id}", method = RequestMethod.GET)
//	public ModelAndView edit(@PathVariable Integer id) {
//		ModelAndView modelAndView = new ModelAndView(EDIT_DEAD_JSP);
//		modelAndView.addObject(DEAD, deadService.getById(id));
//		modelAndView.addObject(CEMETERIES, cemeteryService.getAll());
//		modelAndView.addObject(PLOTS, plotService.getAll());
//		modelAndView.addObject(GRAVES, graveService.getAll());
//		return modelAndView;
//	}
//
//	@RequestMapping(value = "/edit", method = RequestMethod.POST)
//	public ModelAndView doEdit(@ModelAttribute @Validated Dead dead, BindingResult bindingResult,
//			@RequestParam(value = "cemeterySelect", required = false) Integer cemeteryId,
//			@RequestParam(value = "plotSelect", required = false) Integer plotId) {
//		if (bindingResult.hasErrors()) {
//			return edit(dead.getId());
//		}
//
//		if (!graveService.checkGraveExistence(dead.getGrave(), plotId, cemeteryId)) {
//			ModelAndView modelAndView = new ModelAndView(EDIT_DEAD_JSP);
//			modelAndView.addObject(ERROR_MESSAGE, messageSource.getMessage("message.grave.not.exists",
//					new Object[] { dead.getGrave().getId() }, Locale.getDefault()));
//			modelAndView.addObject(DEAD, dead);
//			modelAndView.addObject(CEMETERIES, cemeteryService.getAll());
//			modelAndView.addObject(PLOTS, plotService.getAll());
//			modelAndView.addObject(GRAVES, graveService.getAll());
//			return modelAndView;
//		}
//		deadService.update(dead);
//		return list();
//	}
//}
