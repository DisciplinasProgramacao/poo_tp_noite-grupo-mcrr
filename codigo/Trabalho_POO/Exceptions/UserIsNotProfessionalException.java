package Trabalho_POO.Exceptions;

public class UserIsNotProfessionalException extends Exception {
    public UserIsNotProfessionalException() {
        super("Usuário não é um profissional");
    }

    public UserIsNotProfessionalException(String message) {
        super(message);
    }
}
