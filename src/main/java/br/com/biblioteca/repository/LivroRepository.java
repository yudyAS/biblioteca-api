package br.com.biblioteca.repository;

import br.com.biblioteca.model.Livro;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LivroRepository extends JpaRepository<Livro, Long> {
    List<Livro> findByAutorIgnoreCase(String autor);
    List<Livro> findByTituloContainingIgnoreCase(String titulo);
    Livro findByIsbn(String isbn);
    List<Livro> findByDisponivel(Boolean disponivel);
}
