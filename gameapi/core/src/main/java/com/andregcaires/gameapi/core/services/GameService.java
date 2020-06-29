package com.andregcaires.gameapi.core.services;

import java.util.List;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.andregcaires.gameapi.context.repositories.GameRepository;
import com.andregcaires.gameapi.core.interfaces.IGameService;
import com.andregcaires.gameapi.domain.entities.Game;
import com.andregcaires.gameapi.domain.entities.KillsByPlayer;
import com.andregcaires.gameapi.domain.entities.Player;

@Service
public class GameService implements IGameService {

	@Autowired
	private GameRepository gameRepository;
	
	Logger logger = LoggerFactory.getLogger(GameService.class);
	
	public List<Game> findAll() {
		return gameRepository.findAll();
	}
	
	public Game createNewGame(Set<Player> players, List<KillsByPlayer> killsByPlayers, long totalKills) {
		
		var game = Game.builder()
				.totalKills(totalKills)
				//.killsByPlayers(killsByPlayers)
				.players(players)
				.build();
		
		return game;
	}
	
	public Game insert(Game game) {
		return gameRepository.save(game);
	}
}
