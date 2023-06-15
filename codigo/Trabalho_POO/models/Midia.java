package Trabalho_POO.models;

import java.util.ArrayList;
import java.util.Date;

import Trabalho_POO.Audiencia;

public abstract class Midia {
    // #region Properties
    public final String titulo;
    public final int Id;
    public final String[] idiomas;
    public final String[] generos;
    private ArrayList<Comentario> comentarios;
    public final Date dataDeLancamento;
    private ArrayList<Avaliacao> avaliacoes;
    private boolean lancamento;
    private ArrayList<Audiencia> Audiencia;

    // #endregion Properties
    // #region Public
    /**
     * Construtor da classe
     * 
     * @param id
     * @param titulo
     * @param dataDeLancamento
     * @param idiomas
     * @param generos
     * @param lancamento
     */
    public Midia(int id, String titulo, Date dataDeLancamento, String[] idiomas, String[] generos, boolean lancamento) {
        this.titulo = titulo;
        this.idiomas = idiomas;
        this.generos = generos;
        this.Id = id;
        this.dataDeLancamento = dataDeLancamento;
        this.comentarios = new ArrayList<Comentario>();
        this.avaliacoes = new ArrayList<Avaliacao>();
        this.lancamento = lancamento;
        this.Audiencia = new ArrayList<Audiencia>();
    }

    /**
     * Método para comentar sobre as mídias
     * 
     * @param comentario
     * @param userLogin
     */
    public void Comentar(String comentario, String userLogin) {
        this.comentarios.add(new Comentario(comentario, userLogin, Id));
    }

    /**
     * Método para avaliar mídias
     * 
     * @param avaliacao
     * @param userLogin
     */
    public void Avaliar(int avaliacao, String userLogin) {
        avaliacoes.add(new Avaliacao(userLogin, avaliacao, new Date(), Id));
        System.out.println("Obrigado pela sua avaliação!");
    }

    /**
     * Método GET para ver os comentários das mídias
     * 
     * @return ArrayList<Comentario>
     */

    public ArrayList<Comentario> Comentarios() {
        return comentarios;
    }

    /**
     * Método GET para ver as avaliações das mídias
     * 
     * @return ArrayList<Avaliacao>
     */
    public ArrayList<Avaliacao> Avaliacoes() {
        return avaliacoes;
    }

    /**
     * Método para ver a média de avaliações das mídias utilizando média ponderada
     * 
     * @return double
     */
    public double MediaDeAvaliacoes() {

        if (avaliacoes != null) {
            int muitoRuim = 0;
            int ruim = 0;
            int medio = 0;
            int bom = 0;
            int muitoBom = 0;

            for (Avaliacao avaliacao : avaliacoes) {
                if (avaliacao.avaliacao == 1)
                    muitoRuim++;
                else if (avaliacao.avaliacao == 2)
                    muitoRuim++;
                else if (avaliacao.avaliacao == 3)
                    medio++;
                else if (avaliacao.avaliacao == 4)
                    bom++;
                else if (avaliacao.avaliacao == 5)
                    muitoBom++;
            }
            int somaDosPesos = 1 + 2 + 3 + 4 + 5;

            double media = (muitoRuim * 1) + (ruim * 2) + (medio * 3) + (bom * 4) + (muitoBom * 5) / (somaDosPesos);
            return media;
        }
        return 0;

    }

    /**
     * Método para adicionar audiencia e salvar quantas vezes foi salvo como
     * assistido ou marcado para ver futuramente
     * 
     * @param audiencia
     */
    public void AddAudiencia(Audiencia audiencia) {
        this.Audiencia.add(audiencia);
    }

    /**
     * Método para remover audiência específica
     * 
     * @param audiencia
     */
    public void RemoveAudiencia(Audiencia audiencia) {
        this.Audiencia.remove(audiencia);
    }

    /**
     * Método para ver toda a audiência de uma mídia
     * 
     * @return ArrayList<Audiencia>
     */
    public ArrayList<Audiencia> Audiencia() {
        return Audiencia;
    }

    /**
     * Método para ver se a mídia é um lançamento ou não
     * 
     * @return boolean
     */
    public boolean EhLancamento() {
        return lancamento;
    }
    // #endregion Public
}
