package co.edu.icesi.fi.tics.tssc.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import co.edu.icesi.fi.tics.tssc.model.TsscStory;
import co.edu.icesi.fi.tics.tssc.model.ValidationTsscStory;
import co.edu.icesi.fi.tics.tssc.service.TsscGameServiceImp;
import co.edu.icesi.fi.tics.tssc.service.TsscStoryServiceImp;

@Controller
public class TsscStoryController {

	TsscStoryServiceImp tsscStoryServiceImp;
	
	TsscGameServiceImp tsscGameServiceImp;
	
	@Autowired
	public TsscStoryController(TsscStoryServiceImp tsscStoryServiceImp, TsscGameServiceImp tsscGameServiceImp) {
		this.tsscStoryServiceImp = tsscStoryServiceImp;
		this.tsscGameServiceImp = tsscGameServiceImp;
	}
	
	@GetMapping("/stories/")
	public String indexStory(Model model) {
		model.addAttribute("tsscStories", tsscStoryServiceImp.findAll());
		return "stories/index";
	}
	
	@GetMapping("/stories/add")
	public String addStory(Model model) {
		model.addAttribute("tsscStory", new TsscStory());
		model.addAttribute("tsscGames", tsscGameServiceImp.findAll());
		return "stories/add-story";
	}
	
	@PostMapping("/stories/add")
	public String saveStory(@Validated(ValidationTsscStory.class) @ModelAttribute TsscStory tsscStory, BindingResult bindingResult, @RequestParam(value = "action", required = true) String action, Model model) {
		if(action != null && !action.equals("Cancel")) {
			if(bindingResult.hasErrors()) {
				model.addAttribute("StoryBusinessValue", tsscStory.getBusinessValue());
				model.addAttribute("StoryDescription", tsscStory.getDescription());
				model.addAttribute("StoryInitialSprint", tsscStory.getInitialSprint());
				model.addAttribute("StoryPriority", tsscStory.getPriority());
				model.addAttribute("tsscGames", tsscGameServiceImp.findAll());
				
				return "stories/add-story";
			}
			tsscGameServiceImp.findById(tsscStory.getTsscGame().getId()).addTsscStory(tsscStory);
			tsscStoryServiceImp.saveStory(tsscStory);
			return "redirect:/stories/";
		} else {
			model.addAttribute("tsscStories", tsscStoryServiceImp.findAll());
		}
		return "stories/index";
	}
	
	@GetMapping("/stories/edit/{id}")
	public String showUpdateForm(@PathVariable("id") long id, Model model) {
		TsscStory tsscStory = tsscStoryServiceImp.findById(id);
		
		if(tsscStory == null)
			throw new IllegalArgumentException("Invalid story Id:" + id);
		
		model.addAttribute("tsscStory", tsscStory);
		model.addAttribute("StoryBusinessValue", tsscStory.getBusinessValue());
		model.addAttribute("StoryDescription", tsscStory.getDescription());
		model.addAttribute("StoryInitialSprint", tsscStory.getInitialSprint());
		model.addAttribute("StoryPriority", tsscStory.getPriority());
		model.addAttribute("tsscGames", tsscGameServiceImp.findAll());
				
		return "stories/update-story";
	}
	
	@PostMapping("/stories/edit/{id}")
	public String updateStory(@PathVariable("id") long id, @RequestParam(value = "action", required = true) String action, @Validated(ValidationTsscStory.class) @ModelAttribute TsscStory tsscStory, BindingResult bindingResult, Model model) {
		if(action != null && !action.equals("Cancel")) {
			if(bindingResult.hasErrors()) {
				model.addAttribute("StoryBusinessValue", tsscStory.getBusinessValue());
				model.addAttribute("StoryDescription", tsscStory.getDescription());
				model.addAttribute("StoryInitialSprint", tsscStory.getInitialSprint());
				model.addAttribute("StoryPriority", tsscStory.getPriority());
				model.addAttribute("tsscGames", tsscGameServiceImp.findAll());
				
				return "stories/update-story";
			}
			tsscStoryServiceImp.saveStory(tsscStory);
		}
		return "redirect:/stories/";
	}
	
	@GetMapping("/stories/del/{id}")
	public String deleteStory(@PathVariable("id") long id) {
		//TsscStory tsscStory = tsscStoryServiceImp.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid story Id:" + id));;
		TsscStory tsscStory = tsscStoryServiceImp.findById(id);
		tsscStoryServiceImp.deleteStory(tsscStory);
		return "redirect:/stories/";
	}
	
}