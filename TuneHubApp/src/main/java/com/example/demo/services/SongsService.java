package com.example.demo.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.demo.entities.Songs;

@Service
public interface SongsService {
	public String addSongs(Songs song);
	public boolean songExists(String name);
	public List<Songs> fetchAllSongs();
	public void updateSong(Songs song);
}
