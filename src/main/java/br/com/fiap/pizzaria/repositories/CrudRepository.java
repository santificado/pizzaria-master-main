package br.com.fiap.pizzaria.repositories;

import br.com.fiap.pizzaria.model.Pedido;
import org.springframework.stereotype.Repository;

import java.awt.print.Pageable;
import java.util.List;

@Repository
public interface CrudRepository<T, T1> {


    List<Pedido> findAll(Pageable pageable);
}
