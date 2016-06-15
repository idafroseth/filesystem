package com.svenskeknappen.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.svenskeknappen.service.RepositoryService;
import com.svenskeknappen.model.User;

@Controller
@RequestMapping("/admin")
public class AdminController {
	
	@Autowired
	RepositoryService repositoryService;

	// @RequestMapping(value ="/user/new", method=RequestMethod.POST)
	// public String addUser(@RequestParam(value=""){
	//
	// }
	@RequestMapping(value ="/user", method=RequestMethod.GET) 
	public String getUsers(ModelMap model){
		if(repositoryService ==null){
			model.addAttribute("users", "ERROR");
		}else{
			List<User> users = repositoryService.getAllUsers();
			model.addAttribute("users", users);
		}
		return "user";
	}
	
	@RequestMapping(value = "/user/{id}/delete", method=RequestMethod.GET)
	public String deleteUser(ModelMap model, @PathVariable("id") String username){
		if(repositoryService ==null){
			model.addAttribute("users", "ERROR");
		}else{
			repositoryService.deleteUser(username);
			List<User> users = repositoryService.getAllUsers();
			model.addAttribute("users", users);
			
		}
		return "user";
	}
	
	@RequestMapping(value = "/user/{name}/remove/{role}", method=RequestMethod.GET)
	public String removeRoleFromUser(ModelMap model, @PathVariable("name") String username, @PathVariable("role") String role){
		if(repositoryService ==null){
			model.addAttribute("users", "ERROR");
		}else{
			repositoryService.removeRoleFromUser(username, role);
			List<User> users = repositoryService.getAllUsers();
			model.addAttribute("users", users);
			
		}
		return "user";
	}
}
