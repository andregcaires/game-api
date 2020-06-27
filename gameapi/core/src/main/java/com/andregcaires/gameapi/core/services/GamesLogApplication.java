package com.andregcaires.gameapi.core.services;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;

import com.andregcaires.gameapi.core.interfaces.IGameService;
import com.andregcaires.gameapi.core.interfaces.IGamesLogApplication;
import com.andregcaires.gameapi.domain.entities.Game;
import com.andregcaires.gameapi.domain.utilities.Keys;

@Service
public class GamesLogApplication implements IGamesLogApplication {
	
	@Value("classpath:games.log")
	Resource resourceFile;
	
	@Autowired
	private IGameService gameService;
	
	Logger logger = LoggerFactory.getLogger(GamesLogApplication.class);

	public void parser() {
		
		int lineCounter = 0;
		Game game;
		
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
