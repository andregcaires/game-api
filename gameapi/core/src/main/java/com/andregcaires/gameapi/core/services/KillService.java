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

@Service
public class KillService implements IKillService {

	private final String WORLD = "<world>";

	Logger logger = LoggerFactory.getLogger(KillService.class);

	@Autowired
	private KillRepository killRepository;

	public Kill parseKillLine(String line) {

		var killerPlayer = (line.substring(line.lastIndexOf(": ") + 1, line.indexOf("killed"))).trim();
		var killedPlayer = (line.substring(line.indexOf("killed") + 6, line.indexOf("by"))).trim();
		var reason = (line.substring(line.lastIndexOf(" by ") + 3, line.length())).trim();

		var kill = Kill.builder().killerPlayer(killerPlayer).killedPlayer(killedPlayer).reason(reason).build();

		return kill;
	}

	public List<KillsByPlayer> getTotalAndIndividualKills(Set<Player> playerList, List<Kill> killsList) {

		long individualKills = 0;

		List<KillsByPlayer> killsByPlayerList = new ArrayList<>();

		for (Player player : playerList) {

			// Sums each players kills
			individualKills = getTotalPlayerKills(player.getName(), killsList);

			// Decreases number of world kills from individual player's kills
			individualKills -= getPlayerKillsByWorld(player.getName(), killsList);

			// Updates to 0 when world kills are greater than player's
			individualKills = individualKills > 0 ? individualKills : 0;

			var killsByPlayer = new KillsByPlayer();

			killsByPlayer.setPlayer(player);
			killsByPlayer.setKills(individualKills);

			killsByPlayerList.add(killsByPlayer);

		}

		return killsByPlayerList;
	}

	public List<KillsByPlayer> insert(List<KillsByPlayer> list) {
		return killRepository.saveAll(list);
	}

	public long getTotalPlayerKills(String playerName, List<Kill> killsList) {
		return killsList.stream().filter((t) -> t.getKillerPlayer().equals(playerName)).count();
	}

	public long getPlayerKillsByWorld(String playerName, List<Kill> killsList) {
		return killsList.stream()
				.filter((t) -> t.getKilledPlayer().equals(playerName) && t.getKillerPlayer().equals(WORLD)).count();
	}

	public List<KillsByPlayer> findAll() {
		return killRepository.findAll();
	}
}
