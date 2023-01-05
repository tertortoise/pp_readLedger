package io.tertortoise.readledger_api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@SpringBootApplication //(exclude={DataSourceAutoConfiguration.class}) // WIP temporary exclusion
//@EnableAutoConfiguration(exclude={DataSourceAutoConfiguration.class})
public class ReadLedgerApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(ReadLedgerApiApplication.class, args);
	}

}
