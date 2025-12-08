package lrz.ifpe.vinisweb.repository;

import lrz.ifpe.vinisweb.model.Compra;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CompraRepository extends JpaRepository<Compra, Long> {
    List<Compra> findByClienteId(Long clienteId);
}

