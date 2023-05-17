package trabalho_POO;

public class Serie {
    private String nome;
    private String idioma;
    private String genero;
    private int numeroDeVisualizacoes;

    public Serie(String nome, String idioma, String genero) {
        this.nome = nome;
        this.idioma = idioma;
        this.genero = genero;
        this.numeroDeVisualizacoes = 0;
    }

    public void incrementarVisualizacoes() {
        this.numeroDeVisualizacoes++;
    }

    public String getNome() {
        return nome;
    }

    public String getIdioma() {
        return idioma;
    }

    public String getGenero() {
        return genero;
    }
}
