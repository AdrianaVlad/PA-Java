package com.example.SpringServer;

import java.io.IOException;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SpringServer {

	public static void main(String[] args) {
		SpringApplication.run(SpringServer.class, args);
                try {
                    GameServer server = new GameServer();
                } catch (IOException e) {
                   System.err.println(e);
                }
	}
}
