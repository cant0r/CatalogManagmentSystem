package com.cant0r.cms;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories("com.cant0r.cms.data")
@EntityScan("com.cant0r.cms.data")
public class CatalogManagementSystemApplication {

	public static void main(String[] args) {
		SpringApplication.run(CatalogManagementSystemApplication.class, args);
	}

}
