package com.andregcaires.gameapi.domain.entities;

import java.io.Serializable;

import javax.persistence.Entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

//@Entity
@Getter @Setter 
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class KillsByPlayer implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	private KillsByPlayerPK id = new KillsByPlayerPK();
	
	private long kills;
	
}
