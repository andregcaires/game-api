package com.andregcaires.gameapi.core.services;

import java.util.HashMap;
import java.util.regex.Pattern;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.andregcaires.gameapi.context.repositories.PlayerRepository;
import com.andregcaires.gameapi.core.interfaces.IPlayerService;
import com.andregcaires.gameapi.domain.entities.Player;

@Service
public class PlayerService implements IPlayerService {
	
	private final String lineSeparator = "\\";
	
	@Autowired
	private PlayerRepository repository;
	
	Logger logger = LoggerFactory.getLogger(PlayerService.class);

	public Player getClientUserInfo(String line) {
		
		var map = new HashMap<String, String>();
		
    	var splitUpLineArray = line
    			.replaceAll(Pattern.quote(lineSeparator), "\\\\")
    			.split("\\\\");
    	
    	var foundPlayerName = splitUpLineArray[1];
    	
    	var player = findPlayerByName(foundPlayerName);
    	
    	if (player == null) {
    		
    		player = Player.builder()
    				.name(foundPlayerName)
    				.build();
    		
    		player = insertNewPlayer(player);
    	}    	
    	
    	logger.info("Game has been captured from log file: "+ player.toString());
    	System.out.println(player.toString());
    	return player;
	}
	
	public Player findPlayerByName(String playerName) {
		return repository.findByName(playerName);
	}
	
	public Player insertNewPlayer(Player player) {
		return repository.save(player);
	}
}
