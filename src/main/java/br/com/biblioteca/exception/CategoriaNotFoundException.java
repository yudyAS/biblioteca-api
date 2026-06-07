package br.com.biblioteca.exception;

public class CategoriaNotFoundException extends RuntimeException {
    public CategoriaNotFoundException(Long id) {
        super("Categoria não encontrada com id: " + id);
    }

    public CategoriaNotFoundException(String message) {
        super(message);
    }
}

