package br.com.gdevs.LibraryInfo.model;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.time.Year;

@JsonIgnoreProperties(ignoreUnknown = true)
public record DadosAutor( @JsonAlias("name") String nome,
                          @JsonAlias("birth_year") Year dataNasc,
                          @JsonAlias("death_year") Year dataMorte ){}