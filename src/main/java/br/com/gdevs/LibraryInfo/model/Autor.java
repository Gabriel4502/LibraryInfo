package br.com.gdevs.LibraryInfo.model;

import jakarta.persistence.*;

import java.time.Year;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Entity
@Table(name = "autores")
public class Autor {

    @GeneratedValue(strategy = GenerationType.AUTO)
    @Id
    private Long id;
    @Column(unique = true)
    private String nome;
    private Year dataNasc;
    private Year dataMorte;
    @OneToMany(mappedBy = "autor", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List <Livro> livro = new ArrayList<>();

    public Autor(){
    }

    public Autor(DadosAutor dadosAutor) {
        this.nome = dadosAutor.nome();
        this.dataNasc = dadosAutor.dataNasc();
        this.dataMorte = dadosAutor.dataMorte();
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Year getDataNasc() {
        return dataNasc;
    }

    public void setDataNasc(Year dataNasc) {
        this.dataNasc = dataNasc;
    }

    public Year getDataMorte() {
        return dataMorte;
    }

    public void setDataMorte(Year dataMorte) {
        this.dataMorte = dataMorte;
    }

    public List<Livro> getLivro() {
        return livro;
    }

    public void setLivro(List<Livro> livro) {
        livro.forEach(e -> e.setAutor(this));
        this.livro = livro;
    }

    @Override
    public String toString() {
        List<String> livroNomes = this.livro.stream().map(Livro::getNome).collect(Collectors.toUnmodifiableList());
        return "Autor{" +
                " nome: " + nome +
                ", Ano do nascimento: " + dataNasc +
                ", Ano da morte: " + dataMorte +
                ", livros: " + livroNomes  +
                '}' + "\n";
    }
}
