package br.com.gdevs.LibraryInfo.model;

import br.com.gdevs.LibraryInfo.service.ConverteDados;
import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "livros")
public class Livro {

    @Id
    private Long id;

    private String nome;

    @ManyToOne(cascade = CascadeType.ALL)
    private Autor autor = new Autor();

    private String idioma;
    private Long numeroDownloads;

    public Livro() {
    }

    public Livro(DadosLivro dadosLivro) {
        this.id = dadosLivro.id();
        this.nome = dadosLivro.nome();
        this.idioma = dadosLivro.idioma().get(0);
        this.numeroDownloads = dadosLivro.numeroDownloads();

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

        this.autor.setNome(autor.getNome());
        this.autor.setDataMorte(autor.getDataMorte());
        this.autor.setDataNasc(autor.getDataNasc());
    }

    public String getIdioma() {
        return idioma;
    }

    public void setIdioma(String idioma) {
        this.idioma = idioma;
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
                "id=" + id +
                ", nome='" + nome + '\'' +
                ", autor=" + autor.getNome() +
                ", idioma='" + idioma + '\'' +
                ", numeroDownloads=" + numeroDownloads +
                '}';
    }
}
