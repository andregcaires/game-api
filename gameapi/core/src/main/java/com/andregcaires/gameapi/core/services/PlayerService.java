package com.andregcaires.gameapi.core.services;

import java.util.regex.Pattern;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.andregcaires.gameapi.context.repositories.PlayerRepository;
import com.andregcaires.gameapi.core.interfaces.IPlayerService;
import com.andregcaires.gameapi.domain.entities.Player;

@Service
public class PlayerService implements IPlayerService {

	private final String lineSeparator = "\\";

	@Autowired
	private PlayerRepository repository;

	Logger logger = LoggerFactory.getLogger(PlayerService.class);

	public Player parseClientUserInfoLine(String line) {

		var splitUpLineArray = line.replaceAll(Pattern.quote(lineSeparator), "\\\\").split("\\\\");

		var foundPlayerName = splitUpLineArray[1];

		var player = Player.builder().name(foundPlayerName).build();

		return player;
	}

	public Player insertPlayerIfNotExists(Player newPlayer) {

		var player = findPlayerByName(newPlayer.getName());

		if (player == null) {

			player = insertNewPlayer(newPlayer);
		}

		return player;
	}

	public Player findPlayerByName(String playerName) {
		return repository.findByName(playerName);
	}

	public Player insertNewPlayer(Player player) {
		return repository.save(player);
	}
}
