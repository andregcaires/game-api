package com.andregcaires.gameapi.core.services;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.andregcaires.gameapi.context.repositories.GameRepository;
import com.andregcaires.gameapi.core.exceptions.ObjectNotFoundException;
import com.andregcaires.gameapi.core.interfaces.IGameService;
import com.andregcaires.gameapi.domain.dto.GameDto;
import com.andregcaires.gameapi.domain.entities.Game;
import com.andregcaires.gameapi.domain.entities.KillsByPlayer;
import com.andregcaires.gameapi.domain.entities.Player;

@Service
public class GameService implements IGameService {

	@Autowired
	private GameRepository gameRepository;
	
	Logger logger = LoggerFactory.getLogger(GameService.class);
	
	public List<Map<String, GameDto>> findAll() {
		var games = gameRepository.findAll();
		
		List<Map<String, GameDto>> mapList = new ArrayList<>();
		games.forEach(g -> {
			mapList.add(createDtoFromGame(g));
		});
		
		return mapList;
	}
	
	public Game createNewGame(Set<Player> players, List<KillsByPlayer> killsByPlayers, long totalKills) {
		
		var game = Game.builder()
				.totalKills(totalKills)
				.killsByPlayers(killsByPlayers)
				.players(players)
				.build();
		
		return game;
	}
	
	public Game insert(Game game) {
		return gameRepository.save(game);
	}
	
	public Map<String, GameDto> findById(Long id) {
		var game = gameRepository.findById(id).orElseThrow(() -> {
			throw new ObjectNotFoundException("Object not found: "+ id + " Type: "+ Game.class);
		});
		
		var dto = createDtoFromGame(game);
		
		return dto;
	}
	
	public Map<String, GameDto> createDtoFromGame(Game game) {
		
		// Gets array out of player names
		var playerNames = game.getPlayers()
				.stream()
				.map((p) -> p.getName())
				.toArray(String[]::new);
		
		Map<String, Long> mapPlayersAndKills = new HashMap<>();
		game.getKillsByPlayers().forEach((k) -> {
			mapPlayersAndKills.put(k.getPlayer().getName(), k.getKills());
		});		
		
		var gameDto = GameDto.builder()
				.players(playerNames)
				.kills(mapPlayersAndKills)
				.totalKills(game.getTotalKills())
				.build();
		
		Map<String, GameDto> responseObject = new HashMap<>();
		
		var index = "game_"+ game.getId();
		
		responseObject.put(index, gameDto);
		
		return responseObject;
	}
}
