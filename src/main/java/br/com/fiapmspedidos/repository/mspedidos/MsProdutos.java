package br.com.fiapmspedidos.repository.mspedidos;

import br.com.fiapmspedidos.domain.entity.ProdutoDomainEntity;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.Optional;

@FeignClient(name = "ms-produtos", url = "${application.msprodutos.host}")
public interface MsProdutos {

    @GetMapping("/produtos/{idProduto}")
    Optional<ProdutoDomainEntity> getProdutoPorId(@PathVariable("idProduto") Long idProduto);

    @PostMapping("/produtos/atualizar/{idProduto}")
    Optional<ProdutoDomainEntity> atualizarProduto(
            @PathVariable("idProduto") Long idProduto, @RequestBody ProdutoAtualizarRequestDTO produto);
}
