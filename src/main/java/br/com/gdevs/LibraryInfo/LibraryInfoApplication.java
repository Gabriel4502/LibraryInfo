package br.com.gdevs.LibraryInfo;

import br.com.gdevs.LibraryInfo.principal.Principal;
import br.com.gdevs.LibraryInfo.repository.RepositorioAutor;
import br.com.gdevs.LibraryInfo.repository.RepositorioLivro;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class LibraryInfoApplication implements CommandLineRunner {

	@Autowired
    RepositorioLivro repositorioLivro;
	@Autowired
	RepositorioAutor repositorioAutor;

	public static void main(String[] args) {
		SpringApplication.run(LibraryInfoApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		Principal principal = new Principal(repositorioLivro, repositorioAutor);
		principal.exibeMenu();
	}
}
