package br.com.fiap.pizzaria.controller;

import br.com.fiap.pizzaria.model.Pedido;
import br.com.fiap.pizzaria.repositories.PedidoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/cliente")
public class ClienteController {

    @Autowired
    private PedidoRepository pedidoRepository;

    @GetMapping
    public List<Pedido> getPedidos(Pageable pageable) {
        return pedidoRepository.findAll((java.awt.print.Pageable) pageable);
    }

    @GetMapping("/{id}")
    public EntityModel<Pedido> getPedidoById(@PathVariable Long id) {
        Pedido pedido = (Pedido) pedidoRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Pedido não encontrado"));

        Link selfLink = WebMvcLinkBuilder.linkTo(ClienteController.class)
                .slash(pedido.getId()).withSelfRel();

        return EntityModel.of(pedido, selfLink);
    }

    @PostMapping
    public Pedido createPedido(@RequestBody @Validated Pedido pedido) {
        Pedido pedidoCriado = pedidoRepository.save(pedido);

        Link selfLink = WebMvcLinkBuilder.linkTo(ClienteController.class)
                .slash(pedidoCriado.getId()).withSelfRel();

        return pedidoCriado;
    }

    @PutMapping("/{id}")
    public Pedido updatePedido(@PathVariable Long id, @RequestBody @Validated Pedido pedidoAtualizado) {
        Pedido pedido = (Pedido) pedidoRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Pedido não encontrado"));

        pedido.setNome(pedidoAtualizado.getNome());
        pedido.setEndereco(pedidoAtualizado.getEndereco());

        pedidoAtualizado = pedidoRepository.save(pedido);

        Link selfLink = WebMvcLinkBuilder.linkTo(ClienteController.class)
                .slash(pedidoAtualizado.getId()).withSelfRel();

        return pedidoAtualizado;
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deletePedido(@PathVariable Long id) {
        pedidoRepository.deleteById(id);

        return ResponseEntity.noContent().build();
    }
}
