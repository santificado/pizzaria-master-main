package br.com.fiap.pizzaria.repositories;

import br.com.fiap.pizzaria.model.Cardapio;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import java.util.Optional;

public interface CardapioRepository extends CrudRepository<Cardapio, Long> {

    Optional<Cardapio> findById(Long id);

    Cardapio save(Long cardapio);

    void deleteById(Long id);

    Page<Cardapio> findAll(Pageable pageable);
}
