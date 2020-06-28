package com.andregcaires.gameapi.core.interfaces;

import org.springframework.stereotype.Service;

import com.andregcaires.gameapi.domain.entities.Kill;

@Service
public interface IKillService {

	Kill getKillRecord(String line);
}
