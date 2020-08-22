package com.andregcaires.gameapi.context;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;

@SpringBootApplication
@EntityScan(basePackages = { "com.andregcaires.gameapi.domain" })
public class ContextApplication {

}
