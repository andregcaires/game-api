package com.andregcaires.gameapi.domain.entities;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Singular;

@Entity
@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Game implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Singular
	@ManyToMany
	@JoinTable(name = "game_player", 
		joinColumns = @JoinColumn(name = "game_id"), 
		inverseJoinColumns = @JoinColumn(name = "player_id"))
	@JsonIgnore
	private Set<Player> players = new HashSet<>();
	
//	@Singular
//	@OneToMany(mappedBy = "id.game")
//	private Set<KillsByPlayer> killsByPlayers = new HashSet<>();
//	
	private long totalKills;

}
