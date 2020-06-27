package com.andregcaires.gameapi.core.interfaces;

import org.springframework.stereotype.Service;

import com.andregcaires.gameapi.domain.entities.Game;

@Service
public interface IGameService {

	Game buildGame(String initGameLine);
	
}
