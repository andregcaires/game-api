package com.andregcaires.gameapi.domain.dto;

import java.util.Map;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class GameDto {

	@JsonProperty("total_kills")
	private long totalKills;
	
	private String[] players;
	
	private Map<String, Long> kills;
}
