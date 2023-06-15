package Trabalho_POO;

import java.util.Date;

import Trabalho_POO.models.Midia;

public class Filme extends Midia {
    // #region Properties
    public final int duracao;

    // #endregion Properties
    /**
     * Construtor da classe
     * 
     * @param id
     * @param titulo
     * @param dataDeLancamento
     * @param idiomas
     * @param generos
     * @param duracao
     * @param lancamento
     */
    public Filme(int id, String titulo, Date dataDeLancamento, String[] idiomas, String[] generos, int duracao,
            boolean lancamento) {
        super(id, titulo, dataDeLancamento, idiomas, generos, lancamento);
        this.duracao = duracao;
    }
}
