package com.andregcaires.gameapi.core.services;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;

import com.andregcaires.gameapi.core.interfaces.IGameInfoService;
import com.andregcaires.gameapi.domain.entities.GameInfo;
import com.andregcaires.gameapi.domain.utilities.Keys;

@TestInstance(Lifecycle.PER_CLASS)
public class GameInfoServiceTest {
	
	private IGameInfoService gameInfoService;
	
	@BeforeAll
	void setup() {
		gameInfoService = new GameInfoService();
	}
	
	@Test
	public void buildGameFromFile() {
		
		GameInfo gameInfo = null;
		String line = "  0:00 InitGame: \\sv_floodProtect\\1\\sv_maxPing\\0\\sv_minPing\\0\\sv_maxRate\\10000\\sv_minRate\\0\\sv_hostname\\Code Miner Server\\g_gametype\\0\\sv_privateClients\\2\\sv_maxclients\\16\\sv_allowDownload\\0\\dmflags\\0\\fraglimit\\20\\timelimit\\15\\g_maxGameClients\\0\\capturelimit\\8\\version\\ioq3 1.36 linux-x86_64 Apr 12 2009\\protocol\\68\\mapname\\q3dm17\\gamename\\baseq3\\g_needpass\\0";
		
        if (line.contains(Keys.INITGAME)) {
        	gameInfo = gameInfoService.buildGame(line);
        }
        
        Assertions.assertNotNull(gameInfo);

        Assertions.assertEquals("1", gameInfo.getFloodProtect());
        Assertions.assertEquals(0, gameInfo.getMaxPing());
        Assertions.assertEquals(0, gameInfo.getMinPing());
        Assertions.assertEquals(0, gameInfo.getMinRate());
        Assertions.assertEquals(10000, gameInfo.getMaxRate());
        Assertions.assertEquals("Code Miner Server", gameInfo.getHostName());
        Assertions.assertEquals("0", gameInfo.getGameType());
        Assertions.assertEquals(2, gameInfo.getPrivateClients());
        Assertions.assertEquals(16, gameInfo.getMaxClients());
        Assertions.assertEquals(false, gameInfo.isAllowDownload());
        Assertions.assertEquals(0, gameInfo.getDmFlags());
        Assertions.assertEquals(20, gameInfo.getFragLimit());
        Assertions.assertEquals(15, gameInfo.getTimeLimit());
        Assertions.assertEquals(0, gameInfo.getMaxGameClients());
        Assertions.assertEquals(8, gameInfo.getCaptureLimit());
        Assertions.assertEquals("ioq3 1.36 linux-x86_64 Apr 12 2009", gameInfo.getVersion());
        Assertions.assertEquals("68", gameInfo.getProtocol());
        Assertions.assertEquals("q3dm17", gameInfo.getMapName());
        Assertions.assertEquals("baseq3", gameInfo.getGameName());
        Assertions.assertEquals(false, gameInfo.isNeedPass());
	}
}
