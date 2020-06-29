package com.andregcaires.gameapi.core.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.andregcaires.gameapi.context.repositories.KillRepository;
import com.andregcaires.gameapi.core.interfaces.IKillService;
import com.andregcaires.gameapi.domain.entities.Kill;
import com.andregcaires.gameapi.domain.entities.KillsByPlayer;
import com.andregcaires.gameapi.domain.entities.Player;
import com.andregcaires.gameapi.domain.utilities.TotalGameKillsWrapper;

@Service
public class KillService implements IKillService {
	
	private final String WORLD = "<world>";
	
	Logger logger = LoggerFactory.getLogger(KillService.class);
	
	@Autowired
	private KillRepository killRepository;

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
	
	public TotalGameKillsWrapper getTotalAndIndividualKills(Set<Player> playerList, List<Kill> killsList) {
		
		long individualKills = 0, totalKills = 0;
		
		List<KillsByPlayer> killsByPlayerList = new ArrayList<>();
		
    	for (Player player : playerList) {
    		
    		// Sums each players kills
    		individualKills = getTotalPlayerKills(player.getName(), killsList);
    		
    		// Increments total kills for current game
    		totalKills += individualKills;
    		
    		// Decreases number of world kills from individual player's kills
    		individualKills -= getPlayerKillsByWorld(player.getName(), killsList);
    		
    		// Updates to 0 when world kills are greater than player's
    		individualKills = individualKills > 0 ? individualKills : 0;
    		
    		var killsByPlayer = new KillsByPlayer();
    		
    		killsByPlayer.getId().setPlayer(player);
    		killsByPlayer.setKills(individualKills);
    		
    		killsByPlayerList.add(killsByPlayer);
    		
    	}
    	
    	/*
    	 * In order to avoid iterate over playerList multiple times,
    	 * this Wrapper class is used to encapsulate both totalkills
    	 * and total kills by player
    	 * */
    	var totalGameKills = new TotalGameKillsWrapper();
    	totalGameKills.getKillsByPlayerList().addAll(killsByPlayerList);
    	totalGameKills.setTotalKills(totalKills);
    	System.out.println(totalGameKills);
    	return totalGameKills;
	}
	
	public List<KillsByPlayer> insert(List<KillsByPlayer> list) {
		list.forEach(System.out::println);
		return killRepository.saveAll(list);
	}
	
	private long getTotalPlayerKills(String playerName, List<Kill> killsList) {
    	return killsList
				.stream()
				.filter((t) -> t.getKillerPlayer().equals(playerName))
				.count();
	}
	
	private long getPlayerKillsByWorld(String playerName, List<Kill> killsList) {
    	return killsList
				.stream()
				.filter((t) -> t.getKilledPlayer().equals(playerName) 
						&& t.getKillerPlayer().equals(WORLD))
				.count();
	}
}
