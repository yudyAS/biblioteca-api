package br.com.biblioteca.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "categorias")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Categoria {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true, length = 100)
    private String nome;

    @OneToMany(mappedBy = "categoria", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Livro> livros = new ArrayList<>();

    public void addLivro(Livro livro) {
        livros.add(livro);
        livro.setCategoria(this);
    }

    public void removeLivro(Livro livro) {
        livros.remove(livro);
        livro.setCategoria(null);
    }
}

