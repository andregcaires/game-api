package com.andregcaires.gameapi.core.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.andregcaires.gameapi.core.interfaces.IKillService;
import com.andregcaires.gameapi.domain.entities.Kill;

@Service
public class KillService implements IKillService {
	
	Logger logger = LoggerFactory.getLogger(KillService.class);

	public Kill getKillRecord(String line) {
		
		var killerPlayer = (line.substring(line.lastIndexOf(": ") + 1, line.indexOf("killed"))).trim();
		var killedPlayer = (line.substring(line.indexOf("killed") + 6, line.indexOf("by"))).trim();
		var reason = (line.substring(line.lastIndexOf(" by ") + 3, line.length())).trim();
		
		var kill = Kill.builder()
				.killerPlayer(killerPlayer)
				.killedPlayer(killedPlayer)
				.reason(reason)
				.build();
		
		logger.info("Kill record has been captured from log file: "+ kill.toString());
		
		return kill;
		
	}
}
