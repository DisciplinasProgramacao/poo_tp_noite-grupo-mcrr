package trabalho_POO;

public class Midia {
    protected int id;
    protected String nome;
    protected String idioma;
    protected String genero;
    protected int numeroDeVisualizacoes;

    public Midia(int id, String nome, String idioma, String genero) {
        this.id = id;
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

    public int getId() {
        return id;
    }
}

