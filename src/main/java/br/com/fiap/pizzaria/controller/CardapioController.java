package br.com.fiap.pizzaria.controller;

import br.com.fiap.pizzaria.model.Cardapio;
import br.com.fiap.pizzaria.repositories.CardapioRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/pizzas")
public class CardapioController {

    private final CardapioRepository cardapioRepository;

    public CardapioController(CardapioRepository cardapioRepository) {
        this.cardapioRepository = cardapioRepository;
    }

    @PostMapping
    public EntityModel<Cardapio> criarPizza(@RequestBody @Validated Cardapio cardapio) {
        Cardapio novaPizza = cardapioRepository.save(cardapio);
        return createCardapioEntityModel(novaPizza);
    }

    @PutMapping("/{id}")
    public EntityModel<Cardapio> atualizarPizza(@PathVariable Long id, @RequestBody @Validated Cardapio cardapioAtualizado) {
        Cardapio pizza = cardapioRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));

        pizza.setNome(cardapioAtualizado.getNome());
        pizza.setPreco(cardapioAtualizado.getPreco());
        pizza.setTamanho(cardapioAtualizado.getTamanho());

        Cardapio pizzaAtualizada = cardapioRepository.save(pizza);

        return createCardapioEntityModel(pizzaAtualizada);
    }

    @GetMapping
    public CollectionModel<EntityModel<Cardapio>> getPizzas(Pageable pageable) {
        Page<Cardapio> pizzasPage = cardapioRepository.findAll(pageable);

        List<EntityModel<Cardapio>> pizzasModel = pizzasPage.getContent().stream()
                .map(this::createCardapioEntityModel)
                .collect(Collectors.toList());

        Link selfLink = WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(CardapioController.class).getPizzas(pageable)).withSelfRel();

        return CollectionModel.of(pizzasModel, selfLink);
    }

    @GetMapping("/{id}")
    public EntityModel<Cardapio> buscarPizza(@PathVariable Long id) {
        Cardapio pizza = cardapioRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));

        return createCardapioEntityModel(pizza);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public Object excluirPizza(@PathVariable Long id) {
        cardapioRepository.deleteById(id);
        return null;
    }

    private EntityModel<Cardapio> createCardapioEntityModel(Cardapio pizza) {
        Link selfLink = WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(CardapioController.class).buscarPizza(pizza.getId())).withSelfRel();
        Link atualizarLink = WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(CardapioController.class).atualizarPizza(pizza.getId(), pizza)).withRel("atualizar");
        Link excluirLink = WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(CardapioController.class).excluirPizza(pizza.getId())).withRel("excluir");

        return EntityModel.of(pizza, selfLink, atualizarLink, excluirLink);
    }
}
