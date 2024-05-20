package br.com.fiapmspedidos.repository.msentregas;


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
public class EntregaRequestDTO {

    @JsonProperty("id_cliente")
    private UUID idCliente;

    @JsonProperty("id_pedido")
    private Long idPedido;

    @JsonProperty("endereco_entrega")
    private String enderecoEntrega;

}
