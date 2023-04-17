package br.com.fiap.pizzaria.repositories;

import br.com.fiap.pizzaria.model.Pedido;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository

public interface PedidoRepository extends CrudRepository<Pedido, Long> {
    Optional<Object> findById(Long id);

    List<Pedido> findAll(Pageable pageable);

    Pedido save(Pedido pedido);

    void deleteById(Long id);
}
