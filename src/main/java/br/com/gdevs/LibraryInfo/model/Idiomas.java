package br.com.gdevs.LibraryInfo.model;

public enum Idiomas {
    PORTUGUES("pt"),
    INGLES("en"),
    FRANCES("fr"),
    FINLANDES("fi"),
    DINAMARQUES("de");

    private String idiomaAPI;
    Idiomas (String idiomaAPI){
        this.idiomaAPI = idiomaAPI;

    }

    public static Idiomas fromString(String text){
        for(Idiomas idiomas : Idiomas.values() ){
            if(idiomas.idiomaAPI.equalsIgnoreCase(text)){
                return idiomas;
            }
        }
        throw new IllegalArgumentException("Nehum idioma encontrado para a string fornecida" + text);
    }

}
