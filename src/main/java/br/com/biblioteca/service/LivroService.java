package br.com.biblioteca.service;

import br.com.biblioteca.dto.AtualizarLivroDTO;
import br.com.biblioteca.dto.CriarLivroDTO;
import br.com.biblioteca.dto.RespostaLivroDTO;

import java.util.List;

public interface LivroService {
    RespostaLivroDTO criarLivro(CriarLivroDTO dto);
    List<RespostaLivroDTO> listarTodosLivros();
    RespostaLivroDTO buscarLivroPorId(Long id);
    RespostaLivroDTO atualizarLivro(Long id, AtualizarLivroDTO dto);
    void deletarLivro(Long id);
    List<RespostaLivroDTO> buscarPorAutor(String autor);
    List<RespostaLivroDTO> buscarPorTitulo(String titulo);
    List<RespostaLivroDTO> buscarDisponiveis();
}

