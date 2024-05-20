package br.com.fiapmspedidos.application.entrypoint.rest;

import br.com.fiapmspedidos.application.entrypoint.rest.dto.PedidoRequestDTO;
import br.com.fiapmspedidos.application.entrypoint.rest.dto.PedidoResponseDTO;
import br.com.fiapmspedidos.domain.entity.PedidoDomainEntity;
import br.com.fiapmspedidos.domain.usecase.CriarPedidoUseCase;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/pedidos")
public class PedidoController {

    private final CriarPedidoUseCase criarPedidoUseCase;

    public PedidoController(CriarPedidoUseCase criarPedidoUseCase) {
        this.criarPedidoUseCase = criarPedidoUseCase;
    }

    @PostMapping
    public ResponseEntity<PedidoResponseDTO> criarPedido(
            @RequestBody PedidoRequestDTO pedido
    ) {
        final var entidadeDomainRequest = PedidoDomainEntity.paraEntidadeDominio(pedido);
        final var entidadeResponse = criarPedidoUseCase.execute(entidadeDomainRequest);

        return ResponseEntity.status(HttpStatus.CREATED).body(PedidoDomainEntity.paraDTO(entidadeResponse));
    }
}
