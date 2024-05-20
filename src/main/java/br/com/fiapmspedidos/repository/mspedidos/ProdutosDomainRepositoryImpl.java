package br.com.fiapmspedidos.repository.mspedidos;

import br.com.fiapmspedidos.application.exception.BusinessException;
import br.com.fiapmspedidos.application.exception.FeignClientException;
import br.com.fiapmspedidos.domain.entity.ProdutoDomainEntity;
import br.com.fiapmspedidos.domain.repository.ProdutoDomainRepository;
import org.springframework.stereotype.Service;

@Service
public class ProdutosDomainRepositoryImpl implements ProdutoDomainRepository {

    private final MsProdutos msProdutos;

    public ProdutosDomainRepositoryImpl(
            final MsProdutos msProdutos
    ) {
        this.msProdutos = msProdutos;
    }

    @Override
    public ProdutoDomainEntity buscarProdutoPorId(Long id) {
        try {
            final var produto = msProdutos.getProdutoPorId(id);

            if (produto.isEmpty()) {
                throw new BusinessException("Produto não encontrado");
            }

            return produto.get();

        } catch (Exception e) {
            throw new FeignClientException(e.getMessage());
        }
    }

    @Override
    public ProdutoDomainEntity atualizarProduto(ProdutoDomainEntity produto) {

        final var request = new ProdutoAtualizarRequestDTO(produto.getQuantidade());

        final var response = msProdutos.atualizarProduto(produto.getIdPedido(), request);

        if (response.isEmpty()) {
            throw new BusinessException("Erro ao atualizar produto");
        }

        return response.get();
    }
}
