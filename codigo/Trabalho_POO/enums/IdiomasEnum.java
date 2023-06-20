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

}
