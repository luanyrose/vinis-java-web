package lrz.ifpe.vinisweb.repository;

import lrz.ifpe.vinisweb.model.Vinil;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface VinilRepository extends JpaRepository<Vinil, Long> {
    Vinil findByCodigo(int codigo);
    List<Vinil> findByTituloContainingIgnoreCase(String titulo);
    List<Vinil> findByArtistaContainingIgnoreCase(String artista);
    List<Vinil> findByGenero(String genero);
}

