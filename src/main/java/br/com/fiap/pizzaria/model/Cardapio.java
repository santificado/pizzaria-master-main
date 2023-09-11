package br.com.fiap.pizzaria.model;

import jakarta.persistence.*;
import lombok.Data;

import java.math.BigDecimal;


    @Data
    @Entity
    public class Cardapio {

        public Long getId() {
            return id;
        }

        public void setId(Long id) {
            this.id = id;
        }

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long id;

        private String nome;

        @Enumerated(EnumType.STRING)
        private Tamanho tamanho;

        private BigDecimal preco;

        //getters e setters

        public enum Tamanho {
            PEQUENA, MEDIA, GRANDE
        }

    }







