package br.com.fiap.pizzaria.model;

import java.math.BigDecimal;

public class Cardapio {

    @Data
    @Entity
    public class Cardapio {

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


    public String getNome() {
        return Nome;
    }

    public void setNome(String nome) {
        Nome = nome;
    }

    public Enum getTamanho() {
        return Tamanho;
    }

    public static void setTamanho(Enum tamanho) {
        Tamanho = tamanho;
    }

    public BigDecimal getPreco() {
        return Preco;
    }

    public void setPreco(BigDecimal preco) {
        Preco = preco;
    }

    public BigDecimal getID() {
        return Id;
    }

    public static void setID() {
    }



}
