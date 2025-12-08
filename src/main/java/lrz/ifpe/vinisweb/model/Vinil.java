package lrz.ifpe.vinisweb.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

// Representa um disco de vinil vendido/locado na loja.
@Entity
public class Vinil {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    // Atributos simples do vinil
    @Column(nullable = false, unique = true)
    private int codigo;
    
    @Column(nullable = false)
    private String titulo;
    
    @Column(nullable = false)
    private String artista;
    
    @Column(nullable = false)
    private double precoVenda;
    
    @Column(nullable = false)
    private String genero;
    
    @Column(nullable = false)
    private int qtdDisponivel;

    public Vinil() {
    }

    public Vinil(int codigo, String titulo, String artista,
                 double precoVenda, int qtdDisponivel,
                 String genero) {
        this.codigo = codigo;
        this.titulo = titulo;
        this.artista = artista;
        this.precoVenda = precoVenda;
        this.qtdDisponivel = qtdDisponivel;
        this.genero = genero;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    
    public int getCodigo() { return codigo; }
    public void setCodigo(int codigo) { this.codigo = codigo; }
    
    public String getTitulo() { return titulo; }
    public void setTitulo(String titulo) { this.titulo = titulo; }
    
    public String getArtista() { return artista; }
    public void setArtista(String artista) { this.artista = artista; }
    
    public double getPrecoVenda() { return precoVenda; }
    public void setPrecoVenda(double precoVenda) { this.precoVenda = precoVenda; }

    public String getGenero() { return genero; }
    public void setGenero(String genero) { this.genero = genero; }
    
    public int getQtdDisponivel() { return qtdDisponivel; }
    public void setQtdDisponivel(int qtdDisponivel) { this.qtdDisponivel = qtdDisponivel; }

    // Baixa a quantidade do estoque deste vinil (evita negativo)
    public void diminuirEstoque(int quantidade) {
        if (quantidade <= 0) return;
        if (qtdDisponivel >= quantidade) {
            qtdDisponivel -= quantidade;
        }
    }

    @Override
    public String toString() {
        return "Vinil{" +
                "codigo=" + codigo +
                ", titulo='" + titulo + '\'' +
                ", artista='" + artista + '\'' +
                ", genero='" + genero + '\'' +
                ", precoVenda=" + precoVenda +
                ", qtdDisponivel=" + qtdDisponivel +
                '}';
    }
}