package lrz.ifpe.vinisweb.repository;

import lrz.ifpe.vinisweb.model.Funcionario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FuncionarioRepository extends JpaRepository<Funcionario, Long> {
    Funcionario findByCpf(String cpf);
}

