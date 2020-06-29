package com.andregcaires.gameapi.domain.entities;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.ToString;

/*
 * @Data was not used in this class because the getGame() method
 * must have the @JsonIgnore annotation, in order to avoid
 * circular reference
 * */
@Entity
public class KillsByPlayer implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@ManyToOne
	@JoinColumn(name = "player_id")
	@ToString.Exclude
	private Player player;
	
	@JsonIgnore
	@ManyToOne
	@JoinColumn(name = "game_id")
	@ToString.Exclude
	private Game game;	
	
	private long kills;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Player getPlayer() {
		return player;
	}

	public void setPlayer(Player player) {
		this.player = player;
	}

	@JsonIgnore
	public Game getGame() {
		return game;
	}

	public void setGame(Game game) {
		this.game = game;
	}

	public long getKills() {
		return kills;
	}

	public void setKills(long kills) {
		this.kills = kills;
	}
	
	
	
}
