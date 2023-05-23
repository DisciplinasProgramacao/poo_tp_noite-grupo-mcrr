package trabalho_POO;

import java.util.List;

public class Serie extends Midia {
    private int numeroDeEpisodios;
    private List<Midia> midias;

    public Serie(int id, String nome, String idioma, String genero, int numeroDeEpisodios) {
        super(id, nome, idioma, genero);
        this.numeroDeEpisodios = numeroDeEpisodios;
    }

    public int getNumeroDeEpisodios() {
        return numeroDeEpisodios;
    }
}

