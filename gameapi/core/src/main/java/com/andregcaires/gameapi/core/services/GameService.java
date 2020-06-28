package com.andregcaires.gameapi.core.services;

import java.util.List;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.andregcaires.gameapi.context.repositories.GameRepository;
import com.andregcaires.gameapi.core.interfaces.IGameService;
import com.andregcaires.gameapi.domain.entities.Game;
import com.andregcaires.gameapi.domain.entities.KillsByPlayer;
import com.andregcaires.gameapi.domain.entities.Player;

@Service
public class GameService implements IGameService {

	//@Autowired
	private GameRepository gameRepository;
	
	Logger logger = LoggerFactory.getLogger(GameService.class);
	
	public void createNewGame(Set<Player> players, List<KillsByPlayer> killsByPlayers, long totalKills) {
		
		Game g = Game.builder()
				.totalKills(totalKills)
				.killsByPlayers(killsByPlayers)
				.players(players)
				.build();
		
		// TODO save game
		
		System.out.println(g.toString());
		System.out.println("-----");
	}
}
