package com.siuuuu.establers;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing // craeted_at, updated_at 자동 업데이트
@SpringBootApplication
public class EstablersApplication {
	public static void main(String[] args) {
		SpringApplication.run(EstablersApplication.class, args);
	}

}
