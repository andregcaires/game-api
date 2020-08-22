package com.andregcaires.gameapi.context.repositories;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import com.andregcaires.gameapi.domain.entities.Player;


@DataJpaTest
public class PlayerRepositoryTest {

	@Autowired
    private TestEntityManager entityManager;
	
    @Autowired
    private PlayerRepository playerRepository;
    
	@Test
	public void whenPlayerIsSavedThenFindByName() {
		
		// given
		Player player = Player.builder().name("T'Pol").build();
		entityManager.persist(player);
		entityManager.flush();
		
		// when
		var found = playerRepository.findByName(player.getName());
		
		// then
		Assertions.assertEquals(player.getName(), found.getName());
	}
}
