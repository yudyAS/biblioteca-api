package br.com.biblioteca.dto;

import jakarta.validation.constraints.*;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CategoriaDTO {
    private Long id;

    @NotBlank(message = "Nome da categoria não pode estar vazio")
    @Size(max = 255, message = "Nome da categoria deve ter no máximo 255 caracteres")
    private String nome;
}
