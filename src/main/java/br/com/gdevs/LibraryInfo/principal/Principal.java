package br.com.gdevs.LibraryInfo.principal;

import br.com.gdevs.LibraryInfo.model.*;
import br.com.gdevs.LibraryInfo.repository.RepositorioAutor;
import br.com.gdevs.LibraryInfo.repository.RepositorioLivro;
import br.com.gdevs.LibraryInfo.service.ConsumoApi;
import br.com.gdevs.LibraryInfo.service.ConverteDados;
import org.springframework.dao.DataIntegrityViolationException;

import java.util.InputMismatchException;
import java.util.Optional;
import java.util.Scanner;

public class Principal {


    private RepositorioLivro repositorioLivro;
    private RepositorioAutor repositorioAutor;
    private ConsumoApi consumoApi = new ConsumoApi();
    public ConverteDados conversor = new ConverteDados();
    private Scanner scan = new Scanner(System.in);
    private int option = -1;
    private DadosLivro livro;
    private String BASE_URL = "https://gutendex.com/books/?search=";


    public Principal(RepositorioLivro repositorioLivro, RepositorioAutor repositorioAutor) {
        this.repositorioLivro = repositorioLivro;
        this.repositorioAutor = repositorioAutor;
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
                    8)- Estatisticas
                    0)- Sair
                    
                    """);
            try {
                option = scan.nextInt();
                scan.nextLine();
            }catch (InputMismatchException e){
                System.out.println("Charactere inválido, digite a opção novamente.");
                continue;
            }


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
            }
        }






//        var json = consumoApi.obterDados("https://gutendex.com/books/?search=dom+casmurro");
//
//        System.out.println(json);



    }

    private void buscarAutoresRegistrados() {
    }

    private void buscarLivrosRegistrados() {
    }

    private void buscarLivroPorTitulo() {
        System.out.println("Nome do livro: ");
        String livroNome ="";
        Autor autor = new Autor();
        Livro livro1 = new Livro();


        try{
            livroNome = scan.nextLine();
            var json = consumoApi.obterDados(BASE_URL+livroNome.replace(" ", "+"));
            System.out.println(json);
            Resposta resposta = conversor.obterDados(json, Resposta.class);
            System.out.println(resposta);
            try{

                livro = resposta.livro().get(0);
                System.out.println(resposta);

                DadosLivro dadosLivro = new DadosLivro(livro.id(), livro.nome(), livro.dadosAutor(), livro.idioma(), livro.numeroDownloads());
                System.out.println(dadosLivro);
                var autorList = dadosLivro.dadosAutor();
                DadosAutor dadosAutor = new DadosAutor(autorList.get(0).nome(), autorList.get(0).dataNasc(), autorList.get(0).dataMorte());
                autor = new Autor(dadosAutor);
                livro1 = new Livro(dadosLivro);
                livro1.setAutor(autor);
                System.out.println(dadosAutor);
            }catch (IndexOutOfBoundsException e){
                System.out.println("Nenhum livro encontrado"+ e);
            }

            try{
                repositorioLivro.save(livro1);

            }catch (DataIntegrityViolationException e){
                throw new RuntimeException(e);
            }



            System.out.println(livro1);

        }catch (IllegalArgumentException e){
            throw new RuntimeException(e);
        }

//        private Optional<Livro> verificarDuplicidade(){
//
//            return Optional<Livro> livro();
//        }



//        List <DadosLivro> dadosLivro = resposta.livro().stream()
//                        .map(l -> new DadosLivro(l.id(), l.nome(), l.dadosAutor(), l.idioma(), l.numeroDownloads())).collect(Collectors.toList());





    }
}
