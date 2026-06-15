package br.com.biblioteca.service;

import br.com.biblioteca.dto.AtualizarLivroDTO;
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

    @Test
    void deveListarTodosLivros() {

        Livro livro = new Livro();
        livro.setId(1L);

        RespostaLivroDTO dto = RespostaLivroDTO.builder()
                .id(1L)
                .build();

        when(livroRepository.findAll())
                .thenReturn(java.util.List.of(livro));

        when(livroMapper.toRespostaDTO(livro))
                .thenReturn(dto);

        var resultado = livroService.listarTodosLivros();

        assertEquals(1, resultado.size());
    }

    @Test
    void deveBuscarPorAutor() {

        Livro livro = new Livro();
        livro.setAutor("Robert Martin");

        RespostaLivroDTO dto = RespostaLivroDTO.builder()
                .autor("Robert Martin")
                .build();

        when(livroRepository.findByAutorIgnoreCase("Robert Martin"))
                .thenReturn(java.util.List.of(livro));

        when(livroMapper.toRespostaDTO(livro))
                .thenReturn(dto);

        var resultado = livroService.buscarPorAutor("Robert Martin");

        assertEquals(1, resultado.size());
    }

    @Test
    void deveBuscarPorTitulo() {

        Livro livro = new Livro();
        livro.setTitulo("Clean Code");

        RespostaLivroDTO dto = RespostaLivroDTO.builder()
                .titulo("Clean Code")
                .build();

        when(livroRepository.findByTituloContainingIgnoreCase("Clean"))
                .thenReturn(java.util.List.of(livro));

        when(livroMapper.toRespostaDTO(livro))
                .thenReturn(dto);

        var resultado = livroService.buscarPorTitulo("Clean");

        assertEquals(1, resultado.size());
    }

    @Test
    void deveBuscarLivrosDisponiveis() {

        Livro livro = new Livro();
        livro.setDisponivel(true);

        RespostaLivroDTO dto = RespostaLivroDTO.builder()
                .disponivel(true)
                .build();

        when(livroRepository.findByDisponivel(true))
                .thenReturn(java.util.List.of(livro));

        when(livroMapper.toRespostaDTO(livro))
                .thenReturn(dto);

        var resultado = livroService.buscarDisponiveis();

        assertEquals(1, resultado.size());
    }

    @Test
    void deveDeletarLivroComSucesso() {

        when(livroRepository.existsById(1L))
                .thenReturn(true);

        livroService.deletarLivro(1L);

        verify(livroRepository).deleteById(1L);
    }

    @Test
    void deveAtualizarLivroComSucesso() {

        Livro livro = new Livro();
        livro.setId(1L);
        livro.setIsbn("123");

        AtualizarLivroDTO dto = AtualizarLivroDTO.builder()
                .titulo("Novo Titulo")
                .autor("Novo Autor")
                .anoPublicacao(2024)
                .build();

        RespostaLivroDTO resposta = RespostaLivroDTO.builder()
                .id(1L)
                .titulo("Novo Titulo")
                .build();

        when(livroRepository.findById(1L))
                .thenReturn(Optional.of(livro));

        when(livroRepository.save(livro))
                .thenReturn(livro);

        when(livroMapper.toRespostaDTO(livro))
                .thenReturn(resposta);

        RespostaLivroDTO resultado =
                livroService.atualizarLivro(1L, dto);

        assertNotNull(resultado);

        verify(livroRepository).save(livro);
    }

    @Test
    void deveLancarErroAoAtualizarComIsbnDuplicado() {

        Livro livro = new Livro();
        livro.setId(1L);
        livro.setIsbn("123");

        AtualizarLivroDTO dto = AtualizarLivroDTO.builder()
                .isbn("999")
                .build();

        when(livroRepository.findById(1L))
                .thenReturn(Optional.of(livro));

        when(livroRepository.findByIsbn("999"))
                .thenReturn(new Livro());

        assertThrows(
                DuplicateIsbnException.class,
                () -> livroService.atualizarLivro(1L, dto)
        );
    }

    @Test
    void deveLancarErroAoAtualizarLivroInexistente() {

        AtualizarLivroDTO dto = AtualizarLivroDTO.builder()
                .titulo("Teste")
                .build();

        when(livroRepository.findById(99L))
                .thenReturn(Optional.empty());

        assertThrows(
                LivroNotFoundException.class,
                () -> livroService.atualizarLivro(99L, dto)
        );
    }
}