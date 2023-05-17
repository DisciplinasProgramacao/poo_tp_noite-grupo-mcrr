package trabalho_POO;

import java.util.ArrayList;
import java.util.List;

public class ServicoStreaming {
    private List<Cliente> clientes;
    private List<Serie> series;

    public ServicoStreaming() {
        this.clientes = new ArrayList<>();
        this.series = new ArrayList<>();
    }

    public void adicionarCliente(Cliente cliente) {
        this.clientes.add(cliente);
    }

    public void adicionarSerie(Serie serie) {
        this.series.add(serie);
    }

    public List<Serie> buscarSerie(String nome, String genero, String idioma) {
        List<Serie> result = new ArrayList<>();
        for (Serie serie : series) {
            if (serie.getNome().equals(nome) && serie.getGenero().equals(genero) && serie.getIdioma().equals(idioma)) {
                result.add(serie);
            }
        }
        return result;
    }
}
