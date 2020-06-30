package com.andregcaires.gameapi.domain.dto;

import java.util.Map;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class GameDto {

	
	private long totalKills;
	
	private String[] players;
	
	private Map<String, Long> kills;
}
