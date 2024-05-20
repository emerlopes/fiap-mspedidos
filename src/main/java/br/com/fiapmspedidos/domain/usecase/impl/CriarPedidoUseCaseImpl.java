package br.com.fiapmspedidos.domain.usecase.impl;

import br.com.fiapmspedidos.application.exception.BusinessException;
import br.com.fiapmspedidos.application.exception.IllegalRequestException;
import br.com.fiapmspedidos.domain.entity.ClienteDomainEntity;
import br.com.fiapmspedidos.domain.entity.EntregaDomainEntity;
import br.com.fiapmspedidos.domain.entity.PedidoDomainEntity;
import br.com.fiapmspedidos.domain.entity.ProdutoDomainEntity;
import br.com.fiapmspedidos.domain.repository.ClienteDomainRepository;
import br.com.fiapmspedidos.domain.repository.PedidoDomainRepository;
import br.com.fiapmspedidos.domain.repository.ProdutoDomainRepository;
import br.com.fiapmspedidos.domain.usecase.CriarPedidoUseCase;
import br.com.fiapmspedidos.repository.msentregas.EntregaDomainRepositoryImpl;
import br.com.fiapmspedidos.repository.msentregas.EntregaRequestDTO;
import org.slf4j.Logger;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class CriarPedidoUseCaseImpl implements CriarPedidoUseCase {

    private final Logger logger;
    private final PedidoDomainRepository pedidoDomainRepository;
    private final ProdutoDomainRepository produtoDomainRepository;
    private final ClienteDomainRepository clienteDomainRepository;
    private final EntregaDomainRepositoryImpl entregaDomainRepository;

    public CriarPedidoUseCaseImpl(
            final Logger logger,
            final PedidoDomainRepository pedidoDomainRepository,
            final ProdutoDomainRepository produtoDomainRepository,
            final ClienteDomainRepository clienteDomainRepository,
            final EntregaDomainRepositoryImpl entregaDomainRepository
    ) {
        this.logger = logger;
        this.pedidoDomainRepository = pedidoDomainRepository;
        this.produtoDomainRepository = produtoDomainRepository;
        this.clienteDomainRepository = clienteDomainRepository;
        this.entregaDomainRepository = entregaDomainRepository;
    }

    @Override
    public PedidoDomainEntity execute(PedidoDomainEntity pedido) {

        try {
            final var idProduto = pedido.getIdProduto();
            final var quantidadeSolicitada = pedido.getQuantidade();
            final var produto = produtoDomainRepository.buscarProdutoPorId(idProduto);
            final var quantidadeEstoque = produto.getQuantidade();
            final var preco = produto.getPreco();
            final var valorTotal = BigDecimal.valueOf(quantidadeSolicitada * preco);

            logger.info("Quantidade solicitada: {}", quantidadeSolicitada);
            logger.info("Quantidade em estoque: {}", quantidadeEstoque);

            if (quantidadeEstoque < quantidadeSolicitada) {
                logger.error("Quantidade em estoque insuficiente");
                throw new BusinessException("Quantidade em estoque insuficiente");
            }

            final var produtoDomainEntity = ProdutoDomainEntity.builder()
                    .idPedido(idProduto)
                    .quantidade(quantidadeSolicitada)
                    .valorTotal(valorTotal)
                    .build();


            final var produtoAtualizado = produtoDomainRepository.atualizarProduto(produtoDomainEntity);
            logger.info("Produto atualizado: {}", produtoAtualizado);


            final var clienteDomainEntity = ClienteDomainEntity.builder()
                    .idExterno(pedido.getIdExternoCliente())
                    .build();


            final var pedidoResponse = pedidoDomainRepository.criarPedido(pedido);
            final var clienteResponse = clienteDomainRepository.buscarClientePorId(clienteDomainEntity);
            final var entregaDomainEntity = EntregaDomainEntity.paraEntidadeDominio(clienteResponse, pedidoResponse);
            final var entregaResponse = entregaDomainRepository.cadastrarEntrega(entregaDomainEntity);

            pedido.setIdPedido(pedidoResponse.getIdPedido());
            pedido.setValorTotal(valorTotal);
            pedido.setEnderecoEntrega(entregaResponse.getEnderecoEntrega());
            pedido.setDataEntrega(entregaResponse.getDataEntrega());
            return pedido;

        } catch (Exception e) {
            logger.error("Erro ao criar pedido: {}", e.getMessage());
            throw new IllegalRequestException("Erro ao criar pedido");
        }
    }
}
