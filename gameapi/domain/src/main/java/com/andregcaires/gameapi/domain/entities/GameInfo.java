package com.andregcaires.gameapi.domain.entities;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class GameInfo implements Serializable {

	private static final long serialVersionUID = 1L;

	@OneToOne
	private Game game;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

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
