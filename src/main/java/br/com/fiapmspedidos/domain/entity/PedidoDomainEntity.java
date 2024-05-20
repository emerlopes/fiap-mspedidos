package br.com.fiapmspedidos.domain.entity;

import br.com.fiapmspedidos.application.entrypoint.rest.dto.PedidoRequestDTO;
import br.com.fiapmspedidos.application.entrypoint.rest.dto.PedidoResponseDTO;
import br.com.fiapmspedidos.infrastructure.database.entity.PedidoEntity;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

@Getter
@Builder
public class PedidoDomainEntity {

    private Long idPedido;
    private Long idProduto;
    private Integer quantidade;
    private UUID idExternoCliente;

    @Setter
    private String enderecoEntrega;

    @Setter
    private LocalDate dataEntrega;

    @Setter
    private BigDecimal valorTotal;

    public static PedidoDomainEntity paraEntidadeDominio(
            final PedidoRequestDTO pedidoRequestDTO
    ) {
        return PedidoDomainEntity.builder()
                .idProduto(pedidoRequestDTO.getIdProduto())
                .quantidade(pedidoRequestDTO.getQuantidade())
                .idExternoCliente(UUID.fromString(pedidoRequestDTO.getIdCliente().toString()))
                .build();
    }

    public static PedidoDomainEntity paraEntidadeDominio(
            final PedidoEntity pedidoEntity
    ) {
        return PedidoDomainEntity.builder()
                .idPedido(pedidoEntity.getId())
                .idProduto(pedidoEntity.getIdProduto())
                .quantidade(pedidoEntity.getQuantidade())
                .idExternoCliente(pedidoEntity.getIdCliente())
                .valorTotal(pedidoEntity.getValorTotal())
                .build();
    }

    public static PedidoResponseDTO paraDTO(
            final PedidoDomainEntity pedidoDomainEntity
    ) {
        return PedidoResponseDTO.builder()
                .idPedido(pedidoDomainEntity.getIdPedido())
                .idProduto(pedidoDomainEntity.getIdProduto())
                .quantidade(pedidoDomainEntity.getQuantidade())
                .idExternoCliente(pedidoDomainEntity.getIdExternoCliente())
                .valorTotal(pedidoDomainEntity.getValorTotal())
                .build();
    }

    public static PedidoEntity paraEntidade(
            final PedidoDomainEntity pedidoDomainEntity
    ) {
        return new PedidoEntity()
                .setIdProduto(pedidoDomainEntity.getIdProduto())
                .setQuantidade(pedidoDomainEntity.getQuantidade())
                .setIdCliente(pedidoDomainEntity.getIdExternoCliente())
                .setValorTotal(pedidoDomainEntity.getValorTotal());
    }

}
