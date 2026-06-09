package br.com.biblioteca.controller;


import br.com.biblioteca.dto.AtualizarLivroDTO;
import br.com.biblioteca.dto.CriarLivroDTO;
import br.com.biblioteca.dto.RespostaLivroDTO;
import br.com.biblioteca.service.LivroService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;

import java.util.List;

@RestController
@RequestMapping("/api/livros")
@RequiredArgsConstructor
public class LivroController {

    private final LivroService livroService;

    @Operation(summary = "Cadastrar um novo livro")
    @ApiResponse(responseCode = "201", description = "Livro criado com sucesso")
    @ApiResponse(responseCode = "400", description = "Dados inválidos")
    @ApiResponse(responseCode = "409", description = "ISBN já cadastrado")
    @PostMapping
    public ResponseEntity<RespostaLivroDTO> criarLivro(
            @Valid @RequestBody CriarLivroDTO dto) {

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(livroService.criarLivro(dto));
    }


    @Operation(summary = "Listar todos os livros")
    @ApiResponse(responseCode = "200", description = "Lista retornada com sucesso")
    @GetMapping
    public ResponseEntity<List<RespostaLivroDTO>> listarTodos() {
        return ResponseEntity.ok(livroService.listarTodosLivros());
    }


    @Operation(summary = "Buscar livro por ID")
    @ApiResponse(responseCode = "200", description = "Livro encontrado")
    @ApiResponse(responseCode = "404", description = "Livro não encontrado")
    @GetMapping("/{id}")
    public ResponseEntity<RespostaLivroDTO> buscarPorId(
            @PathVariable Long id) {

        return ResponseEntity.ok(
                livroService.buscarLivroPorId(id)
        );
    }

    @Operation(summary = "Atualizar um livro")
    @ApiResponse(responseCode = "200", description = "Livro atualizado com sucesso")
    @ApiResponse(responseCode = "404", description = "Livro não encontrado")
    @PutMapping("/{id}")
    public ResponseEntity<RespostaLivroDTO> atualizar(
            @PathVariable Long id,
            @Valid @RequestBody AtualizarLivroDTO dto) {

        return ResponseEntity.ok(
                livroService.atualizarLivro(id, dto)
        );
    }

    @Operation(summary = "Deletar um livro")
    @ApiResponse(responseCode = "204", description = "Livro deletado com sucesso")
    @ApiResponse(responseCode = "404", description = "Livro não encontrado")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(
            @PathVariable Long id) {

        livroService.deletarLivro(id);

        return ResponseEntity.noContent().build();
    }

    @Operation(summary = "Buscar livros por autor")
    @ApiResponse(responseCode = "200", description = "Livros encontrados")
    @GetMapping("/autor/{autor}")
    public ResponseEntity<List<RespostaLivroDTO>> buscarPorAutor(
            @PathVariable String autor) {

        return ResponseEntity.ok(
                livroService.buscarPorAutor(autor)
        );
    }

    @Operation(summary = "Buscar livros por título")
    @ApiResponse(responseCode = "200", description = "Livros encontrados")
    @GetMapping("/titulo/{titulo}")
    public ResponseEntity<List<RespostaLivroDTO>> buscarPorTitulo(
            @PathVariable String titulo) {

        return ResponseEntity.ok(
                livroService.buscarPorTitulo(titulo)
        );
    }

    @Operation(summary = "Buscar livros disponíveis")
    @ApiResponse(responseCode = "200", description = "Livros encontrados")
    @GetMapping("/disponiveis")
    public ResponseEntity<List<RespostaLivroDTO>> buscarDisponiveis() {

        return ResponseEntity.ok(
                livroService.buscarDisponiveis()
        );
    }
}
