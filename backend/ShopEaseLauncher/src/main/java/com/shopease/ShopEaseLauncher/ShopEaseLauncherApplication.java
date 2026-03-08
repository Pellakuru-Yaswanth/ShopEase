package com.shopease.ShopEaseLauncher;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.persistence.autoconfigure.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@EntityScan("com.shopease.bean")
@EnableJpaRepositories("com.shopease.dao")
@SpringBootApplication(scanBasePackages = "com.shopease")
public class ShopEaseLauncherApplication {

	public static void main(String[] args) {
		SpringApplication.run(ShopEaseLauncherApplication.class, args);
	}

}
