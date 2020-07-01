package com.andregcaires.gameapi.core;

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
	
	Logger logger = LoggerFactory.getLogger(GamesLogApplication.class);

	public void parser() {
		
		List<Kill> killsList = new ArrayList<>();
		List<KillsByPlayer> killsByPlayerList = new ArrayList<>();
		Set<Player> playerList = new HashSet<>();
		long totalGameKills = 0;
		
		GameInfo gameInfo = new GameInfo();;
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
		            	logger.info("A new game has been captured from log file: "+ gameInfo.toString());
		            }
		            else if (line.contains(Keys.CLIENTUSERINFOCHANGED)) {
		            	var player = playerService.getClientUserInfo(line);
		            	
		            	// if found player not exists in database, inserts it
		            	player = playerService.insertPlayerIfNotExists(player);
		            	
		            	if (!playerList.contains(player)) {
		            		playerList.add(player);		            		
		            	}
		            	logger.info("Game has been captured from log file: "+ player.toString());
		            }
		            else if (line.contains(Keys.KILL)) {
		            	var kill = killService.getKillRecord(line);
		            	killsList.add(kill);
		            	totalGameKills++;
		            	logger.info("Kill record has been captured from log file: "+ kill.toString());
		            }
		            else if (line.contains(Keys.SHUTDOWNGAME)) {
		            	
		            	logger.info("A game has been shutdown");
		            	
		            	killsByPlayerList = killService
		            			.getTotalAndIndividualKills(playerList, killsList);
		            	
		            	var game = gameService.createNewGame(playerList, 
		            			killsByPlayerList, 
		            			totalGameKills);
		            	
		            	killsByPlayerList.forEach(item -> item.setGame(game));
		            	
		            	gameInfo.setGame(game);
		            	
		            	// Save both game and kills by player
	            		gameService.insert(game);
		            	killService.insert(killsByPlayerList);
		            	gameInfoService.insert(gameInfo);
		            	
		            	// Clears lists used for current game
		            	killsByPlayerList.clear();
		            	killsList.clear();
		            	playerList.clear();
		            	
		            	totalGameKills = 0;
		            	
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
