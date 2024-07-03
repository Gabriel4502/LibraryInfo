package br.com.gdevs.LibraryInfo.repository;

import br.com.gdevs.LibraryInfo.model.Autor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface RepositorioAutor extends JpaRepository<Autor, Long> {


    @Query("select a from autores a where a.nome LIKE :nomeAutor  ")
    Optional <Autor> verificarDuplicidade(String nomeAutor);


}
