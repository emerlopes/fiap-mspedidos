package br.com.fiapmspedidos.repository.mspedidos;

import br.com.fiapmspedidos.domain.entity.ProdutoDomainEntity;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class ProdutosDomainRepositoryImplTest {

    @InjectMocks
    ProdutosDomainRepositoryImpl produtosDomainRepository;

    @Mock
    MsProdutos msProdutos;

    @Test
    void deveBuscarProdutoPorId() {
        // Arrange
        var id = 1L;
        var produto = ProdutoDomainEntity.builder()
                .idPedido(id)
                .nome("Produto 1")
                .descricao("Descrição do produto 1")
                .preco(10.0)
                .quantidade(10)
                .build();

        Mockito.when(msProdutos.getProdutoPorId(id)).thenReturn(Optional.of(produto));

        // Act
        var result = produtosDomainRepository.buscarProdutoPorId(id);

        // Assert
        Assertions.assertThat(result).isNotNull();
    }

}