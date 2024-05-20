package br.com.fiapmspedidos.infrastructure.database.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

import br.com.fiapmspedidos.domain.entity.PedidoDomainEntity;
import br.com.fiapmspedidos.infrastructure.database.entity.PedidoEntity;
import br.com.fiapmspedidos.infrastructure.database.repository.PedidoRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.util.UUID;

@ExtendWith(MockitoExtension.class)
public class PedidoDomainDomainRepositoryImplTest {

    @Mock
    private PedidoRepository pedidoRepository;

    @InjectMocks
    private PedidoDomainDomainRepositoryImpl pedidoDomainDomainRepository;

    @Test
    public void testCriarPedido() {
        // Dados de entrada
        PedidoDomainEntity pedidoDomainEntity = PedidoDomainEntity.builder()
                .idPedido(1L)
                .idProduto(1L)
                .quantidade(2)
                .idExternoCliente(UUID.randomUUID())
                .valorTotal(BigDecimal.TEN)
                .build();

        PedidoEntity pedidoEntity = new PedidoEntity()
                .setId(1L)
                .setIdProduto(1L)
                .setQuantidade(2)
                .setIdCliente(UUID.randomUUID())
                .setValorTotal(BigDecimal.TEN);

        // Mockando o comportamento do repository
        when(pedidoRepository.save(any())).thenReturn(pedidoEntity);

        // Chamando o método a ser testado
        PedidoDomainEntity resultado = pedidoDomainDomainRepository.criarPedido(pedidoDomainEntity);

        // Verificando se o método save foi chamado com os dados corretos
        ArgumentCaptor<PedidoEntity> argumentCaptor = ArgumentCaptor.forClass(PedidoEntity.class);
        verify(pedidoRepository).save(argumentCaptor.capture());
        PedidoEntity entidadeRequest = argumentCaptor.getValue();


        Assertions.assertThat(resultado).isNotNull();
        Assertions.assertThat(entidadeRequest).isNotNull();

    }
}
