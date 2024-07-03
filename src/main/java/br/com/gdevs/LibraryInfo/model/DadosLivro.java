package br.com.gdevs.LibraryInfo.model;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public record DadosLivro(Long id, @JsonAlias("title") String nome, @JsonAlias("authors") List<DadosAutor> dadosAutor,
                         @JsonAlias("languages") List <String> idioma, @JsonAlias("download_count") Long numeroDownloads){
}
