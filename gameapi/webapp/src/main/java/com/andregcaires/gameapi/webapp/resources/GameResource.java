package com.andregcaires.gameapi.webapp.resources;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.andregcaires.gameapi.core.interfaces.IGameInfoService;
import com.andregcaires.gameapi.core.interfaces.IGameService;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@RequestMapping(value = {"/games"})
public class GameResource {

	@Autowired
	private IGameService gameService;
	
	@Autowired
	private IGameInfoService gameInfoService;
	
	@ApiOperation(value = "Returns Game list")
	@ApiResponses(value = {
	    @ApiResponse(code = 200, message = "Gets the list of Games obtained from log file"),
	    @ApiResponse(code = 500, message = "An exception was thrown"),
	})
	@RequestMapping(value = {""}, method = RequestMethod.GET, produces = "application/json")
	public ResponseEntity<?> getAll() {
		
		var body = gameService.findAll();
		return ResponseEntity.ok().body(body); 
	}
	
	@ApiOperation(value = "Returns one Game")
	@ApiResponses(value = {
	    @ApiResponse(code = 200, message = "Gets a single Game by its id"),
	    @ApiResponse(code = 404, message = "No Game was found"),
	    @ApiResponse(code = 500, message = "An exception was thrown"),
	})
	@RequestMapping(value = {"/{id}"}, method = RequestMethod.GET, produces = "application/json")
	public ResponseEntity<?> getById(@PathVariable Long id) {
		
		var body = gameService.findById(id);
		return ResponseEntity.ok().body(body); 
	}
	
	@ApiOperation(value = "Returns details from one Game")
	@ApiResponses(value = {
	    @ApiResponse(code = 200, message = "Gets details from a single Game by its id"),
	    @ApiResponse(code = 404, message = "No Game was found"),
	    @ApiResponse(code = 500, message = "An exception was thrown"),
	})
	@RequestMapping(value = {"/{id}/details"}, method = RequestMethod.GET, produces = "application/json")
	public ResponseEntity<?> getDetails(@PathVariable Long id) {
		
		var body = gameInfoService.findByGameId(id);
		return ResponseEntity.ok().body(body); 
	}
}
