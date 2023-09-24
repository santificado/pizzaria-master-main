package br.com.fiap.pizzaria.config;

import br.com.fiap.pizzaria.repositories.CardapioRepository;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Configuration
@EnableJpaRepositories(basePackageClasses = CardapioRepository.class)
public class JpaConfig {


}
