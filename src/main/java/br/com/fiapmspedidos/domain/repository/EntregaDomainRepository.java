package br.com.fiapmspedidos.domain.repository;

import br.com.fiapmspedidos.domain.entity.EntregaDomainEntity;

public interface EntregaDomainRepository {
    EntregaDomainEntity cadastrarEntrega(EntregaDomainEntity entrega);
}
