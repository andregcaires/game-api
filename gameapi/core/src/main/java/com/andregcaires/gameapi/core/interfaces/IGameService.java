package com.andregcaires.gameapi.core.interfaces;

import java.util.List;
import java.util.Set;

import org.springframework.stereotype.Service;

import com.andregcaires.gameapi.domain.entities.KillsByPlayer;
import com.andregcaires.gameapi.domain.entities.Player;

@Service
public interface IGameService {

	void createNewGame(Set<Player> players, List<KillsByPlayer> killsByPlayers, long totalKills);
}
