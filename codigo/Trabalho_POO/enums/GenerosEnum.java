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

}
