package Trabalho_POO;

import java.util.Date;

import Trabalho_POO.models.Midia;

public class Serie extends Midia {
    /**
     * Método construtor de classe
     * 
     * @param id
     * @param titulo
     * @param dataDeLancamento
     * @param idiomas
     * @param generos
     * @param lancamento
     */
    public Serie(int id, String titulo, Date dataDeLancamento, String[] idiomas, String[] generos, boolean lancamento) {
        super(id, titulo, dataDeLancamento, idiomas, generos, lancamento);
    }
}
