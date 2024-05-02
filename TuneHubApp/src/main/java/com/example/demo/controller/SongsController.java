package com.example.demo.controller;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entities.Songs;
import com.example.demo.services.SongsService;
@CrossOrigin("*")
@RestController
public class SongsController {
	@Autowired
	SongsService songserv;
	@PostMapping("/addsongs")
public String addSongs(@RequestBody Songs song) {
		String name=song.getName();
		boolean status=songserv.songExists(name);
		if (status==false) {
				songserv.addSongs(song);
	             return "Songsuccess";
		}
		else {
			return "Songfail";
		}
}
	@GetMapping("/map-viewsongs")
	public String viewSongs(Model model) {
		List<Songs> songslist=songserv.fetchAllSongs();
		model.addAttribute("songslist", songslist);
		return "displaysongs";
	}
	@GetMapping("/viewsongs")
	public List<Songs> viewCustomerSongs() {
		
			List<Songs> songslist = songserv.fetchAllSongs();
			System.out.println(songslist);
			return songslist;
		
}
}