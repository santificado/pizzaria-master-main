package br.com.fiap.pizzaria.controller;

import br.com.fiap.pizzaria.model.Conta;
import br.com.fiap.pizzaria.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.util.List;

@RestController
@RequestMapping("/conta")
public class ContaController {
    @Autowired
    private UserRepository userRepository;

    @GetMapping
    public List<Conta> getUsers() {
        return userRepository.findAll();
    }

    @GetMapping("/{id}")
    public Conta getUserById(@PathVariable Long id) {
        return (Conta) userRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Conta não encontrada"));
    }

    @PostMapping
    public Conta createUser(@RequestBody @Valid Conta conta) {
        return userRepository.save(conta);
    }


    @PutMapping("/{id}")
    public Conta updateUser(@PathVariable Long id, @RequestBody @Validated Conta updatedUser) {
        Conta conta = (Conta) userRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Conta não encontrada"));

        conta.setNome(updatedUser.getNome());
        conta.setEndereco(updatedUser.getEndereco());
        conta.setTelefone(updatedUser.getTelefone());

        return userRepository.save(conta);
    }

    @DeleteMapping("/{id}")
    public void deleteUser(@PathVariable Long id) {
        userRepository.deleteById(id);
    }

    @ExceptionHandler
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public void handleException(javax.validation.ConstraintViolationException exception) {
        throw new ResponseStatusException(HttpStatus.BAD_REQUEST, exception.getMessage());
    }
}
