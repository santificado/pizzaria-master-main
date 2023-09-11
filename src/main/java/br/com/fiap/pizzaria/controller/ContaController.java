package br.com.fiap.pizzaria.controller;

import br.com.fiap.pizzaria.model.Conta;
import br.com.fiap.pizzaria.repositories.UserRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.MediaTypes;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.lang.reflect.Method;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/conta")
public class ContaController {

    @Autowired
    private UserRepository userRepository;

    @GetMapping(produces = MediaTypes.HAL_JSON_VALUE)
    public EntityModel<List<Conta>> getUsers(@RequestParam(defaultValue = "0") int page,
                                             @RequestParam(defaultValue = "10") int size,
                                             @RequestParam(defaultValue = "id") String sortBy) {

        List<Conta> contas = userRepository.findAll(PageRequest.of(page, size, Sort.by(sortBy)));

        EntityModel<List<Conta>> entityModel = EntityModel.of(contas);

        Link selfLink = WebMvcLinkBuilder.linkTo(
                        WebMvcLinkBuilder.methodOn(ContaController.class)
                                .getUsers(page, size, sortBy))
                .withSelfRel()
                .andAffordance(WebMvcLinkBuilder
                        .afford(WebMvcLinkBuilder.methodOn(ContaController.class).createUser(null)));

        entityModel.add(selfLink);

        return entityModel;
    }

    private Object createUser(Object o) {
        return null;
    }


    @GetMapping(value = "/{id}", produces = MediaTypes.HAL_JSON_VALUE)
    public EntityModel<Optional<Conta>> getUserById(@PathVariable Long id) {

        Optional<Conta> conta = userRepository.findById(id);

        if (conta.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Conta não encontrada");
        }

        EntityModel<Optional<Conta>> entityModel = EntityModel.of(conta);

        Link selfLink = WebMvcLinkBuilder.linkTo(
                        WebMvcLinkBuilder.methodOn(ContaController.class)
                                .getUserById(id))
                .withSelfRel()
                .andAffordance(WebMvcLinkBuilder
                        .afford(WebMvcLinkBuilder.methodOn(ContaController.class).updateUser(id, null)));

        Link deleteLink = WebMvcLinkBuilder.linkTo(
                        WebMvcLinkBuilder.methodOn(ContaController.class)
                                .deleteUser(id))
                .withRel("delete");

        entityModel.add(selfLink, deleteLink);

        return entityModel;
    }

    @PutMapping("/{id}")
    public EntityModel<Conta> updateUser(@PathVariable Long id, @RequestBody @Valid Conta contaAtualizada) {
        // Implemente a lógica para atualizar a conta
        // Retorne a conta atualizada com os links apropriados
        return null;
    }

    @DeleteMapping("/{id}")
    public Method deleteUser(@PathVariable Long id) {
        // Implemente a lógica para excluir a conta
        // Não é necessário retornar nada
        return null;
    }
}
