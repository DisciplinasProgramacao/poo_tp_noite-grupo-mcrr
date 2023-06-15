package Trabalho_POO.enums;

import java.util.Random;

public enum GenerosEnum {
    Ação,
    Anime,
    Aventura,
    Comédia,
    Documentário,
    Drama,
    Policial,
    Romance,
    Suspense;

    /**
     * Método para gerar generos aleatórios e retornar um array de String
     * 
     * @return
     */
    public static String[] GenerosAleatorios() {
        Random random = new Random();
        int index = random.nextInt(0, 7);
        GenerosEnum g1 = GenerosEnum.values()[index];
        GenerosEnum g2 = GenerosEnum.values()[++index];
        String[] generos = { g1.toString(), g2.toString() };
        return generos;
    }

    /**
     * Método de exibição dos generos
     * 
     */
    public static void PrintEnums() {
        System.out.println("1 - Ação");
        System.out.println("2 - Anime");
        System.out.println("3 - Aventura");
        System.out.println("4 - Comédia");
        System.out.println("5 - Documentário");
        System.out.println("6 - Drama");
        System.out.println("7 - Policial");
        System.out.println("8 - Romance");
        System.out.println("9 - Suspense");
    }
}
