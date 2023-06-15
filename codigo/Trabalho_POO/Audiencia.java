package Trabalho_POO;

public class Audiencia {
    // #region Properties
    public final int IdSerie;
    public final String Login;
    public final char tipo;

    // #endregion Properties

    /**
     * Construtor da classe
     * 
     * @param login
     * @param idSerie
     * @param tipo
     */
    public Audiencia(String login, int idSerie, char tipo) {
        this.IdSerie = idSerie;
        this.Login = login;
        this.tipo = tipo;
    }

}
