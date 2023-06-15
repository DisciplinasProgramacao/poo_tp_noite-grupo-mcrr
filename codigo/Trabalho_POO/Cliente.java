package Trabalho_POO;

import java.util.ArrayList;
import java.util.Date;

import Trabalho_POO.models.Avaliacao;
import Trabalho_POO.models.Comentario;
import Trabalho_POO.models.Midia;

public class Cliente {
    // #region Properties
    private static final int QUANTIDADE_DE_MEDIA_PROFSSIONAL = 5;
    private int quantidadeDeMediasAssitidas;
    private boolean especialista;
    private ArrayList<Midia> midiasAssistidas;
    private ArrayList<Midia> midiasFuturas;
    private ArrayList<Avaliacao> midiasAvaliadas;
    private ArrayList<Comentario> midiasComentadas;

    private String nome;
    private String login;
    private String senha;

    // #endregion Properties
    // #region Public
    /**
     * Construtor da classe
     * 
     * @param nome
     * @param login
     * @param senha
     */
    public Cliente(String nome, String login, String senha, boolean especialista) {
        this.login = login;
        this.nome = nome;
        this.senha = senha;

        quantidadeDeMediasAssitidas = 0;
        this.especialista = especialista;
        midiasAssistidas = new ArrayList<>();
        midiasFuturas = new ArrayList<>();
        this.midiasAvaliadas = new ArrayList<Avaliacao>();
        this.midiasComentadas = new ArrayList<Comentario>();
    }

    /**
     * Método para autenticar o usuário sem expor sua senha
     * 
     * @param senha
     * @return boolean
     */
    public boolean VerificarUsuario(String senha) {
        if (this.senha.equals(senha))
            return true;
        return false;
    }

    /**
     * Método para marcar uma midia como assistida
     * 
     * @param midia
     */
    public void AdicionarMidiaAssistida(Midia midia) {
        for (Midia midiaVerificar : midiasAssistidas) {
            if (midiaVerificar.Id == midia.Id) {
                System.out.println("Só é possível marcar como assistida uma vez");
                System.out.println();
                return;
            }
        }
        this.midiasAssistidas.add(midia);
        quantidadeDeMediasAssitidas++;
        String mensagem = midia.titulo + " marcado como assistido com sucesso!";
        System.out.println();
        System.out.println(mensagem);
        if (quantidadeDeMediasAssitidas >= QUANTIDADE_DE_MEDIA_PROFSSIONAL) {
            especialista = true;
        }
    }

    /**
     * Método para verificar se o usuário é um especialista ou não
     * 
     * @return boolean
     */
    public boolean EhEspecialista() {
        return especialista;
    }

    /**
     * Método para marcar uma mídia para assistir futuramente
     * 
     * @param midia
     */
    public void AdicionarMidiaFutura(Midia midia) {
        for (Midia midiaVerificar : midiasFuturas) {
            if (midiaVerificar.Id == midia.Id) {
                System.out.println("Só é possível marcar para assistir futuramente uma vez");
                return;
            }
        }
        this.midiasFuturas.add(midia);
        String mensagem = midia.titulo + " marcado para assistir futuramente com sucesso!";
        System.out.println();
        System.out.println(mensagem);
    }

    /**
     * Método para remover a marcação de assistir futuramente
     * 
     * @param midia
     */
    public void RemoverMidiaFutura(Midia midia) {
        for (Midia midiaVerificar : midiasFuturas) {
            if (midiaVerificar.Id == midia.Id) {
                System.out.println(midia.titulo + " desmarcado para assistir futuramente");
                System.out.println();
                midiasFuturas.remove(midia);
                return;
            }
        }
        String mensagem = midia.titulo + " não está marcado para assistir futuramente";
        System.out.println();
        System.out.println(mensagem);
    }

    /**
     * Método Getter que retorna as mídias assistidas
     * 
     * @return ArrayList<Midia>
     */
    public ArrayList<Midia> MidiasAssistidas() {
        return midiasAssistidas;
    }

    /**
     * Método Getter que retorna as midias marcadas para assistir futuramente
     * 
     * @return ArrayList<Midia>
     */
    public ArrayList<Midia> MidiasFuturas() {
        return midiasFuturas;
    }

    /**
     * Método para salvar as midias comentadas do usuário e seus comentários
     * 
     * @param comentario
     * @param midiaId
     */
    public void AdicionarMidiaComentada(String comentario, int midiaId) {
        this.midiasComentadas.add(new Comentario(comentario, login, midiaId));
    }

    /**
     * Método para salvar as midias avaliadas do usuário e suas avaliações
     * 
     * @param avaliacao
     * @param midiaId
     */
    public void AdicionarMidiaAvaliada(int avaliacao, int midiaId) {
        this.midiasAvaliadas.add(new Avaliacao(login, avaliacao, new Date(), midiaId));
    }

    /**
     * Método Getter que retorna os comentários realizados pelo usuário
     * 
     * @return ArrayList<Comentario>
     */
    public ArrayList<Comentario> MidiasComentadas() {
        return midiasComentadas;
    }

    /**
     * Método Getter que retorna as avaliações realizadas pelo usuario
     * 
     * @return ArrayList<Avaliacao>
     */
    public ArrayList<Avaliacao> MidiasAvaliadas() {
        return midiasAvaliadas;
    }

    /**
     * Método Getter do nome do usuário
     * 
     * @return String
     */
    public String Nome() {
        return nome;
    }

    /**
     * Método Getter do Login do usuário
     * 
     * @return String
     */
    public String Login() {
        return login;
    }

    public int GetQuantidadeDeMidiasAssistidas() {
        return quantidadeDeMediasAssitidas;
    }

    public int GetQuantidadeDeMidiasFuturamente() {
        return midiasFuturas.size();
    }

    /**
     * Método Setter para alterar o login do usuário
     * 
     * @param login
     */
    public void AlterarLogin(String login) {
        this.login = login;
        System.out.println("Login alterado com sucesso!");
    }

    /**
     * Método Setter para alterar a senha do usuário
     * 
     * @param senha
     */
    public void AlterarSenha(String senha) {
        this.senha = senha;
        System.out.println("Senha alterada com sucesso!");
    }

    /**
     * Método Setter para alterar o nome do usuário
     * 
     * @param nome
     */
    public void AlterarNome(String nome) {
        this.nome = nome;
        System.out.println("Nome alterado com sucesso!");
    }
    // #endregion Public
}
