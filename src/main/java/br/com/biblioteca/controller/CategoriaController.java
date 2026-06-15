package br.com.biblioteca.controller;

import br.com.biblioteca.dto.CategoriaDTO;
import br.com.biblioteca.service.CategoriaService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/categorias")
@RequiredArgsConstructor
public class CategoriaController {

    private final CategoriaService categoriaService;

    @Operation(summary = "Cadastrar uma nova categoria")
    @ApiResponse(responseCode = "201", description = "Categoria criada com sucesso")
    @ApiResponse(responseCode = "400", description = "Dados inválidos")
    @PostMapping
    public ResponseEntity<CategoriaDTO> criarCategoria(
            @Valid @RequestBody CategoriaDTO dto) {

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(categoriaService.criarCategoria(dto));
    }

    @Operation(summary = "Listar todas as categorias")
    @ApiResponse(responseCode = "200", description = "Lista retornada com sucesso")
    @GetMapping
    public ResponseEntity<List<CategoriaDTO>> listarCategorias() {
        return ResponseEntity.ok(categoriaService.listarTodasCategorias());
    }

    @Operation(summary = "Buscar categoria por ID")
    @ApiResponse(responseCode = "200", description = "Categoria encontrada")
    @ApiResponse(responseCode = "404", description = "Categoria não encontrada")
    @GetMapping("/{id}")
    public ResponseEntity<CategoriaDTO> buscarPorId(@PathVariable Long id) {
        return ResponseEntity.ok(categoriaService.buscarCategoriaPorId(id));
    }

    @Operation(summary = "Atualizar uma categoria")
    @ApiResponse(responseCode = "200", description = "Categoria atualizada com sucesso")
    @ApiResponse(responseCode = "404", description = "Categoria não encontrada")
    @PutMapping("/{id}")
    public ResponseEntity<CategoriaDTO> atualizarCategoria(
            @PathVariable Long id,
            @Valid @RequestBody CategoriaDTO dto) {

        return ResponseEntity.ok(categoriaService.atualizarCategoria(id, dto));
    }

    @Operation(summary = "Deletar uma categoria")
    @ApiResponse(responseCode = "204", description = "Categoria deletada com sucesso")
    @ApiResponse(responseCode = "404", description = "Categoria não encontrada")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarCategoria(@PathVariable Long id) {
        categoriaService.deletarCategoria(id);
        return ResponseEntity.noContent().build();
    }

    @Operation(summary = "Buscar categoria por nome")
    @ApiResponse(responseCode = "200", description = "Categoria encontrada")
    @ApiResponse(responseCode = "404", description = "Categoria não encontrada")
    @GetMapping("/nome/{nome}")
    public ResponseEntity<CategoriaDTO> buscarPorNome(@PathVariable String nome) {
        return ResponseEntity.ok(categoriaService.buscarCategoriaPorNome(nome));
    }
}
