package br.com.biblioteca.service.impl;

import br.com.biblioteca.dto.AtualizarLivroDTO;
import br.com.biblioteca.dto.CriarLivroDTO;
import br.com.biblioteca.dto.RespostaLivroDTO;
import br.com.biblioteca.exception.CategoriaNotFoundException;
import br.com.biblioteca.exception.DuplicateIsbnException;
import br.com.biblioteca.exception.LivroNotFoundException;
import br.com.biblioteca.mapper.LivroMapper;
import br.com.biblioteca.model.Categoria;
import br.com.biblioteca.model.Livro;
import br.com.biblioteca.repository.LivroRepository;
import br.com.biblioteca.service.CategoriaService;
import br.com.biblioteca.service.LivroService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
public class LivroServiceImpl implements LivroService {

    private final LivroRepository livroRepository;
    private final CategoriaService categoriaService;
    private final LivroMapper livroMapper;

    public LivroServiceImpl(LivroRepository livroRepository, CategoriaService categoriaService, LivroMapper livroMapper) {
        this.livroRepository = livroRepository;
        this.categoriaService = categoriaService;
        this.livroMapper = livroMapper;
    }

    @Override
    @Transactional
    public RespostaLivroDTO criarLivro(CriarLivroDTO dto) {
        if (livroRepository.findByIsbn(dto.getIsbn()) != null) {
            throw new DuplicateIsbnException(dto.getIsbn());
        }
        Categoria categoria = null;
        if (dto.getCategoriaId() != null) {
            categoria = categoriaService.findById(dto.getCategoriaId())
                    .orElseThrow(() -> new CategoriaNotFoundException(dto.getCategoriaId()));
        }
        Livro livro = livroMapper.toEntity(dto, categoria);
        Livro salvo = livroRepository.save(livro);
        return livroMapper.toRespostaDTO(salvo);
    }

    @Override
    @Transactional(readOnly = true)
    public List<RespostaLivroDTO> listarTodosLivros() {
        return livroRepository.findAll().stream()
                .map(livroMapper::toRespostaDTO)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public RespostaLivroDTO buscarLivroPorId(Long id) {
        Livro livro = livroRepository.findById(id)
                .orElseThrow(() -> new LivroNotFoundException(id));
        return livroMapper.toRespostaDTO(livro);
    }

    @Override
    @Transactional
    public RespostaLivroDTO atualizarLivro(Long id, AtualizarLivroDTO dto) {
        Livro livro = livroRepository.findById(id)
                .orElseThrow(() -> new LivroNotFoundException(id));

        if (dto.getIsbn() != null && !Objects.equals(dto.getIsbn(), livro.getIsbn())) {
            if (livroRepository.findByIsbn(dto.getIsbn()) != null) {
                throw new DuplicateIsbnException(dto.getIsbn());
            }
        }

        Categoria categoria = null;
        if (dto.getCategoriaId() != null) {
            categoria = categoriaService.findById(dto.getCategoriaId())
                    .orElseThrow(() -> new CategoriaNotFoundException(dto.getCategoriaId()));
        }

        livroMapper.updateEntityFromDto(dto, livro, categoria);
        Livro salvo = livroRepository.save(livro);
        return livroMapper.toRespostaDTO(salvo);
    }

    @Override
    @Transactional
    public void deletarLivro(Long id) {
        if (!livroRepository.existsById(id)) {
            throw new LivroNotFoundException(id);
        }
        livroRepository.deleteById(id);
    }

    @Override
    @Transactional(readOnly = true)
    public List<RespostaLivroDTO> buscarPorAutor(String autor) {
        return livroRepository.findByAutorIgnoreCase(autor).stream()
                .map(livroMapper::toRespostaDTO)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public List<RespostaLivroDTO> buscarPorTitulo(String titulo) {
        return livroRepository.findByTituloContainingIgnoreCase(titulo).stream()
                .map(livroMapper::toRespostaDTO)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public List<RespostaLivroDTO> buscarDisponiveis() {
        return livroRepository.findByDisponivel(true).stream()
                .map(livroMapper::toRespostaDTO)
                .collect(Collectors.toList());
    }
}

