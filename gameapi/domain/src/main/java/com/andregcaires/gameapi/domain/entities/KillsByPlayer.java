package com.andregcaires.gameapi.domain.entities;

import java.io.Serializable;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

/*
 * @Data was not used in this class because the getGame() method
 * must have the @JsonIgnore annotation, in order to avoid
 * circular reference
 * */
@Entity
public class KillsByPlayer implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@EmbeddedId
	private KillsByPlayerPK id = new KillsByPlayerPK();
	
	private long kills;

	@JsonIgnore
	public KillsByPlayerPK getId() {
		return id;
	}

	public void setId(KillsByPlayerPK id) {
		this.id = id;
	}

	public Player getPlayer() {
		return id.getPlayer();
	}

	public void setPlayer(Player player) {
		this.id.setPlayer(player);
	}

	@JsonIgnore
	public Game getGame() {
		return id.getGame();
	}

	public void setGame(Game game) {
		this.id.setGame(game);
	}

	public long getKills() {
		return kills;
	}

	public void setKills(long kills) {
		this.kills = kills;
	}
	
	
	
}
