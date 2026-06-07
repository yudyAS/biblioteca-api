package br.com.biblioteca.dto;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RespostaLivroDTO {
    private Long id;
    private String titulo;
    private String autor;
    private String isbn;
    private Integer anoPublicacao;
    private Boolean disponivel;
    private String categoria;
    private LocalDateTime dataCriacao;
    private LocalDateTime dataAtualizacao;
}
