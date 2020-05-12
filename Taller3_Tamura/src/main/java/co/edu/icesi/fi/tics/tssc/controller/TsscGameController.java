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

import co.edu.icesi.fi.tics.tssc.model.TsscGame;
import co.edu.icesi.fi.tics.tssc.model.ValidationTsscGame;
import co.edu.icesi.fi.tics.tssc.service.TsscGameServiceImp;
import co.edu.icesi.fi.tics.tssc.service.TsscTopicServiceImp;

@Controller
public class TsscGameController {
	
	TsscGameServiceImp tsscGameServiceImp;
	
	TsscTopicServiceImp tsscTopicServiceImp;
		
	@Autowired
	public TsscGameController(TsscGameServiceImp tsscGameServiceImp, TsscTopicServiceImp tsscTopicServiceImp) {
		this.tsscGameServiceImp = tsscGameServiceImp;
		this.tsscTopicServiceImp = tsscTopicServiceImp;
	}
	
	@GetMapping("/games/")
	public String indexGame(Model model) {
		model.addAttribute("tsscGames", tsscGameServiceImp.findAll());
		return "games/index";
	}
	
	@GetMapping("/games/stories/{id}")
	public String showStories(@PathVariable("id") long id, Model model) {
		TsscGame tsscGame = tsscGameServiceImp.findById(id);
		model.addAttribute("tsscGame", tsscGame);
		model.addAttribute("stories", tsscGame.getTsscStories());
		return "games/stories";
	}
	
	@GetMapping("/games/add")
	public String addGame(Model model) {
		model.addAttribute("tsscGame", new TsscGame());
		model.addAttribute("tsscTopics", tsscTopicServiceImp.findAll());
		return "games/add-game";
	}
	
	@PostMapping("/games/add")
	public String saveGame(@Validated(ValidationTsscGame.class) @ModelAttribute TsscGame tsscGame, BindingResult bindingResult, @RequestParam(value = "action", required = true) String action, Model model) {
		if(action != null && !action.equals("Cancel")) {
			if(bindingResult.hasErrors()) {
				model.addAttribute("GameName", tsscGame.getName());
				model.addAttribute("GameDate", tsscGame.getScheduledDate());
				model.addAttribute("GameTime", tsscGame.getScheduledTime());
				model.addAttribute("GameGroups", tsscGame.getNGroups());
				model.addAttribute("GameSprints", tsscGame.getNSprints());
				model.addAttribute("GameAdminPwd", tsscGame.getAdminPassword());
				model.addAttribute("GameUserPwd", tsscGame.getUserPassword());
				model.addAttribute("GameGuestPwd", tsscGame.getGuestPassword());
				model.addAttribute("tsscTopics", tsscTopicServiceImp.findAll());
					
				return "games/add-game";
			}
				tsscGameServiceImp.saveGame(tsscGame);
		} else {
			model.addAttribute("games", tsscGameServiceImp.findAll());
			return "games/index";
		}
		
		return "redirect:/games/";
	}
	
	@GetMapping("/games/edit/{id}")
	public String showUpdateForm(@PathVariable("id") long id, Model model) {
		TsscGame tsscGame = tsscGameServiceImp.findById(id);
		
		model.addAttribute("tsscGame", tsscGame);
		model.addAttribute("GameName", tsscGame.getName());
		model.addAttribute("GameDate", tsscGame.getScheduledDate());
		model.addAttribute("GameTime", tsscGame.getScheduledTime());
		model.addAttribute("GameGroups", tsscGame.getNGroups());
		model.addAttribute("GameSprints", tsscGame.getNSprints());
		model.addAttribute("GameAdminPwd", tsscGame.getAdminPassword());
		model.addAttribute("GameUserPwd", tsscGame.getUserPassword());
		model.addAttribute("GameGuestPwd", tsscGame.getGuestPassword());
		model.addAttribute("tsscTopics", tsscTopicServiceImp.findAll());
			
		return "games/update-game";
	}
	
	@PostMapping("/games/edit/{id}")
	public String updateGame(@PathVariable("id") long id, @RequestParam(value = "action", required = true) String action, @Validated(ValidationTsscGame.class) @ModelAttribute TsscGame tsscGame, BindingResult bindingResult, Model model) {
		if(action != null && !action.equals("Cancel")) {
			if(bindingResult.hasErrors()) {
				model.addAttribute("tsscGame", tsscGame);
				model.addAttribute("GameName", tsscGame.getName());
				model.addAttribute("GameDate", tsscGame.getScheduledDate());
				model.addAttribute("GameTime", tsscGame.getScheduledTime());
				model.addAttribute("GameGroups", tsscGame.getNGroups());
				model.addAttribute("GameSprints", tsscGame.getNSprints());
				model.addAttribute("GameAdminPwd", tsscGame.getAdminPassword());
				model.addAttribute("GameUserPwd", tsscGame.getUserPassword());
				model.addAttribute("GameGuestPwd", tsscGame.getGuestPassword());
				model.addAttribute("tsscTopics", tsscTopicServiceImp.findAll());
				
				return "games/update-game";
			}
		}
		return "redirect:/games/";
	}
	
	@GetMapping("/games/del/{id}")
	public String deleteGame(@PathVariable("id") long id) {
		//TsscGame tsscGame = tsscGameServiceImp.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid game Id:" + id));
		TsscGame tsscGame = tsscGameServiceImp.findById(id);
		tsscGameServiceImp.deleteGame(tsscGame);
		return "redirect:/games/";
	}
	
}