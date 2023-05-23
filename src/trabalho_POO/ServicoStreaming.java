package trabalho_POO;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ServicoStreaming {
    private List<Cliente> clientes;
    private List<Serie> series;
    private List<Filme> filmes;
    private CSVReader csvReader;

    public ServicoStreaming() {
        this.clientes = new ArrayList<>();
        this.series = new ArrayList<>();
        this.filmes = new ArrayList<>();
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
    public void carregarDados(String clientesArquivo, String seriesArquivo, String audienciaArquivo) throws IOException {
        this.clientes = csvReader.readClientes(clientesArquivo);
        this.series = csvReader.readSeries(seriesArquivo);
        this.csvReader = new CSVReader();
        List<Auditoria> auditorias = csvReader.readAudiencia(audienciaArquivo);

        for (Auditoria auditoria : auditorias) {
            Cliente cliente = findClienteByLogin(auditoria.getLogin());
            Serie serie = findSerieById(auditoria.getIdSerie());
            if (cliente != null && serie != null) {
                if (auditoria.getTipo() == 'F') {
                    cliente.adicionarParaAssistir(serie);
                } else if (auditoria.getTipo() == 'A') {
                    cliente.adicionarAssistida(serie);
                }
            }
        }
    }
    public void adicionarFilme(Filme filme) {
        this.filmes.add(filme);
    }
    
    public void carregarFilmes(String filmesArquivo) throws IOException {
        List<Filme> filmes = csvReader.readFilmes(filmesArquivo);
        for (Filme filme : filmes) {
        	this.adicionarFilme(filme);
        }
    }
    

    private Cliente findClienteByLogin(String login) {
        for (Cliente cliente : clientes) {
            if (cliente.getLogin().equals(login)) {
                return cliente;
            }
        }
        return null;
    }

    private Serie findSerieById(int id) {
        for (Serie serie : series) {
            if (serie.getId() == id) {
                return serie;
            }
        }
        return null;
    }
}
