package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.demo.entities.Playlist;
import com.example.demo.entities.Songs;
import com.example.demo.services.PlaylistService;
import com.example.demo.services.SongsService;

@Controller
public class PlaylistController {

@Autowired
PlaylistService pserv;
@Autowired
SongsService sserv;
@GetMapping("/createplaylist")
public String createPlayList(Model model) { 
	//fetching the song using song service
	List<Songs> songslist=sserv.fetchAllSongs();
	//adding the songs in the model
	model.addAttribute("songslist",songslist);
	//sending createplaylist
	return "createplaylist";
}
@PostMapping("/addplaylist")
public String addPlaylist(@ModelAttribute Playlist playlist){
	pserv.addPlaylist(playlist);
	List<Songs> songsList=playlist.getSongs();
	for(Songs song:songsList) {
		song.getPlaylist().add(playlist);
		sserv.updateSong(song);
	}
	return "playlistsuccess";
}
@GetMapping("/viewplaylist")
public String viewplaylist(Model model) {
 List<Playlist> playlist=	pserv.fetchPlaylists();
 model.addAttribute("playlist",playlist);
 return "viewplaylist";
}
}
