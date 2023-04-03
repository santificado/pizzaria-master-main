package br.com.fiap.pizzaria.repositories;

import br.com.fiap.pizzaria.model.Cardapio;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CardapioRepository extends CrudRepository<Cardapio, Long> {

    Iterable<Cardapio> findAll();

    Optional<Object> findById(Long id);

    Cardapio save(Cardapio cardapio);

    void deleteById(Long id);
}