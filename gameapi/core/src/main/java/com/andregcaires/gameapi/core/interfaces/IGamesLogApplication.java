package com.andregcaires.gameapi.core.interfaces;

import java.io.InputStream;

import org.springframework.stereotype.Service;

@Service
public interface IGamesLogApplication {

	void parseLogFileAndSaveData(InputStream inputStream);
}
