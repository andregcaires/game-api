package com.andregcaires.gameapi.core.services;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;

import com.andregcaires.gameapi.core.interfaces.IPlayerService;
import com.andregcaires.gameapi.core.interfaces.IGameInfoService;
import com.andregcaires.gameapi.core.interfaces.IGamesLogApplication;
import com.andregcaires.gameapi.core.interfaces.IKillService;
import com.andregcaires.gameapi.domain.entities.Player;
import com.andregcaires.gameapi.domain.entities.GameInfo;
import com.andregcaires.gameapi.domain.entities.Kill;
import com.andregcaires.gameapi.domain.utilities.Keys;

@Service
public class GamesLogApplication implements IGamesLogApplication {
	
	@Value("classpath:games.log")
	Resource resourceFile;
	
	@Autowired
	private IGameInfoService gameService;
	
	@Autowired
	private IKillService killService;
	
	@Autowired
	private IPlayerService clientUserInfoService;
	
	private final String WORLD = "<world>";
	
	Logger logger = LoggerFactory.getLogger(GamesLogApplication.class);

	public void parser() {
		
		int lineCounter = 0;
		long totalKills = 0;
		long individualKills = 0;
		GameInfo game;
		List<Kill> killsList = new ArrayList<>();
		List<Player> clientUserInfoList = new ArrayList<>();
		
		Map<String, Long> killsByPlayer = new HashMap<String, Long>();
		
		InputStream inputStream;
		
		try {
			
			logger.info("Getting inputs stream...");
			inputStream = resourceFile.getInputStream();
			logger.info("Done!");
			
			try (BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream))) {
				
		        while (reader.ready()) {
		        	
		            var line = reader.readLine();
		            
		            if (line.contains(Keys.INITGAME)) {
		            	game = gameService.buildGame(line);
		            	logger.info("A new game has been initialized");
		            }
		            else if (line.contains(Keys.CLIENTUSERINFOCHANGED)) {
		            	var client = clientUserInfoService.getClientUserInfo(line);
		            	clientUserInfoList.add(client);
		            }
		            else if (line.contains(Keys.KILL)) {
		            	var kill = killService.getKillRecord(line);
		            	killsList.add(kill);
		            }
		            else if (line.contains(Keys.SHUTDOWNGAME)) {
		            	
		            	logger.info("A game has been shutdown");
		            	//TODO save items, flush lists
		            	
		            	for (Player player : clientUserInfoList) {
		            		individualKills = killsList
		            				.stream()
		            				.filter((t) -> t.getKillerPlayer() == player.getName())
		            				.count();
		            		
		            		totalKills += individualKills;
		            		
		            		// Decrease number of world kills from individual player's kills
		            		individualKills -= killsList
		            				.stream()
		            				.filter((t) -> t.getKilledPlayer() == player.getName()
		            						&& t.getKillerPlayer() == WORLD)
		            				.count();
		            		
		            		killsByPlayer.put(player.getName(), individualKills);
		            	}
		            }
		        }
		        
		    } catch (Exception e) {
		    	logger.error("An error has ocurred: "+ e.getMessage());
		        e.printStackTrace();
		    }
		} catch (IOException e) {
			logger.error("An error has ocurred: "+ e.getMessage());
			e.printStackTrace();
		}		

	}
}
