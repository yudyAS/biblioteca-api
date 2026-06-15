package br.com.biblioteca.controller;

import br.com.biblioteca.dto.CategoriaDTO;
import br.com.biblioteca.service.CategoriaService;
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

@WebMvcTest(CategoriaController.class)
class CategoriaControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private CategoriaService categoriaService;

    @Test
    void deveCriarCategoria() throws Exception {
        CategoriaDTO dto = CategoriaDTO.builder()
                .nome("Ficcao")
                .build();

        CategoriaDTO resposta = CategoriaDTO.builder()
                .id(1L)
                .nome("Ficcao")
                .build();

        when(categoriaService.criarCategoria(any(CategoriaDTO.class)))
                .thenReturn(resposta);

        mockMvc.perform(post("/api/categorias")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(dto)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.nome").value("Ficcao"));
    }

    @Test
    void deveListarCategorias() throws Exception {
        CategoriaDTO categoria = CategoriaDTO.builder()
                .id(1L)
                .nome("Ficcao")
                .build();

        when(categoriaService.listarTodasCategorias())
                .thenReturn(List.of(categoria));

        mockMvc.perform(get("/api/categorias"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id").value(1))
                .andExpect(jsonPath("$[0].nome").value("Ficcao"));
    }

    @Test
    void deveBuscarCategoriaPorId() throws Exception {
        CategoriaDTO categoria = CategoriaDTO.builder()
                .id(1L)
                .nome("Ficcao")
                .build();

        when(categoriaService.buscarCategoriaPorId(1L))
                .thenReturn(categoria);

        mockMvc.perform(get("/api/categorias/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.nome").value("Ficcao"));
    }

    @Test
    void deveAtualizarCategoria() throws Exception {
        CategoriaDTO dto = CategoriaDTO.builder()
                .nome("Biografia")
                .build();

        CategoriaDTO resposta = CategoriaDTO.builder()
                .id(1L)
                .nome("Biografia")
                .build();

        when(categoriaService.atualizarCategoria(any(Long.class), any(CategoriaDTO.class)))
                .thenReturn(resposta);

        mockMvc.perform(put("/api/categorias/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(dto)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.nome").value("Biografia"));
    }

    @Test
    void deveDeletarCategoria() throws Exception {
        mockMvc.perform(delete("/api/categorias/1"))
                .andExpect(status().isNoContent());
    }

    @Test
    void deveBuscarCategoriaPorNome() throws Exception {
        CategoriaDTO categoria = CategoriaDTO.builder()
                .id(1L)
                .nome("Ficcao")
                .build();

        when(categoriaService.buscarCategoriaPorNome("Ficcao"))
                .thenReturn(categoria);

        mockMvc.perform(get("/api/categorias/nome/Ficcao"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.nome").value("Ficcao"));
    }
}
