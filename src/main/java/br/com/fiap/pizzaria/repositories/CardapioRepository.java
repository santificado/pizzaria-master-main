package br.com.fiap.pizzaria.repositories;

import br.com.fiap.pizzaria.model.Cardapio;
import org.springframework.boot.autoconfigure.data.web.SpringDataWebProperties;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CardapioRepository extends CrudRepository<Cardapio, Long> {

    Iterable<Cardapio> findAll(SpringDataWebProperties.Pageable pageable);

    Optional<Object> findById(Long id);

    Cardapio save(Long cardapio);

    void deleteById(Long id);

    Page<Cardapio> findAll(Pageable pageable);
}