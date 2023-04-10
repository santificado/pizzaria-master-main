


package br.com.fiap.pizzaria.model;
import java.math.BigDecimal;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

import java.math.BigDecimal;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@Entity
public class Conta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;

    private String endereco;

    private BigDecimal telefone;

    @OneToMany(mappedBy = "conta", cascade = CascadeType.ALL)
    private List<Pedido> pedidos;

}

    public String getNome() {
        return Nome;
    }

    public void setNome(String nome) {
        Nome = nome;
    }

    public String getEndereco() {
        return Endereco;
    }

    public void setEndereco(String endereco) {
        Endereco = endereco;
    }

    public BigDecimal getTelefone() {
        return Telefone;
    }

    public void setTelefone(BigDecimal telefone) {
        Telefone = telefone;
    }

    public BigDecimal getId() {
        return Id;
    }

    public void setID(BigDecimal id) {
        Id = id;
    }

    
}
