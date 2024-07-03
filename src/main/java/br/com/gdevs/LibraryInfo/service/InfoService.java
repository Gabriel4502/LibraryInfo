package br.com.gdevs.LibraryInfo.service;
import br.com.gdevs.LibraryInfo.model.Autor;
import br.com.gdevs.LibraryInfo.model.Idiomas;
import br.com.gdevs.LibraryInfo.model.Livro;
import br.com.gdevs.LibraryInfo.repository.RepositorioAutor;
import br.com.gdevs.LibraryInfo.repository.RepositorioLivro;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Year;
import java.util.List;
import java.util.Optional;

@Service
public class InfoService {

    @Autowired
    RepositorioLivro repositorioLivro;

    @Autowired
    RepositorioAutor repositorioAutor;

    public List<Livro> obterTodosOsLivros (){
        return repositorioLivro.findAll();
    }

    public Optional<Autor> verificarDuplicidade(Autor autor){
        return repositorioAutor.verificarDuplicidade(autor.getNome());
    }


    public List <Autor> obterAutoresRegistrados(){
        return repositorioAutor.findAll();
    }

    public List<Autor> obterAutoresVivosEmAno(Year ano){

        return repositorioAutor.findAllByDataMorteGreaterThan(ano) ;
    }

    public List<Livro> buscarLivrosEmIdioma(Idiomas idiomas){

        return repositorioLivro.findByIdioma(idiomas);
    }

    public List<Livro> top10LivrosMaisBaixados(){

        return repositorioLivro.findTop10ByOrderByNumeroDownloadsDesc();
    }

    public List<Autor> buscarAutoresPorNome(String nome) {

        return repositorioAutor.findByNomeContainingIgnoreCase( nome);
    }
}
