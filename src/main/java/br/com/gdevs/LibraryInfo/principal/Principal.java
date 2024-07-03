package br.com.gdevs.LibraryInfo.principal;

import br.com.gdevs.LibraryInfo.model.*;
import br.com.gdevs.LibraryInfo.repository.RepositorioAutor;
import br.com.gdevs.LibraryInfo.repository.RepositorioLivro;
import br.com.gdevs.LibraryInfo.service.ConsumoApi;
import br.com.gdevs.LibraryInfo.service.ConverteDados;
import br.com.gdevs.LibraryInfo.service.InfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;

import java.time.Year;
import java.util.*;

public class Principal {


    private RepositorioLivro repositorioLivro;
    private RepositorioAutor repositorioAutor;
    private ConsumoApi consumoApi = new ConsumoApi();
    public ConverteDados conversor = new ConverteDados();
    private Scanner scan = new Scanner(System.in);
    @Autowired
    private InfoService service;
    private int option = -1;
    private DadosLivro livroDados;

    private String BASE_URL = "https://gutendex.com/books/?search=";


    public Principal(RepositorioLivro repositorioLivro, RepositorioAutor repositorioAutor, InfoService service) {
        this.repositorioLivro = repositorioLivro;
        this.repositorioAutor = repositorioAutor;
        this.service = service;
    }

    public void exibeMenu() {


        while(option != 0){

            System.out.println("""
                    
                    1)- Buscar livro pelo titulo
                    2)- Listar livros registrados
                    3)- Listar autores registrados
                    4)- Listar autores vivos em um determinado ano
                    5)- Listar livros em um determinado idioma
                    6)- Top10 livros mais baixados
                    7)- Buscar autor por nome
                    0)- Sair
                    
                    """);
//            try {
//                option = scan.nextInt();
//
//            }catch (InputMismatchException e){
//                System.out.println("Charactere inválido, digite a opção novamente.");
//                scan.nextLine();
//                continue;
//            }

            option = scan.nextInt();
            scan.nextLine();

            switch (option){
                case 1:
                    buscarLivroPorTitulo();
                    break;
                    
                case 2:
                    buscarLivrosRegistrados();
                    break;
                    
                case 3:
                    buscarAutoresRegistrados();
                    break;

                case 4:
                    buscarLivrosPorAno();
                    break;

                case 5:
                    buscarLivrosPorIdioma();
                    break;

                case 6:
                    buscarTop10LivrosBaixados();
                    break;

                case 7:
                    buscarAutorPorNome();
                    break;

            }
        }


    }




    private void buscarLivroPorTitulo() {
        System.out.println("Nome do livro: ");
        String livroNome ="";
        Autor autor = new Autor();
        List <Livro> livro1 = new ArrayList<>();

        String json;

        try{
            livroNome = scan.nextLine();
            json = consumoApi.obterDados(BASE_URL+livroNome.replace(" ", "+"));
            System.out.println(json);
        }catch (IllegalArgumentException e){
            throw new RuntimeException(e);
        }
            Resposta resposta = conversor.obterDados(json, Resposta.class);
            System.out.println(resposta);
            try{

                livroDados = resposta.livro().get(0);
                System.out.println(resposta);

                DadosLivro dadosLivro = new DadosLivro(livroDados.id(), livroDados.nome(), livroDados.dadosAutor(), livroDados.idioma(), livroDados.numeroDownloads());
                System.out.println(dadosLivro);

                var autorDados = dadosLivro.dadosAutor();
                DadosAutor dadosAutor = new DadosAutor(autorDados.get(0).nome(), autorDados.get(0).dataNasc(), autorDados.get(0).dataMorte());

                autor = new Autor(dadosAutor);

                livro1.add(new Livro(dadosLivro));

                System.out.println(autor);
                System.out.println(dadosAutor);
            }catch (IndexOutOfBoundsException e){
                System.out.println("Nenhum livro encontrado"+ e);
                return;
            }

        Optional<Autor> autorExistente = service.verificarDuplicidade(autor);
        System.out.println(autorExistente);

            if(autorExistente.isPresent()){
                var autorEncontrado = autorExistente.get();
                System.out.println(autorEncontrado);
              autorEncontrado.setLivro(livro1);
                System.out.println(livro1);
            }else{
                repositorioAutor.save(autor);
                livro1.get(0).setAutor(autor);
                System.out.println(livro1);
            }

        try {
            repositorioLivro.save(livro1.get(0));
            System.out.println(livro1);
        } catch (DataIntegrityViolationException e) {
            System.out.println("Erro ao salvar livro: " + e.getMessage());
        }

    }

    private void buscarAutoresRegistrados() {
        List <Autor> autoresLista = service.obterAutoresRegistrados();
        autoresLista.forEach(System.out::println);
    }

    private void buscarLivrosRegistrados() {
        List livrosLista = service.obterTodosOsLivros();
        System.out.println(livrosLista);
    }

    private void buscarLivrosPorAno() {
        System.out.println("Qual ano deseja verificar autor vivo?");
        Integer ano = scan.nextInt();
        Year year = Year.of(ano);
        List<Autor> autores = service.obterAutoresVivosEmAno(year);
        System.out.println("Autores vivos nos anos " + ano);
        System.out.println(autores);
    }


    private void buscarLivrosPorIdioma() {

        System.out.println("""
                **************************************
                Insira o idioma para realizar a busca
                pt-Português
                en-Inglês
                es-Espanhol
                de-Dinamarquês
                fr-Franês
                fi-Finlandês
                fr-Francês
                """);
        var nomeIdioma = scan.nextLine();
        Idiomas idiomas = Idiomas.fromString(nomeIdioma);
        List<Livro> livrosPorCategoria = service.buscarLivrosEmIdioma(idiomas);
        if(livrosPorCategoria.isEmpty()){
            System.out.println("Nenhum livro encontrado para esse idioma");
        }else{
            System.out.println("Livros em " + Idiomas.fromString(nomeIdioma));
            System.out.println(livrosPorCategoria);
        }
    }

    private void buscarAutorPorNome() {
        System.out.println("Qual autor deseja pesquisar? ");
        var nomeAtor = scan.nextLine();
        List<Autor> autor = service.buscarAutoresPorNome(nomeAtor);
        if(autor.isEmpty()){
            System.out.println("Nenhum ator encontrado");
        }else{

            System.out.println(autor);
        }
    }


    private void buscarTop10LivrosBaixados() {
        List <Livro> livrosLista = service.top10LivrosMaisBaixados();
        System.out.println(livrosLista);
    }








}
