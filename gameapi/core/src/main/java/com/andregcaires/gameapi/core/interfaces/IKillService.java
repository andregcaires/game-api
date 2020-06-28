package com.andregcaires.gameapi.core.interfaces;

import java.util.List;
import java.util.Set;

import org.springframework.stereotype.Service;

import com.andregcaires.gameapi.domain.entities.Kill;
import com.andregcaires.gameapi.domain.entities.Player;
import com.andregcaires.gameapi.domain.utilities.TotalGameKillsWrapper;

@Service
public interface IKillService {

	Kill getKillRecord(String line);
	
	TotalGameKillsWrapper getKillsByPlayerList(Set<Player> playerList, List<Kill> killsList);
}
