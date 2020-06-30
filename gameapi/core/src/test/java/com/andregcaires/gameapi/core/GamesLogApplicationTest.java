package com.andregcaires.gameapi.core;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;

import com.andregcaires.gameapi.core.interfaces.IGamesLogApplication;

@TestInstance(Lifecycle.PER_CLASS)
public class GamesLogApplicationTest {

	private IGamesLogApplication gamesLogApplication;
	
	@BeforeAll
	void setup() {
		gamesLogApplication = new GamesLogApplication();
	}

}
