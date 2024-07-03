package br.com.gdevs.LibraryInfo.repository;

import br.com.gdevs.LibraryInfo.model.Autor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.Year;
import java.util.List;
import java.util.Optional;

public interface RepositorioAutor extends JpaRepository<Autor, Long> {


    @Query("select a from Autor a where a.nome LIKE :nomeAutor  ")
    Optional <Autor> verificarDuplicidade(String nomeAutor);


    List<Autor> findAllByDataMorteGreaterThan(Year anoNasc);

    List<Autor> findByNomeContainingIgnoreCase(String nome);
}
