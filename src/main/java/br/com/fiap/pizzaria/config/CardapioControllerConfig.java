package br.com.fiap.pizzaria.config;

import br.com.fiap.pizzaria.controller.CardapioController;
import br.com.fiap.pizzaria.repositories.CardapioRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
@Configuration
public class CardapioControllerConfig {

    @Bean
    public CardapioController cardapioController(CardapioRepository cardapioRepository) {
        return new CardapioController(cardapioRepository);
    }
}
