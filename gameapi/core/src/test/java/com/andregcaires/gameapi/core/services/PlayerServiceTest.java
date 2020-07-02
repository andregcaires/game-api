package com.andregcaires.gameapi.core.services;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;

import com.andregcaires.gameapi.core.interfaces.IPlayerService;

@TestInstance(Lifecycle.PER_CLASS)
public class PlayerServiceTest {

	private IPlayerService playerService;

	@BeforeAll
	void setup() {

		playerService = new PlayerService();
	}

	@Test
	public void mustGetClientUserInfoFromStringLine() {

		// given
		String line = " 21:53 ClientUserinfoChanged: 3 n\\Mocinha\\t\\0\\model\\sarge\\hmodel\\sarge\\g_redteam\\\\g_blueteam\\\\c1\\4\\c2\\5\\hc\\95\\w\\0\\l\\0\\tt\\0\\tl\\0";

		// when
		var player = playerService.parseClientUserInfoLine(line);

		// then
		Assertions.assertNotNull(player);
		Assertions.assertEquals("Mocinha", player.getName());
	}

}
