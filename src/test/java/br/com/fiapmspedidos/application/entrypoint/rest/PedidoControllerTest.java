package br.com.fiapmspedidos.application.entrypoint.rest;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

import java.math.BigDecimal;
import java.util.UUID;

import br.com.fiapmspedidos.application.entrypoint.rest.dto.PedidoRequestDTO;
import br.com.fiapmspedidos.application.entrypoint.rest.dto.PedidoResponseDTO;
import br.com.fiapmspedidos.domain.entity.PedidoDomainEntity;
import br.com.fiapmspedidos.domain.usecase.CriarPedidoUseCase;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

@ExtendWith(MockitoExtension.class)
public class PedidoControllerTest {

    @Mock
    private CriarPedidoUseCase criarPedidoUseCase;

    @InjectMocks
    private PedidoController pedidoController;

    @Test
    public void testCriarPedido() {
        // Dados de entrada
        PedidoRequestDTO pedidoRequestDTO = PedidoRequestDTO.builder()
                .idProduto(1L)
                .quantidade(2)
                .idCliente(UUID.randomUUID())
                .build();

        PedidoDomainEntity pedidoDomainEntity = PedidoDomainEntity.builder()
                .idPedido(1L)
                .idProduto(pedidoRequestDTO.getIdProduto())
                .quantidade(pedidoRequestDTO.getQuantidade())
                .idExternoCliente(pedidoRequestDTO.getIdCliente())
                .valorTotal(BigDecimal.TEN)
                .build();

        when(criarPedidoUseCase.execute(any())).thenReturn(pedidoDomainEntity);

        ResponseEntity<PedidoResponseDTO> responseEntity = pedidoController.criarPedido(pedidoRequestDTO);

        assertEquals(HttpStatus.CREATED, responseEntity.getStatusCode());

        verify(criarPedidoUseCase).execute(any());
    }
}
