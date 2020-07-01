package com.andregcaires.gameapi.core.interfaces;

import java.util.List;
import java.util.Set;

import org.springframework.stereotype.Service;

import com.andregcaires.gameapi.domain.entities.Kill;
import com.andregcaires.gameapi.domain.entities.KillsByPlayer;
import com.andregcaires.gameapi.domain.entities.Player;

@Service
public interface IKillService {

	Kill getKillRecord(String line);
	
	List<KillsByPlayer> getTotalAndIndividualKills(Set<Player> playerList, List<Kill> killsList);
	
	List<KillsByPlayer> insert(List<KillsByPlayer> list);
	
	List<KillsByPlayer> findAll();
	
	long getPlayerKillsByWorld(String playerName, List<Kill> killsList);
	
	long getTotalPlayerKills(String playerName, List<Kill> killsList);
}
