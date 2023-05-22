package trabalho_POO;

public class Auditoria {
    private String login;
    private char tipo;
    private int idSerie;

    public Auditoria(String login, char tipo, int idSerie) {
        this.login = login;
        this.tipo = tipo;
        this.idSerie = idSerie;
    }

    public String getLogin() {
        return login;
    }

    public char getTipo() {
        return tipo;
    }

    public int getIdSerie() {
        return idSerie;
    }
}

