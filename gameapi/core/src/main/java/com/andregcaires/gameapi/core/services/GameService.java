package com.andregcaires.gameapi.core.services;

import java.util.HashMap;
import java.util.regex.Pattern;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.andregcaires.gameapi.core.interfaces.IGameService;
import com.andregcaires.gameapi.domain.entities.Game;

@Service
public class GameService implements IGameService {
	
	private final String initGameLineSeparator = "\\";
	
	Logger logger = LoggerFactory.getLogger(GameService.class);

	public Game buildGame(String initGameLine) {
		
		var map = new HashMap<String, String>();
		
    	var initGameProperties = initGameLine
    			.replaceAll(Pattern.quote(initGameLineSeparator), "\\\\")
    			.split("\\\\");
    	
    	for (int i = 1; i < initGameProperties.length; i += 2) {    
    		map.put(initGameProperties[i], initGameProperties[i + 1]);   
    	}
    	
    	var game = Game.builder()
    			.needPass(map.get("g_needpass") == "1")
    			.gameName(map.get("gamename"))
    			.mapName(map.get("mapname"))
    			.protocol(map.get("protocol"))
    			.version(map.get("version"))
    			.maxGameClients(Integer.parseInt(map.get("g_maxGameClients")))
    			.timeLimit(Integer.parseInt(map.get("timelimit")))
    			.fragLimit(Integer.parseInt(map.get("fraglimit")))
    			.dmFlags(Integer.parseInt(map.get("dmflags")))
    			.allowDownload(map.get("sv_allowDownload") == "1")
    			.maxClients(Integer.parseInt(map.get("sv_maxclients")))
    			.privateClients(Integer.parseInt(map.get("sv_privateClients")))
    			.gameType(map.get("g_gametype"))
    			.hostName(map.get("sv_hostname"))
    			.maxRate(Integer.parseInt(map.get("sv_maxRate")))
    			.minRate(Integer.parseInt(map.get("sv_maxRate")))
    			.minPing(Integer.parseInt(map.get("sv_minPing")))
    			.maxPing(Integer.parseInt(map.get("sv_maxPing")))
    			.floodProtect(map.get("sv_floodProtect"))
    			.captureLimit(Integer.parseInt(map.get("capturelimit")))
    			.build();
    	
    	logger.info("Game has been captured from log file: "+ game.toString());
		
		return game;
	}
}
