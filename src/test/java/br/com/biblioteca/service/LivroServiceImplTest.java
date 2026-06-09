package br.com.biblioteca.service;

import br.com.biblioteca.dto.CriarLivroDTO;
import br.com.biblioteca.dto.RespostaLivroDTO;
import br.com.biblioteca.exception.DuplicateIsbnException;
import br.com.biblioteca.exception.LivroNotFoundException;
import br.com.biblioteca.mapper.LivroMapper;
import br.com.biblioteca.model.Livro;
import br.com.biblioteca.repository.LivroRepository;
import br.com.biblioteca.service.impl.LivroServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class LivroServiceImplTest {

    @Mock
    private LivroRepository livroRepository;

    @Mock
    private CategoriaService categoriaService;

    @Mock
    private LivroMapper livroMapper;

    @InjectMocks
    private LivroServiceImpl livroService;

    @Test
    void deveCriarLivroComSucesso() {

        CriarLivroDTO dto = CriarLivroDTO.builder()
                .titulo("Clean Code")
                .autor("Robert Martin")
                .isbn("1234567890")
                .anoPublicacao(2008)
                .build();

        Livro livro = new Livro();

        RespostaLivroDTO resposta = RespostaLivroDTO.builder()
                .titulo("Clean Code")
                .build();

        when(livroRepository.findByIsbn(dto.getIsbn()))
                .thenReturn(null);

        when(livroMapper.toEntity(dto, null))
                .thenReturn(livro);

        when(livroRepository.save(livro))
                .thenReturn(livro);

        when(livroMapper.toRespostaDTO(livro))
                .thenReturn(resposta);

        RespostaLivroDTO resultado = livroService.criarLivro(dto);

        assertNotNull(resultado);

        verify(livroRepository).save(livro);
    }

    @Test
    void deveLancarErroQuandoIsbnDuplicado() {

        CriarLivroDTO dto = CriarLivroDTO.builder()
                .isbn("1234567890")
                .build();

        when(livroRepository.findByIsbn(dto.getIsbn()))
                .thenReturn(new Livro());

        assertThrows(
                DuplicateIsbnException.class,
                () -> livroService.criarLivro(dto)
        );
    }

    @Test
    void deveBuscarLivroPorId() {

        Livro livro = new Livro();
        livro.setId(1L);

        RespostaLivroDTO dto = RespostaLivroDTO.builder()
                .id(1L)
                .build();

        when(livroRepository.findById(1L))
                .thenReturn(Optional.of(livro));

        when(livroMapper.toRespostaDTO(livro))
                .thenReturn(dto);

        RespostaLivroDTO resultado =
                livroService.buscarLivroPorId(1L);

        assertEquals(1L, resultado.getId());
    }

    @Test
    void deveLancarErroQuandoLivroNaoExiste() {

        when(livroRepository.findById(99L))
                .thenReturn(Optional.empty());

        assertThrows(
                LivroNotFoundException.class,
                () -> livroService.buscarLivroPorId(99L)
        );
    }

    @Test
    void deveLancarErroAoDeletarLivroInexistente() {

        when(livroRepository.existsById(99L))
                .thenReturn(false);

        assertThrows(
                LivroNotFoundException.class,
                () -> livroService.deletarLivro(99L)
        );
    }
}
