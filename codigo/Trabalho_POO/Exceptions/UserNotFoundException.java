package Trabalho_POO.Exceptions;

public class UserNotFoundException extends Exception {
    public UserNotFoundException() {
        super("Usuário não encontrado.");
    }

    public UserNotFoundException(String message) {
        super(message);
    }
}
