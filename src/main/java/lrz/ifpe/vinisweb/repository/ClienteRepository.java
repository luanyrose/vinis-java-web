package lrz.ifpe.vinisweb.repository;

import lrz.ifpe.vinisweb.model.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface ClienteRepository extends JpaRepository<Cliente, Long> {
    Cliente findByCpf(String cpf);
    List<Cliente> findByNomeContainingIgnoreCase(String nome);
    List<Cliente> findByTipoCliente(String tipoCliente);
}

