package com.andregcaires.gameapi.domain.utilities;

import java.util.List;

import com.andregcaires.gameapi.domain.entities.KillsByPlayer;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class TotalGameKillsWrapper {

	private List<KillsByPlayer> killsByPlayerList;
	private long totalKills;
}
