package br.com.biblioteca.controller;

import br.com.biblioteca.dto.AtualizarLivroDTO;
import br.com.biblioteca.dto.CriarLivroDTO;
import br.com.biblioteca.dto.RespostaLivroDTO;
import br.com.biblioteca.service.LivroService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(LivroController.class)
class LivroControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private LivroService livroService;

    @Test
    void deveListarTodosLivros() throws Exception {

        RespostaLivroDTO livro = RespostaLivroDTO.builder()
                .id(1L)
                .titulo("Clean Code")
                .autor("Robert Martin")
                .isbn("1234567890")
                .build();

        when(livroService.listarTodosLivros())
                .thenReturn(List.of(livro));

        mockMvc.perform(get("/api/livros"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id").value(1))
                .andExpect(jsonPath("$[0].titulo").value("Clean Code"));
    }

    @Test
    void deveBuscarLivroPorId() throws Exception {

        RespostaLivroDTO livro = RespostaLivroDTO.builder()
                .id(1L)
                .titulo("Clean Code")
                .autor("Robert Martin")
                .build();

        when(livroService.buscarLivroPorId(1L))
                .thenReturn(livro);

        mockMvc.perform(get("/api/livros/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.titulo").value("Clean Code"));
    }

    @Test
    void deveCriarLivro() throws Exception {

        CriarLivroDTO dto = CriarLivroDTO.builder()
                .titulo("Clean Code")
                .autor("Robert Martin")
                .isbn("1234567890")
                .anoPublicacao(2008)
                .build();

        RespostaLivroDTO resposta = RespostaLivroDTO.builder()
                .id(1L)
                .titulo("Clean Code")
                .build();

        when(livroService.criarLivro(any(CriarLivroDTO.class)))
                .thenReturn(resposta);

        mockMvc.perform(post("/api/livros")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(dto)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").value(1));
    }

    @Test
    void deveAtualizarLivro() throws Exception {

        AtualizarLivroDTO dto = AtualizarLivroDTO.builder()
                .titulo("Clean Code Atualizado")
                .build();

        RespostaLivroDTO resposta = RespostaLivroDTO.builder()
                .id(1L)
                .titulo("Clean Code Atualizado")
                .build();

        when(livroService.atualizarLivro(any(Long.class), any(AtualizarLivroDTO.class)))
                .thenReturn(resposta);

        mockMvc.perform(put("/api/livros/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(dto)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.titulo")
                        .value("Clean Code Atualizado"));
    }

    @Test
    void deveDeletarLivro() throws Exception {

        mockMvc.perform(delete("/api/livros/1"))
                .andExpect(status().isNoContent());
    }

    @Test
    void deveBuscarPorAutor() throws Exception {

        RespostaLivroDTO livro = RespostaLivroDTO.builder()
                .autor("Robert Martin")
                .build();

        when(livroService.buscarPorAutor("Robert Martin"))
                .thenReturn(List.of(livro));

        mockMvc.perform(get("/api/livros/autor/Robert Martin"))
                .andExpect(status().isOk());
    }

    @Test
    void deveBuscarPorTitulo() throws Exception {

        RespostaLivroDTO livro = RespostaLivroDTO.builder()
                .titulo("Clean Code")
                .build();

        when(livroService.buscarPorTitulo("Clean"))
                .thenReturn(List.of(livro));

        mockMvc.perform(get("/api/livros/titulo/Clean"))
                .andExpect(status().isOk());
    }

    @Test
    void deveBuscarDisponiveis() throws Exception {

        RespostaLivroDTO livro = RespostaLivroDTO.builder()
                .disponivel(true)
                .build();

        when(livroService.buscarDisponiveis())
                .thenReturn(List.of(livro));

        mockMvc.perform(get("/api/livros/disponiveis"))
                .andExpect(status().isOk());
    }
}