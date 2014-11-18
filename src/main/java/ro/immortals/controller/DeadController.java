package ro.immortals.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import ro.immortals.model.Cemetery;
import ro.immortals.model.Dead;

@Controller
@RequestMapping("/dead")
public class DeadController extends MainController {

	@RequestMapping(value = "/add", method = RequestMethod.GET)
	public ModelAndView add(@ModelAttribute Dead dead) {

		ModelAndView modelAndView = new ModelAndView(ADD_DEAD_JSP);
		modelAndView.addObject(DEAD, new Dead());
		modelAndView.addObject(CEMETERIES, cemeteryService.getAll());
		modelAndView.addObject(PLOTS, plotService.getAll());
		modelAndView.addObject(GRAVES, graveService.getAll());
		return modelAndView;
	}

	@RequestMapping(value = "/add", method = RequestMethod.POST)
	public ModelAndView doAdd(@ModelAttribute @Validated Dead dead,
			BindingResult bindingResult,
			 @RequestParam(value = "cemeterySelect", required = false) Integer cemeteryId,
			 @RequestParam(value = "plotSelect", required = false) Integer plotId) {
		if (bindingResult.hasErrors()) {
			ModelAndView modelAndView = new ModelAndView(ADD_DEAD_JSP);
			modelAndView.addObject(DEAD, dead);
			modelAndView.addObject(CEMETERIES, cemeteryService.getAll());
			modelAndView.addObject(PLOTS, plotService.getAll());
			modelAndView.addObject(GRAVES, graveService.getAll());
			return modelAndView;
		}
		//va trebui verificat daca mormantul dat apartine parcelei date-> cimitirului dat
		System.out.println("------------cemeteryID: "+cemeteryId+", plotId "+plotId);//+", graveID "+ dead.getGrave().getId());
		//deadService.add(dead);      //inca nu sunt adaugate morminte
		return new ModelAndView("redirect:/cemetery/list");
	}
}
