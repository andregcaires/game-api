package com.andregcaires.gameapi.core.services;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;

import com.andregcaires.gameapi.core.interfaces.IGameService;
import com.andregcaires.gameapi.domain.entities.Game;
import com.andregcaires.gameapi.domain.entities.KillsByPlayer;
import com.andregcaires.gameapi.domain.entities.Player;

@TestInstance(Lifecycle.PER_CLASS)
public class GameServiceTest {

	private IGameService gameService;

	private Player player1;
	private Player player2;
	private Player player3;

	private final String EXPTECTED_KEY = "game_1";
	private final Long TOTAL_MOCK_KILLS_BY_PLAYER = 24L;

	@BeforeAll
	void setup() {
		gameService = new GameService();
	}

	@Test
	public void createDtoFromGame() {

		// given
		List<Player> players = mockPlayers();
		List<KillsByPlayer> killsByPlayer = mockKillsByPlayer();

		Game game = Game.builder().id(1L).totalKills(30).killsByPlayers(killsByPlayer).players(players).build();

		// when
		var mapDto = gameService.createDtoFromGame(game);

		// then
		Assertions.assertNotNull(mapDto);
		Assertions.assertTrue(mapDto.containsKey(EXPTECTED_KEY));

		var dto = mapDto.get(EXPTECTED_KEY);
		Assertions.assertNotNull(dto);
		Assertions.assertEquals(players.size(), dto.getPlayers().length);
		Assertions.assertTrue(dto.getTotalKills() >= TOTAL_MOCK_KILLS_BY_PLAYER);
	}

	private List<Player> mockPlayers() {
		player1 = Player.builder().name("James Kirk").build();

		player2 = Player.builder().name("S'chn T'gai Spock").build();

		player3 = Player.builder().name("Leonard McCoy").build();

		return Arrays.asList(player1, player2, player3);
	}

	private List<KillsByPlayer> mockKillsByPlayer() {
		var killsByPlayer1 = new KillsByPlayer();
		killsByPlayer1.setPlayer(player1);
		killsByPlayer1.setKills(8);

		var killsByPlayer2 = new KillsByPlayer();
		killsByPlayer2.setPlayer(player2);
		killsByPlayer2.setKills(9);

		var killsByPlayer3 = new KillsByPlayer();
		killsByPlayer3.setPlayer(player3);
		killsByPlayer3.setKills(7);

		return Arrays.asList(killsByPlayer1, killsByPlayer2, killsByPlayer3);
	}
}
