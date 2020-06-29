package com.andregcaires.gameapi.core.interfaces;

import java.util.List;

import org.springframework.stereotype.Service;

import com.andregcaires.gameapi.domain.entities.GameInfo;

@Service
public interface IGameInfoService {

	GameInfo buildGame(String initGameLine);
	
	GameInfo insert(GameInfo gameInfo);
	
	GameInfo findByGameId(Long id);
	
}
