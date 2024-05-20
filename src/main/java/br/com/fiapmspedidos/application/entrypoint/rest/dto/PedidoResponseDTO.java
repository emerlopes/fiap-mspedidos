package br.com.fiapmspedidos.application.entrypoint.rest.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Getter;
import lombok.experimental.Accessors;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

@Getter
@Builder
@Accessors(chain = true)
public class PedidoResponseDTO {
    @JsonProperty("id_pedido")
    private Long idPedido;

    @JsonProperty("id_produto")
    private Long idProduto;

    private Integer quantidade;

    @JsonProperty("id_cliente")
    private UUID idExternoCliente;

    @JsonProperty("valor_total")
    private BigDecimal valorTotal;

    @JsonProperty("endereco_entrega")
    private String enderecoEntrega;

    @JsonProperty("data_entrega")
    private LocalDate dataEntrega;
}
