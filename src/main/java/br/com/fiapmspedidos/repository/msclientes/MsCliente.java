package br.com.fiapmspedidos.repository.msclientes;

import br.com.fiapmspedidos.domain.entity.ProdutoDomainEntity;
import br.com.fiapmspedidos.repository.mspedidos.ProdutoAtualizarRequestDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;
import java.util.UUID;

@FeignClient(name = "ms-clientes", url = "http://localhost:8080")
public interface MsCliente {

    @GetMapping("/clientes/{idCliente}")
    Optional<ClienteResponseDTO> getClientePorIds(
            @RequestHeader("correlation-id") String correlationId,
            @RequestHeader("flow-id") String flowId,
            @RequestHeader("Content-Type") String contentType,
            @PathVariable("idCliente") UUID idCliente);

}
