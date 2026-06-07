package br.com.biblioteca.service.impl;

import br.com.biblioteca.model.Categoria;
import br.com.biblioteca.repository.CategoriaRepository;
import br.com.biblioteca.service.CategoriaService;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CategoriaServiceImpl implements CategoriaService {

    private final CategoriaRepository categoriaRepository;

    public CategoriaServiceImpl(CategoriaRepository categoriaRepository) {
        this.categoriaRepository = categoriaRepository;
    }

    @Override
    public Optional<Categoria> findById(Long id) {
        return categoriaRepository.findById(id);
    }

    @Override
    public Optional<Categoria> findByNomeIgnoreCase(String nome) {
        return Optional.ofNullable(categoriaRepository.findByNomeIgnoreCase(nome));
    }
}

