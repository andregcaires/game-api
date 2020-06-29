package com.andregcaires.gameapi.domain.entities;

import java.io.Serializable;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;

import com.fasterxml.jackson.annotation.JsonBackReference;

import lombok.Data;

@Entity
@Data
public class KillsByPlayer implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@JsonBackReference
	@EmbeddedId
	private KillsByPlayerPK id = new KillsByPlayerPK();
	
	private long kills;
	
}
