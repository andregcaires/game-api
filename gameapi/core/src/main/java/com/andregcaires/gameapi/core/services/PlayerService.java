package com.andregcaires.gameapi.core.services;

import java.util.HashMap;
import java.util.regex.Pattern;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.andregcaires.gameapi.core.interfaces.IPlayerService;
import com.andregcaires.gameapi.domain.entities.Player;

@Service
public class PlayerService implements IPlayerService {
	
	private final String lineSeparator = "\\";
	
	Logger logger = LoggerFactory.getLogger(PlayerService.class);

	public Player getClientUserInfo(String line) {
		
		var map = new HashMap<String, String>();
		
    	var splitUpLineArray = line
    			.replaceAll(Pattern.quote(lineSeparator), "\\\\")
    			.split("\\\\");
    	
    	// TODO change to repository call
    	var clientUserInfo = Player.builder()
    			.name(splitUpLineArray[1])
    			.build();
    	
    	logger.info("Game has been captured from log file: "+ clientUserInfo.toString());
    	System.out.println(clientUserInfo.toString());
    	return clientUserInfo;
	}
}
