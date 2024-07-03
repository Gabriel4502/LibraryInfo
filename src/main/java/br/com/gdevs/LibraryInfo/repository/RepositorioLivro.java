package br.com.gdevs.LibraryInfo.repository;
import br.com.gdevs.LibraryInfo.model.Idiomas;
import br.com.gdevs.LibraryInfo.model.Livro;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface RepositorioLivro extends JpaRepository<Livro, Long> {


    List<Livro> findByIdioma(Idiomas nomeIdioma);

    List<Livro> findTop10ByOrderByNumeroDownloadsDesc();
}
