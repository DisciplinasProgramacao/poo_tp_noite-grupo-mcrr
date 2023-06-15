package Trabalho_POO.enums;

import java.util.Random;

public enum IdiomasEnum {
    Inglês,
    Português,
    Francês,
    Alemão,
    Chinês,
    Espanhol,
    Italiano;

    /**
     * Método para gerar idiomas aleatórios e retornar um array de String
     * 
     * @return
     */
    public static String[] IdiomasAleatorios() {
        Random random = new Random();
        int index = random.nextInt(0, 5);
        IdiomasEnum g1 = IdiomasEnum.values()[index];
        IdiomasEnum g2 = IdiomasEnum.values()[++index];
        String[] generos = { g1.toString(), g2.toString() };
        return generos;
    }

    /**
     * Método de exibição dos idiomas
     */
    public static void PrintEnums() {
        System.out.println("1 - Inglês");
        System.out.println("2 - Português");
        System.out.println("3 - Francês");
        System.out.println("4 - Alemão");
        System.out.println("5 - Chinês");
        System.out.println("6 - Espanhol");
        System.out.println("7 - Italiano");
    }
}
