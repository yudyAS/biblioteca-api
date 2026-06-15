package br.com.biblioteca.service.impl;

import br.com.biblioteca.dto.CategoriaDTO;
import br.com.biblioteca.exception.CategoriaNotFoundException;
import br.com.biblioteca.model.Categoria;
import br.com.biblioteca.repository.CategoriaRepository;
import br.com.biblioteca.service.CategoriaService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CategoriaServiceImpl implements CategoriaService {

    private final CategoriaRepository categoriaRepository;

    public CategoriaServiceImpl(CategoriaRepository categoriaRepository) {
        this.categoriaRepository = categoriaRepository;
    }

    @Override
    public CategoriaDTO criarCategoria(CategoriaDTO dto) {
        Categoria categoria = Categoria.builder()
                .nome(dto.getNome())
                .build();

        return toDTO(categoriaRepository.save(categoria));
    }

    @Override
    public List<CategoriaDTO> listarTodasCategorias() {
        return categoriaRepository.findAll().stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public CategoriaDTO buscarCategoriaPorId(Long id) {
        return categoriaRepository.findById(id)
                .map(this::toDTO)
                .orElseThrow(() -> new CategoriaNotFoundException(id));
    }

    @Override
    public CategoriaDTO buscarCategoriaPorNome(String nome) {
        Categoria categoria = categoriaRepository.findByNomeIgnoreCase(nome);
        if (categoria == null) {
            throw new CategoriaNotFoundException("Nome: " + nome);
        }
        return toDTO(categoria);
    }

    @Override
    public CategoriaDTO atualizarCategoria(Long id, CategoriaDTO dto) {
        Categoria categoria = categoriaRepository.findById(id)
                .orElseThrow(() -> new CategoriaNotFoundException(id));

        categoria.setNome(dto.getNome());
        return toDTO(categoriaRepository.save(categoria));
    }

    @Override
    public void deletarCategoria(Long id) {
        if (!categoriaRepository.existsById(id)) {
            throw new CategoriaNotFoundException(id);
        }
        categoriaRepository.deleteById(id);
    }

    @Override
    public Optional<Categoria> findById(Long id) {
        return categoriaRepository.findById(id);
    }

    @Override
    public Optional<Categoria> findByNomeIgnoreCase(String nome) {
        return Optional.ofNullable(categoriaRepository.findByNomeIgnoreCase(nome));
    }

    private CategoriaDTO toDTO(Categoria categoria) {
        return CategoriaDTO.builder()
                .id(categoria.getId())
                .nome(categoria.getNome())
                .build();
    }
}

