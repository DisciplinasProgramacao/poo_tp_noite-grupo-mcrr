package Trabalho_POO.Exceptions;

public class MediaAlreadyEvaluatedException extends Exception {
    public MediaAlreadyEvaluatedException() {
        super("Mídia já avaliada.");
    }

    public MediaAlreadyEvaluatedException(String message) {
        super(message);
    }
}
