package br.com.fiap.pizzaria.controller;

import br.com.fiap.pizzaria.model.Pedido;
import br.com.fiap.pizzaria.repositories.PedidoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.awt.print.Pageable;
import java.util.List;

@RestController
@RequestMapping("/cliente")
class ClienteController {
    @Autowired
    private PedidoRepository pedidoRepository;

    @GetMapping
    public List<Pedido> getClients(Pageable pageable) {
        return pedidoRepository.findAll(pageable);
    }

    @GetMapping("/{id}")
    public EntityModel<Pedido> getClientById(@PathVariable Long id) {
        Pedido pedido = (Pedido) pedidoRepository.findById(id)
                .orElseThrow(() -> {
                    return new ResponseStatusException(HttpStatus.NOT_FOUND, "Cliente não encontrado");
                });

        Link selfLink = linkTo(methodOn(ClienteController.class).getClientById(id)).withSelfRel();

        return EntityModel.of(pedido, selfLink);
    }

    @PostMapping
    public Pedido createClient(@RequestBody Pedido pedido) {
        Pedido pedidoCriado = pedidoRepository.save(pedido);

        Link selfLink = linkTo(methodOn(ClienteController.class).getClientById(pedidoCriado.getId())).withSelfRel();

        return pedidoCriado.add(selfLink);
    }

    @PutMapping("/{id}")
    public Pedido updateClient(@PathVariable Long id, @RequestBody @Validated Pedido pedidoAtualizado) {
        Pedido pedido = (Pedido) pedidoRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Cliente não encontrado"));

        pedido.setNome(pedidoAtualizado.getNome());
        pedido.setEntrega(pedidoAtualizado.getEntrega());

        pedidoAtualizado = pedidoRepository.save(pedido);

        Link selfLink = linkTo(methodOn(ClienteController.class).getClientById(pedidoAtualizado.getId())).withSelfRel();

        return pedidoAtualizado.add(selfLink);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteClient(@PathVariable Long id) {
        pedidoRepository.deleteById(id);

        return ResponseEntity.noContent().build();
    }
}
