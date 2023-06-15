package Trabalho_POO.models;

public class Comentario {
    // #region Properties
    public final String comentario;
    public final String userLogin;
    public final int midiaId;

    // #endregion Properties
    /**
     * Método construtor de classe
     * 
     * @param comentario
     * @param userId
     * @param midiaId
     */
    public Comentario(String comentario, String userId, int midiaId) {
        this.comentario = comentario;
        this.userLogin = userId;
        this.midiaId = midiaId;
    }
}
