package Trabalho_POO.Exceptions;

public class MediaNotFoundException extends Exception {
    public MediaNotFoundException() {
        super("Nenhuma mídia encontrada.");
    }

    public MediaNotFoundException(String message) {
        super(message);
    }
}
