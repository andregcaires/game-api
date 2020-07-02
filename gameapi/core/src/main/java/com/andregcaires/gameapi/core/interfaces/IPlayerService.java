package com.andregcaires.gameapi.core.interfaces;

import org.springframework.stereotype.Service;

import com.andregcaires.gameapi.domain.entities.Player;

@Service
public interface IPlayerService {

	Player parseClientUserInfoLine(String line);

	Player insertPlayerIfNotExists(Player newPlayer);
}
