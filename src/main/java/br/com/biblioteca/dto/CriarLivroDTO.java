package br.com.biblioteca.dto;

import jakarta.validation.constraints.*;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CriarLivroDTO {

    @NotBlank
    @Size(max = 255)
    private String titulo;

    @NotBlank
    @Size(max = 255)
    private String autor;

    @NotBlank
    @Size(max = 20)
    @Pattern(regexp = "^[0-9\\-]{10,17}$", message = "ISBN deve conter 10 ou 13 dígitos com hífens opcionais")
    private String isbn;

    @NotNull
    @Min(1000)
    @Max(9999)
    private Integer anoPublicacao;

    // categoria opcional ao criar
    private Long categoriaId;
}
