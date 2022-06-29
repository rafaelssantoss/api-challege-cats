package br.com.project.animals.exception;

public class CatException extends RuntimeException {

    public CatException(String message) {
        super(message);
    }

    public static class NotFound extends CatException {
        public NotFound(String message) {
            super(message);
        }
    }

    public static class BadRequest extends CatException {
        public BadRequest(String message) {
            super(message);
        }
    }
}