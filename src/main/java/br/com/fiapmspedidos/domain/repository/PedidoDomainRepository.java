package br.com.fiapmspedidos.domain.repository;

import br.com.fiapmspedidos.domain.entity.PedidoDomainEntity;

public interface PedidoDomainRepository {

    PedidoDomainEntity criarPedido(PedidoDomainEntity pedido);
}
