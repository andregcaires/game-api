package com.andregcaires.gameapi.core.services;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;

import com.andregcaires.gameapi.core.interfaces.IGameService;

@TestInstance(Lifecycle.PER_CLASS)
public class GameServiceTest {

	private IGameService gameService;
	
	@BeforeAll
	void setup() {
		gameService = new GameService();
	}
}
