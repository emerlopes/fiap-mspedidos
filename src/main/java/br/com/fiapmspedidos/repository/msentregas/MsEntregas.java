package br.com.fiapmspedidos.repository.msentregas;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.Optional;


@FeignClient(name = "ms-entregas", url = "http://localhost:8083")
public interface MsEntregas {

    @GetMapping("/entregas")
    Optional<EntregaResponseDTO> cadastrarEntregas(
            @RequestBody EntregaRequestDTO entregaRequestDTO);

}
