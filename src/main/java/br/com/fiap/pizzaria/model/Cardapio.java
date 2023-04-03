package br.com.fiap.pizzaria.model;

import java.math.BigDecimal;

public class Cardapio {

    public String Nome;

    public static Enum Tamanho;

    public BigDecimal Preco;

    public static BigDecimal Id;

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
