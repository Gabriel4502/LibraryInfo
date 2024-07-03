package br.com.gdevs.LibraryInfo.model;

import br.com.gdevs.LibraryInfo.service.ConverteDados;
import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "livros")
public class Livro {

    @GeneratedValue(strategy = GenerationType.AUTO)
    @Id
    private Long id;

    private String nome;

    @ManyToOne
    private Autor autor;

    @Enumerated(EnumType.STRING)
    private Idiomas idioma;
    private Long numeroDownloads;

    public Livro() {
    }

    public Livro(DadosLivro dadosLivro) {
        this.id = dadosLivro.id();
        this.nome = dadosLivro.nome();
        this.idioma = Idiomas.fromString(dadosLivro.idioma().get(0));
        this.numeroDownloads = dadosLivro.numeroDownloads();

    }

    public void setAutorid(Long id){
        this.autor.setId(id);
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Autor getAutor() {
        return autor;
    }

    public void setAutor(Autor autor) {

        this.autor = autor;
    }

    public Idiomas getIdioma() {
        return idioma;
    }

    public void setIdioma(String idioma) {
        this.idioma = Idiomas.fromString(idioma);
    }

    public Long getNumeroDownloads() {
        return numeroDownloads;
    }

    public void setNumeroDownloads(Long numeroDownloads) {
        this.numeroDownloads = numeroDownloads;
    }

    public Long getId() {
        return id;
    }

    @Override
    public String toString() {
        return "Livro{" +
                ", nome: " + nome +
                ", autor: " + autor.getNome() +
                ", idioma: " + idioma +
                ", numeroDownloads: " + numeroDownloads +
                '}' +"\n";
    }
}
