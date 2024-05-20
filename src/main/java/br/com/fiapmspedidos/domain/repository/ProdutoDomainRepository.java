package br.com.fiapmspedidos.domain.repository;

import br.com.fiapmspedidos.domain.entity.ProdutoDomainEntity;

public interface ProdutoDomainRepository {
    ProdutoDomainEntity buscarProdutoPorId(Long id);
    ProdutoDomainEntity atualizarProduto(ProdutoDomainEntity produto);
}
