package br.com.fiap.pizzaria.controller;

import br.com.fiap.pizzaria.repositories.CardapioRepository;
import br.com.fiap.pizzaria.model.Cardapio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping("/pizzas")
public class CardapioController {
    @Autowired
    private CardapioRepository cardapioRepository;

    @PostMapping
    public Cardapio criarPizza(@RequestBody Cardapio cardapio) {
        return cardapioRepository.save(cardapio);
    }

    @GetMapping
    public Iterable<Cardapio> listarPizzas() {
        return cardapioRepository.findAll();
    }

    @GetMapping("/{id}")
    public Cardapio buscarPizza(@PathVariable Long id) {
        return (Cardapio) cardapioRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    @PutMapping("/{id}")
    public Cardapio atualizarPizza(@PathVariable Long id, @RequestBody @Validated Cardapio cardapioAtualizado) {
        Cardapio cardapio = (Cardapio) cardapioRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        cardapio.setNome(cardapioAtualizado.getNome());
        Cardapio.setID();
        cardapio.setPreco(cardapioAtualizado.getPreco());
        Cardapio.setTamanho(cardapioAtualizado.getTamanho());
        return cardapioRepository.save(cardapio);
    }

    @DeleteMapping("/{id}")
    public void deletarPizza(@PathVariable Long id) {
        cardapioRepository.deleteById(id);
    }
}
