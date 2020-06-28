package com.andregcaires.gameapi.core.interfaces;

import org.springframework.stereotype.Service;

import com.andregcaires.gameapi.domain.entities.GameInfo;

@Service
public interface IGameInfoService {

	GameInfo buildGame(String initGameLine);
	
}
