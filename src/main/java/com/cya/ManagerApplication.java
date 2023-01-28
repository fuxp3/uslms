package com.cya;

import com.github.xiaoymin.swaggerbootstrapui.annotations.EnableSwaggerBootstrapUI;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import java.net.UnknownHostException;

@EnableSwaggerBootstrapUI
@EnableJpaRepositories
@SpringBootApplication
public class ManagerApplication {

	public static void main(String[] args) throws UnknownHostException {
		SpringApplication.run(ManagerApplication.class, args);
	}

}
