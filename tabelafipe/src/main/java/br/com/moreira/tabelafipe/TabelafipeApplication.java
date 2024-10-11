package br.com.moreira.tabelafipe;

import br.com.moreira.tabelafipe.controller.Principal;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class TabelafipeApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(TabelafipeApplication.class, args);
	}


	@Override
	public void run(String... args) throws Exception {
		Principal exibir = new Principal();
		exibir.Menu();

	}

}
