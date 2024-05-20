package br.com.fiapmspedidos.application.entrypoint.rest.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Getter;
import lombok.experimental.Accessors;

import java.util.UUID;

@Getter
@Builder
@Accessors(chain = true)
@JsonIgnoreProperties(ignoreUnknown = true)
public class PedidoRequestDTO {

    @JsonProperty("id_produto")
    private Long idProduto;

    private Integer quantidade;

    @JsonProperty("id_cliente")
    private UUID idCliente;
}
