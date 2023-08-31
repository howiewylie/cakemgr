package com.waracle.cakemgr;

import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;
import com.waracle.cakemgr.entity.Cake;
import com.waracle.cakemgr.service.CakeService;
import jakarta.servlet.ServletException;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.dao.DataIntegrityViolationException;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;

@SpringBootApplication
public class CakemgrApplication {

	public static void main(String[] args) {
		SpringApplication.run(CakemgrApplication.class, args);
	}


	@Bean
	public CommandLineRunner init(CakeService cakeService) {
		return (args) -> {
			// save a few customers
			System.out.println("init started");


			System.out.println("downloading cake json");
			try (InputStream inputStream = new URL("https://gist.githubusercontent.com/hart88/198f29ec5114a3ec3460/raw/8dd19a88f9b8d24c23d9960f3300d0c917a4f07c/cake.json").openStream()) {
				BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));

				StringBuffer buffer = new StringBuffer();
				String line = reader.readLine();
				while (line != null) {
					buffer.append(line);
					line = reader.readLine();
				}

				System.out.println("parsing cake json");
				JsonParser parser = new JsonFactory().createParser(buffer.toString());
				if (JsonToken.START_ARRAY != parser.nextToken()) {
					throw new Exception("bad token");
				}

				JsonToken nextToken = parser.nextToken();
				while(nextToken == JsonToken.START_OBJECT) {
					System.out.println("creating cake entity");

					Cake cake = Cake.builder().build();
					System.out.println(parser.nextFieldName());
					cake.setTitle(parser.nextTextValue());

					System.out.println(parser.nextFieldName());
					cake.setDescription(parser.nextTextValue());

					System.out.println(parser.nextFieldName());
					cake.setImage(parser.nextTextValue());
					System.out.println("cake: " + cake);
					try {
					cakeService.addCake(cake);
					} catch(DataIntegrityViolationException de) {
						System.out.println("trying to add duplicate record");
					}
					nextToken = parser.nextToken();
					System.out.println(nextToken);

					nextToken = parser.nextToken();
					System.out.println(nextToken);
				}

			} catch (Exception ex) {
				throw new ServletException(ex);
			}
			System.out.println("init finished");

		};
	}

}
