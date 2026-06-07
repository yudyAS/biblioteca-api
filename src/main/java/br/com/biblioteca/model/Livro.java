package br.com.biblioteca.model;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;

@Entity
@Table(name = "livros", uniqueConstraints = {@UniqueConstraint(columnNames = "isbn")})
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Livro {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 255)
    private String titulo;

    @Column(nullable = false, length = 255)
    private String autor;

    @Column(nullable = false, length = 20, unique = true)
    private String isbn;

    @Column(nullable = false)
    private Integer anoPublicacao;

    @Column(nullable = false)
    private Boolean disponivel = true;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "categoria_id")
    private Categoria categoria;

    @CreationTimestamp
    private LocalDateTime dataCriacao;

    @UpdateTimestamp
    private LocalDateTime dataAtualizacao;
}
