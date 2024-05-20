package br.com.fiapmspedidos.infrastructure.database.service;

import br.com.fiapmspedidos.domain.entity.PedidoDomainEntity;
import br.com.fiapmspedidos.domain.repository.PedidoDomainRepository;
import br.com.fiapmspedidos.infrastructure.database.repository.PedidoRepository;
import org.springframework.stereotype.Service;

@Service
public class PedidoDomainDomainRepositoryImpl implements PedidoDomainRepository {

    private final PedidoRepository pedidoRepository;

    public PedidoDomainDomainRepositoryImpl(
            final PedidoRepository pedidoRepository
    ) {
        this.pedidoRepository = pedidoRepository;
    }

    @Override
    public PedidoDomainEntity criarPedido(PedidoDomainEntity pedido) {
        final var entidadeRequest = PedidoDomainEntity.paraEntidade(pedido);
        final var entidadeResponse = pedidoRepository.save(entidadeRequest);
        entidadeResponse.setValorTotal(pedido.getValorTotal());

        return PedidoDomainEntity.paraEntidadeDominio(entidadeResponse);

    }
}
