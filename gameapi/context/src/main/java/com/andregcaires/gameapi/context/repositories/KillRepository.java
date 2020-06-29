package com.andregcaires.gameapi.context.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.andregcaires.gameapi.domain.entities.KillsByPlayer;

@Repository
public interface KillRepository extends JpaRepository<KillsByPlayer, Long> {

}
