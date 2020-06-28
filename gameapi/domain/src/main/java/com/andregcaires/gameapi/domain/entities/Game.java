package com.andregcaires.gameapi.domain.entities;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.Singular;
import lombok.ToString;

//@Entity
@Getter @Setter 
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class Game implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Singular
	private Set<Player> players;
	
	//@OneToMany
	@Singular
	private Set<KillsByPlayer> killsByPlayers;
	
	private long totalKills;

}
