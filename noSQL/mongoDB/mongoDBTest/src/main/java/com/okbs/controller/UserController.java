package com.okbs.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.okbs.document.UserDocument;
import com.okbs.service.UserService;

import lombok.RequiredArgsConstructor;

@Controller
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {
	private final UserService userService;  
	
	@GetMapping("/select")
	public String selectUser(Model model) {
		List<UserDocument> userList = userService.selectAllUsers();
		System.out.println(userList);
		model.addAttribute("userList", userList);
		return "user/select";
	}
	
	@GetMapping("/insert")
	public String insertUser(UserDocument userDocument) {
		return "user/insert";
	}
	
	@PostMapping("/insert")
	public String insertUserProcedure(UserDocument userDocument,@RequestParam("id") String id ) {
		//userService.createUser(userDocument);
//		System.out.println(id);
		userDocument.set_id(id);
//		System.out.println("_id 의 값 "+userDocument.get_id());
//		System.out.println("name 의 값 "+userDocument.getName());
//		System.out.println("pw 의 값 "+userDocument.getPw());
		userService.createUser2(userDocument);
		return "index";
	}
	
	@GetMapping("/update")
	public String updateUser(UserDocument userDocument, @RequestParam("id") String id,Model model) {
		userDocument = userService.selectById(id);
		model.addAttribute("userDocument", userDocument);
		model.addAttribute("id", id);
		return "user/update";
	}
	
	@PostMapping("/update")
	public String updateUserProcedure(UserDocument userDocument, @RequestParam("id") String id) {
		userService.updateUser(id, userDocument);
		return "redirect:/user/select";
	}
	
	@GetMapping("/delete")
	public String deleteUser(@RequestParam("id") String id) {
		userService.deleteUser(id);
		return "redirect:/user/select";
		
	}
}
