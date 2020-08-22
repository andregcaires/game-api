package com.andregcaires.gameapi.context.repositories;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import com.andregcaires.gameapi.domain.entities.GameInfo;

@DataJpaTest
public class GameInfoRepositoryTest {

	@Autowired
	private TestEntityManager entityManager;

	@Autowired
	private GameInfoRepository gameInfoRepository;

	@Test
	public void whenGameInfoIsSavedThenFindById() {

		// given
		var gameInfo = GameInfo.builder().needPass(false).gameName("The Cage").mapName("Talos IV").protocol("")
				.version("v1.0").maxGameClients(10).timeLimit(10).fragLimit(10).dmFlags(0).allowDownload(false)
				.maxClients(10).privateClients(2).gameType("Type").hostName("Host").maxRate(100).minRate(10).minPing(1)
				.maxPing(100).floodProtect("").captureLimit(0).build();

		var t = entityManager.persist(gameInfo);
		entityManager.flush();

		// when
		var found = gameInfoRepository.findById(t.getId()).orElse(null);

		// then
		Assertions.assertNotNull(found);
		Assertions.assertEquals(gameInfo, found);
	}
}
