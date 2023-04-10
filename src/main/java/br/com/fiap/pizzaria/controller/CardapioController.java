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
    public Cardapio criarPizza(@RequestBody @Validated Cardapio cardapio) {
        return cardapioRepository.save(cardapio);
    }

    @PutMapping("/{id}")
    public Cardapio atualizarPizza(@PathVariable Long id, @RequestBody @Validated Cardapio cardapioAtualizado) {
        Cardapio cardapio = (Cardapio) cardapioRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        cardapio.setNome(cardapioAtualizado.getNome());
        cardapio.setPreco(cardapioAtualizado.getPreco());
        Cardapio.setTamanho(cardapioAtualizado.getTamanho());
        return cardapioRepository.save(cardapio);
    }

    // outros métodos omitidos



    @DeleteMapping("/{id}")
    public void deletarPizza(@PathVariable Long id) {
        cardapioRepository.deleteById(id);
    }
}
