package trabalho_POO;

public class Filme extends Midia {
    private int duracao;

    public Filme(int id, String nome, String idioma, String genero, int duracao) {
        super(id, nome, idioma, genero);
        this.duracao = duracao;
    }

    public int getDuracao() {
        return duracao;
    }
}

