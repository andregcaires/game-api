package com.andregcaires.gameapi.core.services;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;

import com.andregcaires.gameapi.core.interfaces.IKillService;
import com.andregcaires.gameapi.domain.entities.Kill;
import com.andregcaires.gameapi.domain.entities.KillsByPlayer;
import com.andregcaires.gameapi.domain.entities.Player;

@TestInstance(Lifecycle.PER_CLASS)
public class KillServiceTest {

	private IKillService killService;	
	private List<Kill> killsList = new ArrayList<>();
	private Set<Player> players = new HashSet<>();
	
	private final String WORLD = "<world>";
	
	@BeforeAll
	void setup() {
		
		killService = new KillService();
		
		addMockKillList();
		addMockPlayersSet();
	}
	
	@Test
	public void mustGetTotalAndIndividualPlayerKills() {
		
		var killsByPlayer = killService.getTotalAndIndividualKills(players, killsList);
		
		KillsByPlayer objNoWorldKills = killsByPlayer
				.stream()
				.filter((item) -> item.getPlayer().getName() == "Worf, son of Mogh")
				.findFirst().orElseThrow();
		
		Assertions.assertEquals(3L, objNoWorldKills.getKills());
		
		KillsByPlayer objWithWorldKills = killsByPlayer
				.stream()
				.filter((item) -> item.getPlayer().getName() == "Tasha Yar")
				.findFirst().orElseThrow();
		
		Assertions.assertEquals(0, objWithWorldKills.getKills());
	}
	
	@Test
	public void mustGetKillRecordFromStringLine() {
		
		String line = "  2:11 Kill: 2 4 6: Dono da Bola killed Zeh by MOD_ROCKET";
		
		var kill = killService.getKillRecord(line);
		
		Assertions.assertEquals("Dono da Bola", kill.getKillerPlayer());
		Assertions.assertEquals("Zeh", kill.getKilledPlayer());
		Assertions.assertEquals("MOD_ROCKET", kill.getReason());
	}
	
	@Test
	public void mustReturnTotalPlayerKills() {
		
		var totalPlayerKills = killService.getTotalPlayerKills("Worf, son of Mogh", killsList);
		
		Assertions.assertEquals(3L, totalPlayerKills);
	}
	
	@Test
	public void mustReturnTotalPlayerKillsByWorld() {
		
		var totalPlayerKills = killService.getPlayerKillsByWorld("Tasha Yar", killsList);
		
		Assertions.assertEquals(2L, totalPlayerKills);
	}
	
	private void addMockPlayersSet() {
		var player1 = Player.builder()
			.name("Jean-Luc Picard")
			.build();
		
		var player2 = Player.builder()
			.name("Wesley Crusher")
			.build();
		
		var player3 = Player.builder()
			.name("Worf, son of Mogh")
			.build();
		
		var player4 = Player.builder()
			.name("William Riker")
			.build();
		
		var player5 = Player.builder()
			.name("Tasha Yar")
			.build();
		
		var player6 = Player.builder()
			.name("Katherine Pulaski")
			.build();
		
		var player7 = Player.builder()
			.name("Ro Laren")
			.build();
		
		players.addAll(Arrays.asList(player1, player2, player3, player4, player5, player6, player7));
	}
	
	private void addMockKillList() {
		var kill1 = Kill.builder()
				.killerPlayer("Jean-Luc Picard")
				.killedPlayer("Wesley Crusher")
				.reason("Shut up, Wesley")
				.build();
		
		var kill2 = Kill.builder()
				.killerPlayer("Worf, son of Mogh")
				.killedPlayer("William Riker")
				.reason("Bat'leth")
				.build();
		
		var kill3 = Kill.builder()
				.killerPlayer("Tasha Yar")
				.killedPlayer("Katherine Pulaski")
				.reason("Romulan Disruptor")
				.build();
		
		var kill4 = Kill.builder()
				.killerPlayer("Worf, son of Mogh")
				.killedPlayer("Ro Laren")
				.reason("Hand Phaser")
				.build();
		
		var kill5 = Kill.builder()
				.killerPlayer(WORLD)
				.killedPlayer("Tasha Yar")
				.reason("Armus")
				.build();
		
		var kill6 = Kill.builder()
				.killerPlayer(WORLD)
				.killedPlayer("Tasha Yar")
				.reason("Romulan Empire")
				.build();
		
		var kill7 = Kill.builder()
				.killerPlayer("Worf, son of Mogh")
				.killedPlayer("Gowron")
				.reason("For the Glory of the Empire")
				.build();
		
		killsList.addAll(Arrays.asList(kill1, kill2, kill3, kill4, kill5, kill6, kill7));
	}
	
}
