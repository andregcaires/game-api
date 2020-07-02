package com.andregcaires.gameapi.core;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.andregcaires.gameapi.core.interfaces.IGameInfoService;
import com.andregcaires.gameapi.core.interfaces.IGameService;
import com.andregcaires.gameapi.core.interfaces.IGamesLogApplication;
import com.andregcaires.gameapi.core.interfaces.IKillService;
import com.andregcaires.gameapi.core.interfaces.IPlayerService;
import com.andregcaires.gameapi.domain.entities.GameInfo;
import com.andregcaires.gameapi.domain.entities.Kill;
import com.andregcaires.gameapi.domain.entities.KillsByPlayer;
import com.andregcaires.gameapi.domain.entities.Player;
import com.andregcaires.gameapi.domain.utilities.Keys;

@Service
public class GamesLogApplication implements IGamesLogApplication {

	@Autowired
	private IGameInfoService gameInfoService;

	@Autowired
	private IKillService killService;

	@Autowired
	private IPlayerService playerService;

	@Autowired
	private IGameService gameService;

	Logger logger = LoggerFactory.getLogger(GamesLogApplication.class);

	private List<Kill> killsList = new ArrayList<>();
	private List<KillsByPlayer> killsByPlayerList = new ArrayList<>();
	private Set<Player> playerList = new HashSet<>();
	private long totalGameKills = 0;

	private GameInfo gameInfo;

	public void parseLogFileAndSaveData(InputStream inputStream) {

		logger.info("Initialize input stream reader");

		try (BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream))) {

			while (reader.ready()) {

				var line = reader.readLine();

				if (line.contains(Keys.INITGAME)) {

					gameInfo = gameInfoService.parseInitGameLine(line);

					logger.info("A new game has been captured from log file: " + gameInfo.toString());

				} else if (line.contains(Keys.CLIENTUSERINFOCHANGED)) {

					var player = playerService.parseClientUserInfoLine(line);

					// if found player not exists in database, inserts it
					player = playerService.insertPlayerIfNotExists(player);

					if (!playerList.contains(player)) {
						playerList.add(player);
					}

					logger.info("Game has been captured from log file: " + player.toString());

				} else if (line.contains(Keys.KILL)) {

					var kill = killService.parseKillLine(line);

					killsList.add(kill);

					totalGameKills++;

					logger.info("Kill record has been captured from log file: " + kill.toString());

				} else if (line.contains(Keys.SHUTDOWNGAME) || (gameInfo != null && line.contains(Keys.ONLYLINES))) {

					/*
					 * Since there is a game in the logs file that does not finish with Shutdown, it
					 * checks for separation lines when there's a not finished game (gameInfo !=
					 * null)
					 */

					logger.info("A game has been shutdown");

					saveGameFromLogFile();
				}
			}

		} catch (Exception e) {
			logger.error("An error has ocurred: " + e.getMessage());
			e.printStackTrace();
		}

	}

	public void saveGameFromLogFile() {

		// Get total kills count by player
		killsByPlayerList = killService.getTotalAndIndividualKills(playerList, killsList);

		// Creates new game to be persisted
		var game = gameService.createNewGame(playerList, killsByPlayerList, totalGameKills);

		// Binds game to each kills by player record
		killsByPlayerList.forEach(item -> item.setGame(game));

		// Binds game to game info / details
		gameInfo.setGame(game);

		// Save both game and kills by player
		gameService.insert(game);
		killService.insert(killsByPlayerList);
		gameInfoService.insert(gameInfo);

		// Clears lists, objects and variables used for current game
		killsByPlayerList.clear();
		killsList.clear();
		playerList.clear();

		totalGameKills = 0;
		gameInfo = null;

	}
}
