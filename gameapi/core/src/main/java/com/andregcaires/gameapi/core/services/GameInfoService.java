package com.andregcaires.gameapi.core.services;

import java.util.HashMap;
import java.util.regex.Pattern;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.andregcaires.gameapi.context.repositories.GameInfoRepository;
import com.andregcaires.gameapi.core.exceptions.ObjectNotFoundException;
import com.andregcaires.gameapi.core.interfaces.IGameInfoService;
import com.andregcaires.gameapi.domain.entities.GameInfo;

@Service
public class GameInfoService implements IGameInfoService {

	@Autowired
	private GameInfoRepository gameInfoRepository;

	private final String lineSeparator = "\\";

	Logger logger = LoggerFactory.getLogger(GameInfoService.class);

	public GameInfo parseInitGameLine(String line) {

		var map = new HashMap<String, String>();

		var splitUpLineArray = line.replaceAll(Pattern.quote(lineSeparator), "\\\\").split("\\\\");

		for (int i = 1; i < splitUpLineArray.length; i += 2) {
			map.put(splitUpLineArray[i], splitUpLineArray[i + 1]);
		}

		var gameInfo = GameInfo.builder().needPass(map.get("g_needpass") == "1").gameName(map.get("gamename"))
				.mapName(map.get("mapname")).protocol(map.get("protocol")).version(map.get("version"))
				.maxGameClients(Integer.parseInt(map.get("g_maxGameClients")))
				.timeLimit(Integer.parseInt(map.get("timelimit"))).fragLimit(Integer.parseInt(map.get("fraglimit")))
				.dmFlags(Integer.parseInt(map.get("dmflags"))).allowDownload(map.get("sv_allowDownload") == "1")
				.maxClients(Integer.parseInt(map.get("sv_maxclients")))
				.privateClients(Integer.parseInt(map.get("sv_privateClients"))).gameType(map.get("g_gametype"))
				.hostName(map.get("sv_hostname")).maxRate(Integer.parseInt(map.get("sv_maxRate")))
				.minRate(Integer.parseInt(map.get("sv_minRate"))).minPing(Integer.parseInt(map.get("sv_minPing")))
				.maxPing(Integer.parseInt(map.get("sv_maxPing"))).floodProtect(map.get("sv_floodProtect"))
				.captureLimit(Integer.parseInt(map.get("capturelimit"))).build();

		return gameInfo;
	}

	public GameInfo insert(GameInfo gameInfo) {
		return gameInfoRepository.save(gameInfo);
	}

	public GameInfo findByGameId(Long id) {
		return gameInfoRepository.findById(id).orElseThrow(() -> {
			throw new ObjectNotFoundException("Object not found: " + id + " Type: " + GameInfo.class);
		});
	}
}
