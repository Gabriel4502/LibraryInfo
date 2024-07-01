package br.com.gdevs.LibraryInfo.principal;

import br.com.gdevs.LibraryInfo.service.ConsumoApi;

import java.util.Scanner;

public class Principal {

    private ConsumoApi consumoApi = new ConsumoApi();
        private Scanner scan = new Scanner(System.in);
        private String BASE_URL = "https://gutendex.com/books&search=";

    public void exibeMenu() {

        System.out.println("Nome do autor: ");
        var autor = scan.nextLine();


        var json = consumoApi.obterDados(BASE_URL + autor.replace(" ", "+"));

        System.out.println(json);



    }
}
