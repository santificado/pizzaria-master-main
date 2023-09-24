package br.com.fiap.pizzaria.repositories;

import br.com.fiap.pizzaria.model.Pedido;

import java.awt.print.Pageable;
import java.util.List;

public interface CrudRepository<T, T1> {


    List<Pedido> findAll(Pageable pageable);
}
