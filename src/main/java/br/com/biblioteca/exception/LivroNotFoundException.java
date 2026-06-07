package br.com.biblioteca.exception;

public class LivroNotFoundException extends RuntimeException {
    public LivroNotFoundException(Long id) {
        super("Livro não encontrado com id: " + id);
    }

    public LivroNotFoundException(String message) {
        super(message);
    }
}

