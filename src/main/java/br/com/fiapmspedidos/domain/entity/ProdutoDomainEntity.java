package br.com.fiapmspedidos.domain.entity;

import lombok.Builder;
import lombok.Getter;

import java.math.BigDecimal;

@Getter
@Builder
public class ProdutoDomainEntity {

    private Long idPedido;
    private String nome;
    private String descricao;
    private Double preco;
    private Integer quantidade;
    private BigDecimal valorTotal;

    @Override
    public String toString() {
        return "ProdutoDomainEntity{" +
                "idPedido=" + idPedido +
                ", nome='" + nome + '\'' +
                ", descricao='" + descricao + '\'' +
                ", preco=" + preco +
                ", quantidade=" + quantidade +
                ", valorTotal=" + valorTotal +
                '}';
    }
}
