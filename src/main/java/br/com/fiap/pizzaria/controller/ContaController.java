package br.com.fiap.pizzaria.controller;

import br.com.fiap.pizzaria.model.Conta;
import br.com.fiap.pizzaria.repositories.UserRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.MediaTypes;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;

@RestController
@RequestMapping("/conta")
public class ContaController {

    @Autowired
    private UserRepository userRepository;

    @GetMapping(produces = MediaTypes.HAL_JSON_VALUE)
    public EntityModel<Page<Conta>> getUsers(@RequestParam(defaultValue = "0") int page,
                                             @RequestParam(defaultValue = "10") int size,
                                             @RequestParam(defaultValue = "id") String sortBy) {

        Page<Conta> contas = userRepository.findAll(PageRequest.of(page, size, Sort.by(sortBy)));

        EntityModel<Page<Conta>> entityModel = EntityModel.of(contas);

        Link selfLink = WebMvcLinkBuilder.linkTo(
                        WebMvcLinkBuilder.methodOn(ContaController.class)
                                .getUsers(page, size, sortBy))
                .withSelfRel()
                .andAffordance(WebMvcLinkBuilder
                        .afford(WebMvcLinkBuilder.methodOn(ContaController.class).createUser(null)));

        entityModel.add(selfLink);

        return entityModel;
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

    @PreAuthorize
    @PreAuthorize("isAuthenticated()")
    @GetMapping(value = "/{id}", produces = MediaTypes.HAL_JSON_VALUE)
    public EntityModel<Optional<Conta>> getUserById(@PathVariable Long id) {
        // Código do método
    }
}