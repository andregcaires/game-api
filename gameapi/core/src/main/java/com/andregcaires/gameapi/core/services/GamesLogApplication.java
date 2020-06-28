package com.andregcaires.gameapi.core.services;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;

import com.andregcaires.gameapi.core.interfaces.IGameInfoService;
import com.andregcaires.gameapi.core.interfaces.IGameService;
import com.andregcaires.gameapi.core.interfaces.IGamesLogApplication;
import com.andregcaires.gameapi.core.interfaces.IKillService;
import com.andregcaires.gameapi.core.interfaces.IPlayerService;
import com.andregcaires.gameapi.domain.entities.GameInfo;
import com.andregcaires.gameapi.domain.entities.Kill;
import com.andregcaires.gameapi.domain.entities.KillsByPlayer;
import com.andregcaires.gameapi.domain.entities.Player;
import com.andregcaires.gameapi.domain.utilities.Keys;

@Service
public class GamesLogApplication implements IGamesLogApplication {
	
	@Value("classpath:games.log")
	Resource resourceFile;
	
	@Autowired
	private IGameInfoService gameInfoService;
	
	@Autowired
	private IKillService killService;
	
	@Autowired
	private IPlayerService playerService;
	
	@Autowired
	private IGameService gameService;
	
	private final String WORLD = "<world>";
	
	Logger logger = LoggerFactory.getLogger(GamesLogApplication.class);

	public void parser() {
		
		GameInfo gameInfo;
		List<Kill> killsList = new ArrayList<>();
		List<KillsByPlayer> killsByPlayerList = new ArrayList<>();
		Set<Player> playerList = new HashSet<>();
		
		//Map<String, Long> killsByPlayer = new HashMap<String, Long>();
		
		InputStream inputStream;
		
		try {
			
			logger.info("Getting input stream...");
			inputStream = resourceFile.getInputStream();
			logger.info("Done!");
			
			try (BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream))) {
				
		        while (reader.ready()) {
		        	
		            var line = reader.readLine();
		            
		            if (line.contains(Keys.INITGAME)) {
		            	gameInfo = gameInfoService.buildGame(line);
		            	logger.info("A new game has been initialized");
		            }
		            else if (line.contains(Keys.CLIENTUSERINFOCHANGED)) {
		            	var player = playerService.getClientUserInfo(line);
		            	
		            	if (!playerList.contains(player)) {
		            		playerList.add(player);		            		
		            	}
		            }
		            else if (line.contains(Keys.KILL)) {
		            	var kill = killService.getKillRecord(line);
		            	killsList.add(kill);
		            }
		            else if (line.contains(Keys.SHUTDOWNGAME)) {
		            	
		            	logger.info("A game has been shutdown");
		            	
		            	var totalGameKillsWrapper = killService.getKillsByPlayerList(playerList, killsList);
		            	
		            	// TODO salva Game
		            	gameService.createNewGame(playerList, 
		            			totalGameKillsWrapper.getKillsByPlayerList(), 
		            			totalGameKillsWrapper.getTotalKills());
		            	
		            	// Clears lists used for current game
		            	killsByPlayerList.clear();
		            	killsList.clear();
		            	playerList.clear();
		            	
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
