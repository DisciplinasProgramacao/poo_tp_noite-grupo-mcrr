package trabalho_POO;

import java.util.ArrayList;
import java.util.List;

public class Cliente {
    private String login;
    private List<Serie> listaParaAssistir;
    private List<Serie> listaAssistidas;

    public Cliente(String login) {
        this.login = login;
        this.listaParaAssistir = new ArrayList<>();
        this.listaAssistidas = new ArrayList<>();
    }

    public void adicionarParaAssistir(Serie serie) {
        this.listaParaAssistir.add(serie);
    }

    public void adicionarAssistida(Serie serie) {
        this.listaAssistidas.add(serie);
        serie.incrementarVisualizacoes();
    }

    public List<Serie> buscarSerie(String nome, String genero, String idioma) {
        List<Serie> result = new ArrayList<>();
        for (Serie serie : listaParaAssistir) {
            if (serie.getNome().equals(nome) && serie.getGenero().equals(genero) && serie.getIdioma().equals(idioma)) {
                result.add(serie);
            }
        }
        return result;
    }
}
