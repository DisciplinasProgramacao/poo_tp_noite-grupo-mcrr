package Trabalho_POO.models;

import java.util.Date;

public class Avaliacao {
    // #region Properties
    public final String userLogin;
    public final int avaliacao;
    public final Date dataDeAvaliacao;
    public final int midiaId;

    // #endregion Properties
    /**
     * Construtor da classe
     * 
     * @param userlogin
     * @param avaliacao
     * @param dataDeAvaliacao
     */
    public Avaliacao(String userlogin, int avaliacao, Date dataDeAvaliacao, int midiaId) {
        this.userLogin = userlogin;
        this.avaliacao = avaliacao;
        this.dataDeAvaliacao = dataDeAvaliacao;
        this.midiaId = midiaId;
    }

}
