package com.andregcaires.gameapi.core.services;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;

//import com.andregcaires.gameapi.core.configurations.ServiceConfig;
import com.andregcaires.gameapi.core.interfaces.IGameInfoService;

//@ContextConfiguration(classes = ServiceConfig.class)
public class GameInfoServiceTest {
	
	@Autowired
	private IGameInfoService gameService;
	
	@Test
	public void getLogStringAndBuildGame() {
		
		String line = "  0:00 InitGame: \\sv_floodProtect\\1\\sv_maxPing\\0\\sv_minPing\\0\\sv_maxRate\\10000\\sv_minRate\\0\\sv_hostname\\Code Miner Server\\g_gametype\\0\\sv_privateClients\\2\\sv_maxclients\\16\\sv_allowDownload\\0\\dmflags\\0\\fraglimit\\20\\timelimit\\15\\g_maxGameClients\\0\\capturelimit\\8\\version\\ioq3 1.36 linux-x86_64 Apr 12 2009\\protocol\\68\\mapname\\q3dm17\\gamename\\baseq3\\g_needpass\\0";
		
		//var game = gameService.buildGame(line);
		
		//Assertions.assertEquals(game.getFloodProtect(), "1");
	}
}
