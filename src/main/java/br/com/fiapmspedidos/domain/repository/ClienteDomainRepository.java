package br.com.fiapmspedidos.domain.repository;

import br.com.fiapmspedidos.domain.entity.ClienteDomainEntity;

public interface ClienteDomainRepository {
    ClienteDomainEntity buscarClientePorId(ClienteDomainEntity cliente);
}
