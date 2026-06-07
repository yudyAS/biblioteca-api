package br.com.biblioteca.mapper;

import br.com.biblioteca.dto.AtualizarLivroDTO;
import br.com.biblioteca.dto.CriarLivroDTO;
import br.com.biblioteca.dto.RespostaLivroDTO;
import br.com.biblioteca.model.Categoria;
import br.com.biblioteca.model.Livro;

import org.springframework.stereotype.Component;

import java.util.Objects;

@Component
public class LivroMapper {

    public Livro toEntity(CriarLivroDTO dto, Categoria categoria) {
        Livro livro = Livro.builder()
                .titulo(dto.getTitulo())
                .autor(dto.getAutor())
                .isbn(dto.getIsbn())
                .anoPublicacao(dto.getAnoPublicacao())
                .disponivel(true)
                .build();
        if (Objects.nonNull(categoria)) {
            livro.setCategoria(categoria);
        }
        return livro;
    }

    public RespostaLivroDTO toRespostaDTO(Livro livro) {
        String categoria = null;
        if (livro.getCategoria() != null) {
            categoria = livro.getCategoria().getNome();
        }
        return RespostaLivroDTO.builder()
                .id(livro.getId())
                .titulo(livro.getTitulo())
                .autor(livro.getAutor())
                .isbn(livro.getIsbn())
                .anoPublicacao(livro.getAnoPublicacao())
                .disponivel(livro.getDisponivel())
                .categoria(categoria)
                .dataCriacao(livro.getDataCriacao())
                .dataAtualizacao(livro.getDataAtualizacao())
                .build();
    }

    public void updateEntityFromDto(AtualizarLivroDTO dto, Livro livro, Categoria categoria) {
        if (dto.getTitulo() != null) livro.setTitulo(dto.getTitulo());
        if (dto.getAutor() != null) livro.setAutor(dto.getAutor());
        if (dto.getIsbn() != null) livro.setIsbn(dto.getIsbn());
        if (dto.getAnoPublicacao() != null) livro.setAnoPublicacao(dto.getAnoPublicacao());
        if (dto.getDisponivel() != null) livro.setDisponivel(dto.getDisponivel());
        if (categoria != null) livro.setCategoria(categoria);
    }
}

