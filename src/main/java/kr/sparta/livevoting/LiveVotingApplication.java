package kr.sparta.livevoting;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
public class LiveVotingApplication {

	public static void main(String[] args) {
		SpringApplication.run(LiveVotingApplication.class, args);
	}

}