package com.andregcaires.gameapi.domain.entities;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Kill {

	private String killerPlayer;

	private String killedPlayer;

	private String reason;

}
