package br.com.biblioteca.controller;

import br.com.biblioteca.dto.RespostaLivroDTO;
import br.com.biblioteca.service.LivroService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(LivroController.class)
class LivroControllerTest {

    @Autowired
    private MockMvc mockMvc;

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
}
