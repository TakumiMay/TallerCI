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

import co.edu.icesi.fi.tics.tssc.model.TsscTopic;
import co.edu.icesi.fi.tics.tssc.model.ValidationTsscTopic;
import co.edu.icesi.fi.tics.tssc.service.TsscTopicServiceImp;

@Controller
public class TsscTopicController {

	TsscTopicServiceImp tsscTopicServiceImp;
	
	@Autowired
	public TsscTopicController(TsscTopicServiceImp tsscTopicServiceImp) {
		this.tsscTopicServiceImp = tsscTopicServiceImp;
	}
	
	@GetMapping("/topics/")
	public String indexTopic(Model model) {
		model.addAttribute("tsscTopics", tsscTopicServiceImp.findAll());
		return "topics/index";
	}
	
	@GetMapping("/topics/add")
	public String addTopic(Model model) {
		model.addAttribute("tsscTopic", new TsscTopic());
		return "topics/add-topic";
	}
	
	@PostMapping("/topics/add")
	public String saveTopic(@Validated(ValidationTsscTopic.class) @ModelAttribute TsscTopic tsscTopic, BindingResult bindingResult, @RequestParam(value = "action", required = true) String action, Model model) {
		if(action != null && !action.equals("Cancel")) {
			if(bindingResult.hasErrors()) {
				
				model.addAttribute("TopicName", tsscTopic.getName());
				model.addAttribute("TopicDescription", tsscTopic.getDescription());
				model.addAttribute("TopicDefaultGroups", tsscTopic.getDefaultGroups());
				model.addAttribute("TopicDefaultSprints", tsscTopic.getDefaultSprints());
				model.addAttribute("TopicGroupPrefix", tsscTopic.getGroupPrefix());
				
				return "topics/add-topic";
			}
			
			tsscTopicServiceImp.saveTopic(tsscTopic);
			
		} else {
			model.addAttribute("topics", tsscTopicServiceImp.findAll());
			return "topics/index";
		}
		return "redirect:/topics/";
	}
	
	@GetMapping("/topics/edit/{id}")
	public String showUpdateForm(@PathVariable("id") long id, Model model) {
		TsscTopic tsscTopic = tsscTopicServiceImp.findById(id);
		
		if (tsscTopic == null)
			throw new IllegalArgumentException("Invalid topic Id:" + id);
		
		model.addAttribute("tsscTopic", tsscTopic);
		model.addAttribute("TopicName", tsscTopic.getName());
		model.addAttribute("TopicDescription", tsscTopic.getDescription());
		model.addAttribute("TopicDefaultGroups", tsscTopic.getDefaultGroups());
		model.addAttribute("TopicDefaultSprints", tsscTopic.getDefaultSprints());
		model.addAttribute("TopicGroupPrefix", tsscTopic.getGroupPrefix());

		return "topics/update-topic";
	}
	
	@PostMapping("/topics/edit/{id}")
	public String updateTopic(@PathVariable("id") long id, @RequestParam(value = "action", required = true) String action, @Validated(ValidationTsscTopic.class) @ModelAttribute TsscTopic tsscTopic, BindingResult bindingResult, Model model) {
		if(action != null && !action.equals("Cancel")) {
			tsscTopicServiceImp.saveTopic(tsscTopic);
		}
		if(bindingResult.hasErrors()) {
			
			model.addAttribute("TopicName", tsscTopic.getName());
			model.addAttribute("TopicDescription", tsscTopic.getDescription());
			model.addAttribute("TopicDefaultGroups", tsscTopic.getDefaultGroups());
			model.addAttribute("TopicDefaultSprints", tsscTopic.getDefaultSprints());
			model.addAttribute("TopicGroupPrefix", tsscTopic.getGroupPrefix());
			
			return "topics/update-topic";
		}	
		if(action != null && action.equals("Cancel")) {
			return "redirect:/topics/";
		}
		
		return "redirect:/topics/";
	}
	
	@GetMapping("/topics/del/{id}")
	public String deleteTopic(@PathVariable("id") long id) {
		//TsscTopic tsscTopic = tsscTopicServiceImp.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid topic Id:" + id));
		TsscTopic tsscTopic = tsscTopicServiceImp.findById(id);
		tsscTopicServiceImp.deleteTopic(tsscTopic);
		return "redirect:/topics/";
	}
	
}