package com.voja.Zljeb;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import com.voja.Zljeb.Storage.StorageProperties;
import com.voja.Zljeb.spotify.SpotifyService;

@SpringBootApplication
@EnableConfigurationProperties(StorageProperties.class)
public class ZljebApplication {

	public static void main(String[] args) {
		SpringApplication.run(ZljebApplication.class, args);
	}

}
