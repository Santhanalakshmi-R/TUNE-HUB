package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entities.LoginData;
import com.example.demo.entities.Songs;
import com.example.demo.entities.Users;
import com.example.demo.services.SongsService;
import com.example.demo.services.UsersService;

import jakarta.servlet.http.HttpSession;
@CrossOrigin("*")
@RestController
public class usersController {
	@Autowired
	UsersService userv;
	@Autowired
	SongsService sserv;
	@PostMapping("/register")
  public String addUser(@RequestBody Users user) {
	boolean userstatus=userv.emailExits(user.getEmail());
	if(userstatus==false) {
		userv.addUser(user);
		return "registersuccess";
	}
	else {
	  return "registerfail";
	}
  }
	@PostMapping("/login")
	public String valudateUser(@RequestBody LoginData data,HttpSession session)
	{
		boolean loginsts=userv.validateUser(data.getEmail(), data.getPassword());
		if(loginsts==true) {
			session.setAttribute("email", data.getEmail());
			String role=userv.getRole(data.getEmail());
			if(role.equals("Admin")) {
			return "Adminhome";}
			else {
			return "customerhome";}
		}
		else {
			return "loginfail";
		}
	}
	@GetMapping("/exploreSongs")
	public String exploreSongs(HttpSession session,Model model){
		  String email=  (String)	session.getAttribute("email");
		Users user=userv.getUser(email);
		boolean userStatus=user.isPremium();
		if(userStatus) {
			List<Songs> songslist=sserv.fetchAllSongs();
			model.addAttribute("songslist", songslist);
			return "displaysongs"; 
		}
		else {
			return "payment";
		}
	}
}