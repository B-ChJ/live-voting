package kr.sparta.livevoting;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing // Auditing 기능 활성화
@SpringBootApplication // 애플리케이션의 시작점임을 명시해준다.
public class LiveVotingApplication {

	public static void main(String[] args) {
		SpringApplication.run(LiveVotingApplication.class, args);
	}

}