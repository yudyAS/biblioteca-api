package br.com.biblioteca.service;

import br.com.biblioteca.dto.CategoriaDTO;
import br.com.biblioteca.model.Categoria;

import java.util.List;
import java.util.Optional;

public interface CategoriaService {
    CategoriaDTO criarCategoria(CategoriaDTO dto);
    List<CategoriaDTO> listarTodasCategorias();
    CategoriaDTO buscarCategoriaPorId(Long id);
    CategoriaDTO buscarCategoriaPorNome(String nome);
    CategoriaDTO atualizarCategoria(Long id, CategoriaDTO dto);
    void deletarCategoria(Long id);

    Optional<Categoria> findById(Long id);
    Optional<Categoria> findByNomeIgnoreCase(String nome);
}

