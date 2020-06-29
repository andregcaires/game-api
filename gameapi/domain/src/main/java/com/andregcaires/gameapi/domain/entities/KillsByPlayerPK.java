package com.andregcaires.gameapi.domain.entities;

import java.io.Serializable;

import javax.persistence.Embeddable;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;
import lombok.ToString;

@Embeddable
@Data
public class KillsByPlayerPK implements Serializable {

	private static final long serialVersionUID = 1L;

	@ManyToOne
	@JoinColumn(name = "player_id")
	//@JsonIgnoreProperties("killsByPlayers")
	@ToString.Exclude
	private Player player;
	
	@ManyToOne
	@JoinColumn(name = "game_id")
	@JsonIgnore
	@ToString.Exclude
	private Game game;	
	
	
}
