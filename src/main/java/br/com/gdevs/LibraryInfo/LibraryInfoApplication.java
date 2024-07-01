package br.com.gdevs.LibraryInfo;

import br.com.gdevs.LibraryInfo.principal.Principal;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class LibraryInfoApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(LibraryInfoApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		Principal principal = new Principal();
		principal.exibeMenu();
	}
}
