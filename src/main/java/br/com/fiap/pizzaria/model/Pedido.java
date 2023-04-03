package br.com.fiap.pizzaria.model;

import java.math.BigDecimal;

public class Pedido {
 
    public String Nome;

    public Enum Tamanho;

    public BigDecimal Preco;

    public Boolean Entrega;

    public String Observacao;

    public String getNome() {
        return Nome;
    }

    public void setNome(String nome) {
        Nome = nome;
    }

    public Enum getTamanho() {
        return Tamanho;
    }

    public void setTamanho(Enum tamanho) {
        Tamanho = tamanho;
    }

    public BigDecimal getPreco() {
        return Preco;
    }

    public void setPreco(BigDecimal preco) {
        Preco = preco;
    }

    public Boolean getEntrega() {
        return Entrega;
    }

    public void setEntrega(Boolean entrega) {
        Entrega = entrega;
    }

    public String getObservacao() {
        return Observacao;
    }

    public void setObservacao(String observacao) {
        Observacao = observacao;
    }

    
}
