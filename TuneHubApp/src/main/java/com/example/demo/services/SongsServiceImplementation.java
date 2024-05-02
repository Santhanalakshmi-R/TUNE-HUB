package com.example.demo.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entities.Songs;
import com.example.demo.repositories.SongsRepository;

@Service
public class SongsServiceImplementation implements SongsService {
	@Autowired
SongsRepository srep;
	@Override
	public String addSongs(Songs song) {
		srep.save(song);
		return "Song is added";
	}
	@Override
	public boolean songExists(String name) {
	Songs song=	srep.findByName(name);
	if(song==null)
		return false;
	else 
		return true;
	}
	@Override
	public List<Songs> fetchAllSongs() {
	  List<Songs> songsList=	srep.findAll();
	  System.out.println(songsList);
		return songsList;
	}
	@Override
	public void updateSong(Songs song) {
		srep.save(song);
	}

	

}
