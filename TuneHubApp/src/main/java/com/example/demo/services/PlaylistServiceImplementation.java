package com.example.demo.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entities.Playlist;
import com.example.demo.repositories.playlistRepository;

@Service
public class PlaylistServiceImplementation implements PlaylistService{
		@Autowired
		playlistRepository prep;

		@Override
		public void addPlaylist(Playlist playlist) {
			prep.save(playlist);
		}
		@Override
		public List<Playlist> fetchPlaylists() {
			return prep.findAll();
		}
}

