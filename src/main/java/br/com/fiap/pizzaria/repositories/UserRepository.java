package br.com.fiap.pizzaria.repositories;

import br.com.fiap.pizzaria.model.Conta;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends CrudRepository<Conta, Long> {
    Optional<Conta> findById(Long id);

    Conta save(Conta conta);

    void deleteById(Long id);

    List<Conta> findAll(PageRequest of);
}
