package br.com.biblioteca.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CategoriaDTO {
    private Long id;
    private String nome;
}
