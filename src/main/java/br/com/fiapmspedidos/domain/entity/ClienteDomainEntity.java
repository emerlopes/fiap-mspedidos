package br.com.fiapmspedidos.domain.entity;

import br.com.fiapmspedidos.application.entrypoint.rest.dto.PedidoRequestDTO;
import br.com.fiapmspedidos.application.entrypoint.rest.dto.PedidoResponseDTO;
import br.com.fiapmspedidos.infrastructure.database.entity.PedidoEntity;
import br.com.fiapmspedidos.repository.msclientes.ClienteResponseDTO;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Builder
public class ClienteDomainEntity {

    @Setter
    private UUID idExterno;

    private String nome;

    private String endereco;

    private String telefone;

    private String email;

    private LocalDateTime dataCriacao;
    private LocalDateTime dataAtualizacao;


    public static ClienteDomainEntity paraEntidadeDominio(
            final String idExterno
    ) {
        return ClienteDomainEntity.builder()
                .idExterno(UUID.fromString(idExterno))
                .build();
    }

    public static ClienteDomainEntity paraEntidadeDominio(
            final ClienteResponseDTO clienteResponseDTO) {

        return ClienteDomainEntity.builder()
                .idExterno(clienteResponseDTO.getIdExterno())
                .nome(clienteResponseDTO.getNome())
                .endereco(clienteResponseDTO.getEndereco())
                .telefone(clienteResponseDTO.getTelefone())
                .email(clienteResponseDTO.getEmail())
                .dataCriacao(clienteResponseDTO.getDataCriacao())
                .dataAtualizacao(clienteResponseDTO.getDataAtualizacao())
                .build();
    }

}
