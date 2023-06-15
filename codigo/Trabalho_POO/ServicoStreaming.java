package Trabalho_POO;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.stream.Stream;

import Trabalho_POO.Exceptions.MediaAlreadyCommentedException;
import Trabalho_POO.Exceptions.MediaAlreadyEvaluatedException;
import Trabalho_POO.Exceptions.MediaNotFoundException;
import Trabalho_POO.Exceptions.MediaNotWatchedException;
import Trabalho_POO.Exceptions.UserIsNotProfessionalException;
import Trabalho_POO.Exceptions.UserNotFoundException;
import Trabalho_POO.enums.GenerosEnum;
import Trabalho_POO.models.Avaliacao;
import Trabalho_POO.models.Comentario;
import Trabalho_POO.models.Midia;

public class ServicoStreaming {
    // #region Properties
    private ArrayList<Midia> catalogo;
    private ArrayList<Cliente> clientes;
    private Reader reader;
    private Cliente clienteAtual;

    // #endregion Properties
    // #region Public
    /**
     * Método construtor de classe
     * 
     * @param pathClienteData
     * @param pathSeriesData
     * @param pathFilmesData
     * @param pathAudienciaData
     */
    public ServicoStreaming(String pathClienteData, String pathSeriesData, String pathFilmesData,
            String pathAudienciaData) {
        catalogo = new ArrayList<>();
        clientes = new ArrayList<Cliente>();
        reader = new Reader();
        clienteAtual = null;
        try {
            CarregarDados(pathClienteData, pathSeriesData, pathFilmesData, pathAudienciaData);
        } catch (Exception e) {
            System.out.println(e);
        }

    }

    /**
     * Método de autenticação de usuário baseado no seu login e senha
     * 
     * @param login
     * @param senha
     */
    public void Login(String login, String senha) {
        for (Cliente cliente : clientes) {
            if (cliente.Login().equals(login)) {
                boolean logado = cliente.VerificarUsuario(senha);

                if (logado) {
                    clienteAtual = cliente;
                    return;
                }
            }
        }
    }

    /**
     * Método de logout que apenas marca o clienteAtual como nulo
     */
    public void Logout() {
        clienteAtual = null;
    }

    /**
     * Método que de registro que verifica e cria um novo cliente
     * 
     * @param login
     * @param senha
     * @param nome
     */
    public void Registrar(String login, String senha, String nome) {
        for (Cliente cliente : clientes) {
            if (cliente.Login().equals(login)) {
                ClearScreen();
                System.out.println(
                        "Não foi possível criar seu perfil pois já existe alguem registrado com este login");
                System.out.println();
                return;
            }
        }
        Cliente cliente = new Cliente(nome, login, senha, false);
        this.clientes.add(cliente);
        ClearScreen();
        System.out.println("Perfil criado com sucesso!");
        System.out.println();
    }

    /**
     * Método para deletar conta do usuário
     */
    public void DeleteUser() {
        for (Cliente cliente : clientes) {
            if (cliente.Login().equalsIgnoreCase(clienteAtual.Login())) {
                clientes.remove(cliente);
                System.out.println("Conta deletada com sucesso");
            }
        }
    }

    /**
     * Método que retorna as mídias assistidas do usuário que está logado
     * 
     * @return ArrayList<Midia>
     */
    public ArrayList<Midia> Assistida() {
        return clienteAtual.MidiasAssistidas();
    }

    /**
     * Método que retorna as mídias marcadas para assistir futuramente do usuário
     * que está logado
     * 
     * @return ArrayList<Midia>
     */
    public ArrayList<Midia> Futuras() {
        return clienteAtual.MidiasFuturas();
    }

    /**
     * Método que adiciona uma mídia como assistida no usuário que esta logado
     * 
     * @param midia
     */
    public void AdicionarAssistida(Midia midia) {
        Audiencia audiencia = new Audiencia(clienteAtual.Login(), midia.Id, 'A');
        clienteAtual.AdicionarMidiaAssistida(midia);
        midia.AddAudiencia(audiencia);
    }

    /**
     * Método que adiciona uma mídia para assistir futuramente no usuário que está
     * logado
     * 
     * @param midia
     */
    public void AdicionarMidiaFutura(Midia midia) {
        Audiencia audiencia = new Audiencia(clienteAtual.Login(), midia.Id, 'F');
        clienteAtual.AdicionarMidiaFutura(midia);
        midia.AddAudiencia(audiencia);
    }

    /**
     * Método que remove uma mídia marcada para assistir futuramente do usuário que
     * está logado
     * 
     * @param midia
     */
    public void RemoverMidiaFutura(Midia midia) {
        Audiencia audiencia = new Audiencia(clienteAtual.Login(), midia.Id, 'F');
        clienteAtual.RemoverMidiaFutura(midia);
        midia.RemoveAudiencia(audiencia);
    }

    /**
     * Método que verifica e adiciona um comentário em uma mídia e na lista do
     * usuário que está logado
     * 
     * @param midia
     * @param ent
     * @throws MediaAlreadyCommentedException
     * @throws MediaNotWatchedException
     * @throws UserIsNotProfessionalException
     */
    public void AdicionarComentario(Midia midia, Scanner ent)
            throws MediaAlreadyCommentedException, MediaNotWatchedException, UserIsNotProfessionalException {
        if (!JaAssistiu(midia)) {
            throw new MediaNotWatchedException("Só é possível comentar filmes ou séries que você já assistiu.");
        }
        if (!clienteAtual.EhEspecialista()) {
            throw new UserIsNotProfessionalException(
                    "Só é possível comentar se você for um usuário profissional. Para atingir esse patamar é necessário que você tenha avaliado pelo menos 5 filmes no ultimo mês");
        }
        if (JaComentou(midia)) {
            throw new MediaAlreadyCommentedException("Só é possível comentar uma vez por filme ou série");
        }
        try {
            System.out.print("Digite seu comentário: ");
            String comentario = ent.nextLine();
            midia.Comentar(comentario, clienteAtual.Login());
            clienteAtual.AdicionarMidiaComentada(comentario, midia.Id);
        } catch (Exception e) {
            return;
        }

    }

    /**
     * Método para exibição do usuário atual
     */
    public void PrintUsuario() {
        System.out.println("Nome: " + clienteAtual.Nome());
        System.out.println("Usuário: " + clienteAtual.Login());
        System.out.println("Senha: ******");
        System.out.println("-------------------------------");
    }

    /**
     * Método que verifica e altera o login do usuário
     * 
     * @param login
     * @throws Exception
     */
    public void AlterarLogin(String login) throws Exception {
        for (Cliente cliente : clientes) {
            if (cliente.Login().equalsIgnoreCase(login)) {
                throw new Exception("Não foi possível alterar o login pois esse login já está registrado");
            }
        }
        this.clienteAtual.AlterarLogin(login);
    }

    /**
     * Método que altera o nome do usuário
     * 
     * @param nome
     */
    public void AlterarNome(String nome) {
        this.clienteAtual.AlterarNome(nome);
    }

    /**
     * Método que altera a senha do usuário
     * 
     * @param senha
     */
    public void AlterarSenha(String senha) {
        this.clienteAtual.AlterarSenha(senha);
    }

    /**
     * Método que verifica e adiciona uma avaliação à uma mídia e na lista de
     * avaliações do usuário logado
     * 
     * @param midia
     * @param ent
     * @throws MediaAlreadyEvaluatedException
     * @throws MediaNotWatchedException
     */
    public void AdicionarAvaliacao(Midia midia, Scanner ent)
            throws MediaAlreadyEvaluatedException, MediaNotWatchedException {

        if (!JaAssistiu(midia))
            throw new MediaNotWatchedException("Só é possível avaliar filmes ou séries já assistidas.");

        if (JaAvaliou(midia)) {
            throw new MediaAlreadyEvaluatedException("Só é possível avaliar uma vez por mídia");
        }
        try {
            System.out.println("1 - Muito ruim");
            System.out.println("2 - Ruim");
            System.out.println("3 - Médio");
            System.out.println("4 - Bom");
            System.out.println("5 - Muito bom");
            System.out.println();
            System.out.print("Sua avaliação: ");
            int avaliacao = ent.nextInt();
            ent.nextLine();
            System.out.println();
            // ClearScreen();
            midia.Avaliar(avaliacao, clienteAtual.Login());
            clienteAtual.AdicionarMidiaAvaliada(avaliacao, midia.Id);
        } catch (Exception e) {
            return;
        }

    }

    /**
     * Método de busca para achar uma mídia por titulo
     * 
     * @param title
     * @param midias
     * @return Midia
     * @throws MediaNotFoundException
     */
    public Midia findMediaByTitle(String title, ArrayList<Midia> midias) throws MediaNotFoundException {
        for (Midia midia : midias) {
            if (midia.titulo.equalsIgnoreCase(title))
                return midia;
        }
        throw new MediaNotFoundException("Nenhuma mídia encontrada com o titulo: " + title);
    }

    /**
     * Método de busca para achar uma mídia por genero
     * 
     * @param genero
     * @param midias
     * @return ArrayList<Midia>
     * @throws MediaNotFoundException
     */
    public ArrayList<Midia> findMediasByGender(String genero, ArrayList<Midia> midias) throws MediaNotFoundException {
        ArrayList<Midia> response = new ArrayList<Midia>();
        for (Midia media : midias) {
            for (String generoMedia : media.generos) {
                if (generoMedia.equalsIgnoreCase(genero)) {
                    response.add(media);
                }
            }
        }
        if (response.size() > 0)
            return response;

        throw new MediaNotFoundException("Nenhuma mídia encontrada com o gênero: " + genero);
    }

    /**
     * Método de busca para achar uma mídia por idioma
     * 
     * @param language
     * @param midias
     * @return ArrayList<Midia>
     * @throws MediaNotFoundException
     */
    public ArrayList<Midia> findMediaByLanguage(String language, ArrayList<Midia> midias)
            throws MediaNotFoundException {
        ArrayList<Midia> response = new ArrayList<Midia>();
        for (Midia media : midias) {
            for (String idiomaMedia : media.idiomas) {
                if (idiomaMedia.equalsIgnoreCase(language)) {
                    response.add(media);
                }
            }
        }
        if (response.size() > 0)
            return response;

        throw new MediaNotFoundException("Nenhuma mídia encontrada com o idioma: " + language);
    }

    /**
     * Método de busca para achar um usuário pelo login
     * 
     * @param login
     * @return Cliente
     * @throws UserNotFoundException
     */
    public Cliente findClientByLogin(String login) throws UserNotFoundException {
        for (Cliente cliente : clientes) {
            if (cliente.Login().equalsIgnoreCase(login))
                return cliente;
        }
        throw new UserNotFoundException("Nenhum usuário encontrado com o login: " + login);
    }

    /**
     * Método para gerar relatórios gerenciais
     */
    public void GerarRelatorio() {
        System.out.println();
        System.out.println("Relatório gerencial");
        System.out.println();
        try {
            System.out.println("Cliente com mais mídias assistidas: " + ClienteComMaisMidiasAssistidas().Login());
            System.out.println("Quantidade de mídias: " + ClienteComMaisMidiasAssistidas().MidiasAssistidas().size());
            System.out.println("--------------------------------------------------------");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        try {
            Cliente resposta = ClienteComMaisAvaliacoes();
            var cliente = resposta == null ? "Nenhum cliente avaliou ainda" : resposta;
            var quantidadeDeAvaliacoes = resposta == null ? 0 : resposta.MidiasAvaliadas().size();
            System.out.println("Cliente com mais avaliações: " + cliente);
            System.out.println("Quantidade de avaliações: " + quantidadeDeAvaliacoes);
            System.out.println("--------------------------------------------------------");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        try {
            System.out.println("Porcentagem de clientes com menos de 15 avaliações: "
                    + PorcentagemDeClientesComMenosQuinzeAvaliacoes() + "%");
            System.out.println("--------------------------------------------------------");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        try {
            System.out
                    .println(
                            "Top 10 Mídias com melhor média de avaliações e que tenham sido vistas pelo menos 100 vezes em ordem decrescente: ");
            var topDezMidiasBaseadoEmAvaliacao = TopDezMidiasBaseadoEmAvaliacao();
            if (topDezMidiasBaseadoEmAvaliacao.size() > 0) {
                for (Midia midia : topDezMidiasBaseadoEmAvaliacao) {
                    System.out.println("Titulo: " + midia.titulo);
                    System.out.println("--------------------------------");
                }
            } else {
                System.out.println("Nenhuma mídia avaliada ainda");
            }

            System.out.println("--------------------------------------------------------");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        try {
            System.out.println("Top 10 mídias com mais visualizações, em ordem decrescente: ");
            var TopDezMidiasComMaisVisualizacoes = TopDezMidiasComMaisVisualizacoes(catalogo);
            for (Midia midia : TopDezMidiasComMaisVisualizacoes) {
                Stream<Audiencia> audienciasStream = midia.Audiencia().stream()
                        .filter(audiencia -> audiencia.tipo == 'A');
                System.out.println("Titulo: " + midia.titulo);
                System.out.println("Quantidade de visualizações: " + audienciasStream.count());
                System.out.println("--------------------------------");
            }
            System.out.println("--------------------------------------------------------");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        try {
            for (int i = 0; i < GenerosEnum.values().length; i++) {
                String generobusca = GenerosEnum.values()[i].toString();
                System.out.println("Top 10 Mídias de gênero " +
                        generobusca
                        + " com melhor média de avaliações e que tenham sido vistas pelo menos 100 vezes em ordem decrescente: ");
                var top10MidiasPorGeneroEmAvaliacoes = Top10MidiasPorGeneroEmAvaliacoes(generobusca);
                for (Midia midia : top10MidiasPorGeneroEmAvaliacoes) {
                    System.out.println("Titulo : " + midia.titulo);
                    System.out.println("--------------------------------");
                }
            }
            System.out.println("--------------------------------------------------------");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        try {
            for (int i = 0; i < GenerosEnum.values().length; i++) {
                String generobusca = GenerosEnum.values()[i].toString();
                System.out.println("Top 10 Mídias de gênero " +
                        generobusca
                        + "com mais visualizações, em ordem decrescente: ");
                var top10MidiasPorGeneroEmVisualizacoes = Top10MidiasPorGeneroEmVisualizacoes(generobusca);
                if (top10MidiasPorGeneroEmVisualizacoes.size() > 0) {
                    for (Midia midia : top10MidiasPorGeneroEmVisualizacoes) {
                        Stream<Audiencia> audienciasStream = midia.Audiencia().stream()
                                .filter(audiencia -> audiencia.tipo == 'A');
                        System.out.println("Titulo: " + midia.titulo);
                        System.out.println("Quantidade de visualizações: " + audienciasStream.count());
                        System.out.println("--------------------------------");
                    }
                } else {
                    System.out.println("Nenhuma mídia do gênero " + generobusca + " visualizada");
                }

            }
            System.out.println("--------------------------------------------------------");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

    }

    /**
     * Método que verifica se tem um usuário logado e retorna true caso tenha e
     * false caso não tenha
     * 
     * @return boolean
     */
    public boolean IsLogged() {
        if (clienteAtual == null)
            return false;
        return true;
    }

    /**
     * Método para ver todo catálogo
     * 
     * @return ArrayList<Midia>
     */
    public ArrayList<Midia> Catalogo() {
        return catalogo;
    }

    /**
     * Método de exibiçào de uma mídia
     * 
     * @param midia
     * @return void
     */
    public void PrintMidia(Midia midia) {
        if (clienteAtual.EhEspecialista()) {
            if (midia instanceof Serie) {
                Serie serie = (Serie) midia;
                System.out.println("Titulo: " + serie.titulo);
                System.out.println("Gêneros: " + serie.generos[0] + ", " + serie.generos[1]);
                System.out.println("Idiomas: " + serie.idiomas[0] + ", " + serie.idiomas[1]);
                System.out.println("Lançamento: " + (serie.EhLancamento() ? "Sim" : "Não"));
                System.out.println("Avaliação: "
                        + (serie.Avaliacoes().size() > 0 ? serie.MediaDeAvaliacoes() + "%" : "Ainda não avaliado"));

            } else {
                Filme filme = (Filme) midia;
                System.out.println("Titulo: " + filme.titulo);
                System.out.println("Duração: " + filme.duracao);
                System.out.println("Gêneros: " + filme.generos[0] + ", " + filme.generos[1]);
                System.out.println("Idiomas: " + filme.idiomas[0] + ", " + filme.idiomas[1]);
                System.out.println("Lançamento: " + (filme.EhLancamento() ? "Sim" : "Não"));
                System.out.println("Avaliação: "
                        + (filme.Avaliacoes().size() > 0 ? filme.MediaDeAvaliacoes() + "%" : "Ainda não avaliado"));

            }
            System.out.println("-------------------------------------------------------");
        } else {
            if (!midia.EhLancamento()) {
                if (midia instanceof Serie) {
                    Serie serie = (Serie) midia;
                    System.out.println("Titulo: " + serie.titulo);
                    System.out.println("Gêneros: " + serie.generos[0] + ", " + serie.generos[1]);
                    System.out.println("Idiomas: " + serie.idiomas[0] + ", " + serie.idiomas[1]);
                    System.out.println("Lançamento: " + (serie.EhLancamento() ? "Sim" : "Não"));
                    System.out.println("Avaliação: "
                            + (serie.Avaliacoes().size() > 0.0 ? serie.MediaDeAvaliacoes() + "%"
                                    : "Ainda não avaliado"));

                } else {
                    Filme filme = (Filme) midia;
                    System.out.println("Titulo: " + filme.titulo);
                    System.out.println("Duração: " + filme.duracao + " minutos");
                    System.out.println("Gêneros: " + filme.generos[0] + ", " + filme.generos[1]);
                    System.out.println("Idiomas: " + filme.idiomas[0] + ", " + filme.idiomas[1]);
                    System.out.println("Lançamento: " + (filme.EhLancamento() ? "Sim" : "Não"));
                    System.out.println("Avaliação: "
                            + (filme.Avaliacoes().size() > 0 ? filme.MediaDeAvaliacoes() + "%" : "Ainda não avaliado"));

                }
                System.out.println("-------------------------------------------------------");
            }

        }
    }

    /**
     * Método getter que verifica o usuário atual
     * 
     * @return Cliente
     */
    public Cliente GetCliente() {
        return clienteAtual;
    }

    /**
     * Método de exibição de comentários
     * 
     * @param midia
     * @return void
     */
    public void PrintComentarios(Midia midia) {
        ArrayList<Comentario> comentarios = midia.Comentarios();

        if (comentarios.size() > 0) {
            String mensagem = midia instanceof Serie ? "Comentários da série: " + midia.titulo
                    : "Comentários do Filme: " + midia.titulo;
            System.out.println(mensagem);
            System.out.println();

            for (Comentario comentario : comentarios) {
                System.out.println(comentario.userLogin + ": " + comentario.comentario);
                System.out.println();
            }
        } else {
            String mensagem = midia instanceof Serie ? "Nenhum comentário ainda para a série: " + midia.titulo
                    : "Nenhum comentário ainda para o filme: " + midia.titulo;
            System.out.println(mensagem);
        }

    }

    // #endregion Public
    // #region Private
    /**
     * Método para limpar a tela
     */
    private static void ClearScreen() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }

    /**
     * Método de busca de mídia pelo id da mídia
     * 
     * @param id
     * @return
     * @throws MediaNotFoundException
     */
    private Midia findMediaById(int id) throws MediaNotFoundException {
        for (Midia midia : catalogo) {
            if (midia.Id == id)
                return midia;
        }
        throw new MediaNotFoundException("Nenhuma mídia encontrada com o id: " + id);
    }

    /**
     * Método para adicionar uma mídia nova
     * 
     * @param midia
     */
    private void AdicionarMidia(Midia midia) {
        this.catalogo.add(midia);
    }

    /**
     * Método para preencher os dados de audiência
     * 
     * @param audiencia
     */
    private void PreencherAudiencia(ArrayList<Audiencia> audiencia) {
        Map<String, Audiencia> audienciaMap = new HashMap<>();
        for (Audiencia audienciaAdicionar : audiencia) {
            audienciaMap.put(audienciaAdicionar.Login, audienciaAdicionar);
        }
        for (Cliente cliente : clientes) {
            Audiencia audienciaMapped = audienciaMap.getOrDefault(cliente.Login(), null);
            if (audienciaMapped != null) {
                try {
                    Midia midia = findMediaById(audienciaMapped.IdSerie);
                    midia.AddAudiencia(audienciaMapped);
                    if (audienciaMapped.tipo == 'F') {
                        cliente.AdicionarMidiaFutura(midia);
                    } else {
                        cliente.AdicionarMidiaAssistida(midia);
                    }
                } catch (Exception e) {
                    System.err.println(e);
                }
            }

        }
    }

    /**
     * Método que verifica se o usuário já assistiu uma mídia
     * 
     * @param midia
     * @return
     */
    private boolean JaAssistiu(Midia midia) {
        ArrayList<Midia> midiasAssistidas = clienteAtual.MidiasAssistidas();
        for (Midia midiaVerificar : midiasAssistidas) {
            if (midia.Id == midiaVerificar.Id)
                return true;
        }
        return false;
    }

    /**
     * Método que verifica se o usuário já avaliou uma mídia
     * 
     * @param midia
     * @return
     */
    private boolean JaAvaliou(Midia midia) {
        ArrayList<Avaliacao> avaliacoes = clienteAtual.MidiasAvaliadas();

        for (Avaliacao avaliacao : avaliacoes) {
            if (avaliacao.midiaId == midia.Id) {
                return true;
            }
        }
        return false;
    }

    /**
     * Método que verifica se o usuário ja comentou uma mídia
     * 
     * @param midia
     * @return
     */
    private boolean JaComentou(Midia midia) {
        ArrayList<Comentario> comentarios = clienteAtual.MidiasComentadas();

        for (Comentario comentario : comentarios) {
            if (comentario.midiaId == midia.Id) {
                return true;
            }
        }
        return false;
    }

    /**
     * Método para carregar todos os dados do serviço de streaming
     * 
     * @param pathClientesData
     * @param pathSeriesData
     * @param pathFilmesData
     * @param pathAudienciaData
     * @throws FileNotFoundException
     */
    private void CarregarDados(String pathClientesData, String pathSeriesData, String pathFilmesData,
            String pathAudienciaData)
            throws FileNotFoundException {
        ArrayList<Midia> series = reader.ReadSeries(pathSeriesData);
        ArrayList<Midia> filmes = reader.ReadFilmes(pathFilmesData);
        this.clientes = reader.ReadClients(pathClientesData);
        ArrayList<Audiencia> audiencia = reader.ReadAudiencia(pathAudienciaData);

        for (Midia serie : series)
            AdicionarMidia(serie);
        for (Midia filme : filmes)
            AdicionarMidia(filme);
        PreencherAudiencia(audiencia);

    }

    // #endregion Private
    // #region Relatório
    /**
     * Método que retorna o Cliente com mais mídias assistidas
     * 
     * @return Cliente
     */
    private Cliente ClienteComMaisMidiasAssistidas() {
        Cliente response = null;
        for (Cliente cliente : clientes) {
            int maiorQuantidadeDeMidias = 0;
            if (cliente.MidiasAssistidas().size() > maiorQuantidadeDeMidias) {
                response = cliente;
            }
        }
        return response;
    }

    /**
     * Método que retorna o Cliente com mais avaliações
     * 
     * @return Cliente
     */
    private Cliente ClienteComMaisAvaliacoes() {
        Cliente response = null;
        for (Cliente cliente : clientes) {
            int maiorQuantidadeDeAvaliacoes = 0;
            if (cliente.MidiasAvaliadas().size() > maiorQuantidadeDeAvaliacoes) {
                response = cliente;
            }
        }
        return response;
    }

    /**
     * Método que retorna a porcentagem de clientes com menos de quinze avaliações
     * 
     * @return double
     */
    private double PorcentagemDeClientesComMenosQuinzeAvaliacoes() {
        int total = clientes.size();
        int quantidadeDeClientes = 0;
        for (Cliente cliente : clientes) {
            int quantidadeDeMidiasAvaliadas = cliente.MidiasAvaliadas().size();
            if (quantidadeDeMidiasAvaliadas < 15) {
                quantidadeDeClientes++;
            }
        }

        double response = quantidadeDeClientes * 100 / total;
        return response;
    }

    /**
     * Método que retorna as top 10 mídias de forma decrescente baseado na avaliação
     * média de cada mídia
     * 
     * @return ArrayList<Midia>
     */
    private ArrayList<Midia> TopDezMidiasBaseadoEmAvaliacao() {
        ArrayList<Midia> response = new ArrayList<>();
        ArrayList<Midia> midias = MidiasComMaisDeCemVisualizacoes(catalogo);
        Collections.sort(midias, new Comparator<Midia>() {
            @Override
            public int compare(Midia midia1, Midia midia2) {
                return Double.compare(midia2.MediaDeAvaliacoes(), midia1.MediaDeAvaliacoes());
            }
        });
        var top10Decresc = midias.subList(0, Math.min(midias.size(), 10));
        response = new ArrayList<>(top10Decresc);
        return response;
    }

    /**
     * Método que separa as top 10 mídias com mais visualizações em um Array de
     * mídias fornecido
     * 
     * @param arrayDeMidia Array de mídia fornecido para a busca das top 10 mídias
     *                     com mais visualizações e ordena de forma decrescente
     * @return ArrayList<Midia>
     */
    private ArrayList<Midia> TopDezMidiasComMaisVisualizacoes(ArrayList<Midia> arrayDeMidia) {
        ArrayList<Midia> response = new ArrayList<>();
        ArrayList<Midia> midiaClone = arrayDeMidia;
        Collections.sort(midiaClone, new Comparator<Midia>() {
            @Override
            public int compare(Midia midia1, Midia midia2) {
                return Integer.compare(midia2.Audiencia().size(), midia1.Audiencia().size());
            }
        });
        var top10Decresc = midiaClone.subList(0, Math.min(midiaClone.size(), 10));
        response = new ArrayList<>(top10Decresc);
        return response;
    }

    /**
     * Método que busca a partir de uma lista todas as mídias com mais de cem
     * visualizações
     * 
     * @param arrayDeMidia Array de mídias a serem buscadas
     * @return ArrayList<Midia>
     */
    private ArrayList<Midia> MidiasComMaisDeCemVisualizacoes(ArrayList<Midia> arrayDeMidia) {
        ArrayList<Midia> AcimaDeCemVisualizacoes = new ArrayList<Midia>();
        for (Midia midia : arrayDeMidia) {
            var audienciaMidia = midia.Audiencia();
            if (audienciaMidia.size() > 100) {
                int quantidadeDeVisualizacoes = 0;
                for (Audiencia audiencia : audienciaMidia) {
                    if (audiencia.tipo == 'A') {
                        quantidadeDeVisualizacoes++;
                    }
                }
                if (quantidadeDeVisualizacoes > 100) {
                    AcimaDeCemVisualizacoes.add(midia);
                }
            }
        }
        return AcimaDeCemVisualizacoes;
    }

    /**
     * Método que busca e ordena de forma decrescente as top 10 mídias com mais de
     * cem visualizações e o gênero escolhido
     * 
     * @param generoBusca Paramêtro para buscar o gênero desejado
     * @return ArrayList<Midia>
     */
    private ArrayList<Midia> Top10MidiasPorGeneroEmVisualizacoes(String generoBusca) {
        var midias = catalogo;
        ArrayList<Midia> midiasResponse = new ArrayList<Midia>();
        for (Midia midia : midias) {
            String genero1 = midia.generos[0];
            String genero2 = midia.generos[1];
            if (genero1.equals(generoBusca) || genero2.equals(generoBusca)) {
                midiasResponse.add(midia);
            }
        }
        Collections.sort(midiasResponse, new Comparator<Midia>() {
            @Override
            public int compare(Midia midia1, Midia midia2) {
                return Integer.compare(midia2.Audiencia().size(), midia1.Audiencia().size());
            }
        });
        var top10Decresc = midiasResponse.subList(0, Math.min(midiasResponse.size(), 10));
        ArrayList<Midia> response = new ArrayList<>(top10Decresc);
        return response;
    }

    /**
     * Método que busca ordena de forma decrescente as top 10 mídias baseadas em sua
     * avaliação média e seu gênero
     * 
     * @param generoBusca Paramêtro para buscar o gênero desejado
     * @return ArrayList<Midia>
     */
    private ArrayList<Midia> Top10MidiasPorGeneroEmAvaliacoes(String generoBusca) {
        var midias = catalogo;
        ArrayList<Midia> midiasResponse = new ArrayList<Midia>();
        for (Midia midia : midias) {
            String genero1 = midia.generos[0];
            String genero2 = midia.generos[1];
            if (genero1.equals(generoBusca) || genero2.equals(generoBusca)) {
                midiasResponse.add(midia);
            }
        }
        Collections.sort(midias, new Comparator<Midia>() {
            @Override
            public int compare(Midia midia1, Midia midia2) {
                return Double.compare(midia2.MediaDeAvaliacoes(), midia1.MediaDeAvaliacoes());
            }
        });
        var top10Decresc = midias.subList(0, Math.min(midias.size(), 10));
        ArrayList<Midia> response = new ArrayList<>(top10Decresc);
        return response;
    }
    // #endregion Relatório
}
