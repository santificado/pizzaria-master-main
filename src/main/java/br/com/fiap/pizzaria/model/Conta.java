package br.com.fiap.pizzaria.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

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
    private List<Conta> pedidos;

     }


