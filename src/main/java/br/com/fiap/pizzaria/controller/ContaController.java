package br.com.fiap.pizzaria.controller;

import br.com.fiap.pizzaria.model.Conta;
import br.com.fiap.pizzaria.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/conta")
public class ContaController {
    @Autowired
    private UserRepository userRepository;

    @GetMapping
    public Page<Conta> getUsers(@RequestParam(defaultValue = "0") int page,
                                @RequestParam(defaultValue = "10") int size,
                                @RequestParam(defaultValue = "id") String sortBy) {
        return userRepository.findAll(PageRequest.of(page, size, Sort.by(sortBy)));
    }

    @GetMapping("/{id}")
    public Optional<Object> getUserById(@PathVariable Long id) {
        return userRepository.findById(id);
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

    @GetMapping("/search")
    public List<Conta> searchUsers(@RequestParam(required = false) String nome,
                                   @RequestParam(required = false) String endereco,
                                   @RequestParam(required = false) String telefone) {
        return userRepository.findAll();
    }

    @GetMapping("/sorted")
    public List<Conta> getUsersSorted(@RequestParam(defaultValue = "id") String sortBy) {
        return userRepository.findAll(Sort.by(sortBy));
    }

    @ExceptionHandler
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public void handleException(javax.validation.ConstraintViolationException exception) throws ResponseStatusException {
        throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                exception.getMessage());
    }
}
