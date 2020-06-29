package com.andregcaires.gameapi.webapp.resources;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.andregcaires.gameapi.core.interfaces.IGameInfoService;
import com.andregcaires.gameapi.core.interfaces.IGameService;

@RestController
@RequestMapping(value = {"/games"})
public class GameResource {

	@Autowired
	private IGameService gameService;
	
	@Autowired
	private IGameInfoService gameInfoService;
	
	@RequestMapping(value = {"/", ""}, method = RequestMethod.GET)
	public ResponseEntity<?> getAll() {
		
		var body = gameService.findAll();
		return ResponseEntity.ok().body(body); 
	}
	
	@RequestMapping(value = {"/{id}", "/{id}/"}, method = RequestMethod.GET)
	public ResponseEntity<?> getById(@PathVariable Long id) {
		
		var body = gameService.findById(id);
		return ResponseEntity.ok().body(body); 
	}
	
	@RequestMapping(value = {"/{id}/details", "/{id}/details/"}, method = RequestMethod.GET)
	public ResponseEntity<?> getDetails(@PathVariable Long id) {
		
		var body = gameInfoService.findByGameId(id);
		return ResponseEntity.ok().body(body); 
	}
}
