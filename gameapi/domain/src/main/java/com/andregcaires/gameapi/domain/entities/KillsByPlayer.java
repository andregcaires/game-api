package com.andregcaires.gameapi.domain.entities;

import java.io.Serializable;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class KillsByPlayer implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@EmbeddedId
	private KillsByPlayerPK id = new KillsByPlayerPK();
	
	private long kills;
	
}
