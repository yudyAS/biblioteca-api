package br.com.biblioteca.service;

import br.com.biblioteca.model.Categoria;

import java.util.Optional;

public interface CategoriaService {
    Optional<Categoria> findById(Long id);
    Optional<Categoria> findByNomeIgnoreCase(String nome);
}

