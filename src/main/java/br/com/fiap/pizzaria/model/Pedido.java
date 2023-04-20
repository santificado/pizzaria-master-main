package br.com.fiap.pizzaria.model;

import java.math.BigDecimal;

public class Pedido extends Conta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String nome;

    @Column(nullable = false)
    private String endereco;

    @Column(nullable = false)
    private BigDecimal telefone;

    public void Conta(Long id) {
        this.id = id;
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
