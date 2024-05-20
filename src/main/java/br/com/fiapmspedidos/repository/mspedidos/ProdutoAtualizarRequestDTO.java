package br.com.fiapmspedidos.repository.mspedidos;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class ProdutoAtualizarRequestDTO {
    @JsonProperty("quantidade_solicitada")
    private Integer quantidadeSolicitada;
}
