package com.andregcaires.gameapi.webapp.resources;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.andregcaires.gameapi.core.interfaces.IGameService;
import com.andregcaires.gameapi.core.interfaces.IKillService;

@RestController
@RequestMapping(value = {"/games"})
public class GameResource {

	@Autowired
	private IGameService gameService;
	
	@Autowired
	private IKillService killService;
	
	@RequestMapping(value = {"/", ""}, method = RequestMethod.GET)
	public ResponseEntity<?> getAll() {
		
		var body = gameService.findAll();
		return ResponseEntity.ok().body(body); 
	}
}
