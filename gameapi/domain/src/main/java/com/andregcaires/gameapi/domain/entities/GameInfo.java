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
public class GameInfo {

	private String floodProtect;
	
	private int maxPing;
	
	private int minPing;
	
	private int maxRate;
	
	private int minRate;
	
	private String hostName;
	
	private String gameType;
	
	private int privateClients;
	
	private int maxClients;
	
	private boolean allowDownload;
	
	private int dmFlags;
	
	private int fragLimit;
	
	private int timeLimit;
	
	private int maxGameClients;
	
	private int captureLimit;
	
	private String version;
	
	private String protocol;
	
	private String mapName;
	
	private String gameName;
	
	private boolean needPass;
	
}
