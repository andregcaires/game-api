package com.andregcaires.gameapi.context.repositories;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.andregcaires.gameapi.domain.entities.Player;

@Repository
public interface PlayerRepository extends JpaRepository<Player, Long> {

	@Transactional
	Player findByName(String name);
}
