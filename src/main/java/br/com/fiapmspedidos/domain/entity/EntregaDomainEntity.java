package br.com.fiapmspedidos.domain.entity;


import br.com.fiapmspedidos.repository.msentregas.EntregaResponseDTO;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDate;
import java.util.UUID;

@Getter
@Builder
public class EntregaDomainEntity {

    private Long idEntrega;
    private UUID idCliete;
    private Long idPedido;
    private String enderecoEntrega;
    private LocalDate dataEntrega;

    public static EntregaDomainEntity paraEntidadeDominio(
            final ClienteDomainEntity cliente,
            final PedidoDomainEntity pedido
    ) {
        return EntregaDomainEntity.builder()
                .idCliete(cliente.getIdExterno())
                .idPedido(pedido.getIdPedido())
                .enderecoEntrega(cliente.getEndereco())
                .build();
    }

    public static EntregaDomainEntity paraEntidadeDominio(
            final EntregaResponseDTO entregaResponseDTO
    ) {
        return EntregaDomainEntity.builder()
                .idEntrega(entregaResponseDTO.getIdEntrega())
                .idCliete(entregaResponseDTO.getIdCliete())
                .idPedido(entregaResponseDTO.getIdPedido())
                .enderecoEntrega(entregaResponseDTO.getEnderecoEntrega())
                .dataEntrega(entregaResponseDTO.getDataEntrega())
                .build();
    }

}
