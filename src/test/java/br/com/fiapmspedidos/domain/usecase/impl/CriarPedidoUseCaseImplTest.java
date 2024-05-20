package br.com.fiapmspedidos.domain.usecase.impl;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import br.com.fiapmspedidos.application.exception.IllegalRequestException;
import br.com.fiapmspedidos.domain.entity.ClienteDomainEntity;
import br.com.fiapmspedidos.domain.entity.PedidoDomainEntity;
import br.com.fiapmspedidos.domain.entity.ProdutoDomainEntity;
import br.com.fiapmspedidos.domain.repository.ClienteDomainRepository;
import br.com.fiapmspedidos.domain.repository.PedidoDomainRepository;
import br.com.fiapmspedidos.domain.repository.ProdutoDomainRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.slf4j.Logger;

import java.util.UUID;

public class CriarPedidoUseCaseImplTest {

    @Mock
    private Logger logger;

    @Mock
    private PedidoDomainRepository pedidoDomainRepository;

    @Mock
    private ProdutoDomainRepository produtoDomainRepository;

    @Mock
    private ClienteDomainRepository clienteDomainRepository;

    @InjectMocks
    private CriarPedidoUseCaseImpl criarPedidoUseCase;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }


    public void testExecute() {
        PedidoDomainEntity pedido = PedidoDomainEntity.builder()
                .idProduto(1L)
                .quantidade(10)
                .build();

        ProdutoDomainEntity produto = ProdutoDomainEntity.builder()
                .idPedido(1L)
                .quantidade(15)
                .preco(10.0)
                .build();

        when(produtoDomainRepository.buscarProdutoPorId(1L)).thenReturn(produto);
        when(pedidoDomainRepository.criarPedido(pedido)).thenReturn(pedido);
        when(clienteDomainRepository.buscarClientePorId(criarClienteDomainEntity())).thenReturn(criarClienteDomainEntity());

        PedidoDomainEntity resultado = criarPedidoUseCase.execute(pedido);

        assertEquals(pedido, resultado);
        verify(logger).info("Quantidade solicitada: {}", 10);
        verify(logger).info("Quantidade em estoque: {}", 15);
        verify(pedidoDomainRepository).criarPedido(pedido);
    }

    @Test
    public void testExecuteQuantidadeInsuficiente() {
        PedidoDomainEntity pedido = PedidoDomainEntity.builder()
                .idProduto(1L)
                .quantidade(20)
                .build();

        ProdutoDomainEntity produto = ProdutoDomainEntity.builder()
                .idPedido(1L)
                .quantidade(15)
                .preco(10.0)
                .build();

        when(produtoDomainRepository.buscarProdutoPorId(1L)).thenReturn(produto);


        final var exception = assertThrows(IllegalRequestException.class, () -> criarPedidoUseCase.execute(pedido));

        assertEquals("Erro ao criar pedido", exception.getMessage());
        verify(pedidoDomainRepository, never()).criarPedido(any());
    }

    @Test
    public void testExecuteErroAoCriarPedido() {
        PedidoDomainEntity pedido = PedidoDomainEntity.builder()
                .idProduto(1L)
                .quantidade(10)
                .build();

        ProdutoDomainEntity produto = ProdutoDomainEntity.builder()
                .idPedido(1L)
                .quantidade(15)
                .build();

        when(produtoDomainRepository.buscarProdutoPorId(1L)).thenReturn(produto);
        when(pedidoDomainRepository.criarPedido(pedido)).thenThrow(new RuntimeException("Erro ao criar pedido"));

        IllegalRequestException exception = assertThrows(IllegalRequestException.class, () -> criarPedidoUseCase.execute(pedido));

        assertEquals("Erro ao criar pedido", exception.getMessage());
    }

    private ClienteDomainEntity criarClienteDomainEntity() {
        return ClienteDomainEntity.builder()
                .idExterno(UUID.randomUUID())
                .nome("Cliente")
                .endereco("Endereco")
                .telefone("11999999999")
                .email("email@mail.com")
                .build();
    }
}
