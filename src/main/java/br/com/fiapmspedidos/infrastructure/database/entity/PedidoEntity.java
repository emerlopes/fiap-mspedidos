package br.com.fiapmspedidos.infrastructure.database.entity;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.math.BigDecimal;
import java.util.UUID;

@Getter
@Setter
@Entity
@Accessors(chain = true)
public class PedidoEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private UUID idCliente;
    private Long idProduto;
    private Integer quantidade;
    private BigDecimal valorTotal;
}
