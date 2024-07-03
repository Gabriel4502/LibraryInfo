package br.com.gdevs.LibraryInfo.service;
import br.com.gdevs.LibraryInfo.model.Autor;
import br.com.gdevs.LibraryInfo.model.Livro;
import br.com.gdevs.LibraryInfo.repository.RepositorioAutor;
import br.com.gdevs.LibraryInfo.repository.RepositorioLivro;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Optional;

public class LivroService {

    @Autowired
    RepositorioLivro repositorioLivro;

    @Autowired
    RepositorioAutor repositorioAutor;

    public List<Livro> obterTodosOsLivros (){

        return repositorioLivro.findAll();
    }

    public Optional<Livro> verificarDuplicidade(){
        return null;
    }


    public List <Autor> obterAutoresRegistrados(){

        return null;
    }

    public List<Autor> obterAutoresVivosEmAnos(){

        return null;
    }

    public List<Livro> buscarLivrosEmIdioma(){

        return null;
    }

    public List<Livro> top10LivrosMaisBaixados(){

        return null;
    }




}
