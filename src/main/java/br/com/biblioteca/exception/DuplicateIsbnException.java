package br.com.biblioteca.exception;

public class DuplicateIsbnException extends RuntimeException {
    public DuplicateIsbnException(String isbn) {
        super("ISBN já cadastrado: " + isbn);
    }

    public DuplicateIsbnException(String message, Throwable cause) {
        super(message, cause);
    }
}

