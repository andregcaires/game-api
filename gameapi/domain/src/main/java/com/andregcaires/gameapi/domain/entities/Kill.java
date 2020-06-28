package com.andregcaires.gameapi.domain.entities;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter @Setter 
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class Kill {

	private String killerPlayer;
	
	private String killedPlayer;
	
	private String reason;
	
}
