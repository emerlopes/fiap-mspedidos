package br.com.fiapmspedidos.repository.mspedidos;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class ProdutoDomainEntityDTO {

    private Long id;
    private String name;
    private String description;
    private Double price;
    private Integer quantity;

}