package Trabalho_POO.Exceptions;

public class MediaNotWatchedException extends Exception {
    public MediaNotWatchedException() {
        super("Mídia não assistida");
    }

    public MediaNotWatchedException(String message) {
        super(message);
    }
}
