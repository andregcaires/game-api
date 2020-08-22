package com.andregcaires.gameapi.context.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.andregcaires.gameapi.domain.entities.GameInfo;

@Repository
public interface GameInfoRepository extends JpaRepository<GameInfo, Long> {

	public Optional<GameInfo> findById(final Long id);
}
