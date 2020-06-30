package com.andregcaires.gameapi.core.interfaces;

import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.stereotype.Service;

import com.andregcaires.gameapi.domain.dto.GameDto;
import com.andregcaires.gameapi.domain.entities.Game;
import com.andregcaires.gameapi.domain.entities.KillsByPlayer;
import com.andregcaires.gameapi.domain.entities.Player;

@Service
public interface IGameService {

	Game createNewGame(Set<Player> players, List<KillsByPlayer> killsByPlayers, long totalKills);
	
	Game insert(Game game);
	
	List<Map<String, GameDto>> findAll();
	
	Map<String, GameDto> findById(Long id);
}
