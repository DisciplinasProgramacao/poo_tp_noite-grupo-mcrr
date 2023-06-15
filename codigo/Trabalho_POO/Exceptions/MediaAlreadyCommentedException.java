package Trabalho_POO.Exceptions;

public class MediaAlreadyCommentedException extends Exception {
    public MediaAlreadyCommentedException() {
        super("Mídia já comentada.");
    }

    public MediaAlreadyCommentedException(String message) {
        super(message);
    }
}
