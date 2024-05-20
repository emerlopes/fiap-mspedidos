package br.com.fiapmspedidos.repository.msclientes;

import br.com.fiapmspedidos.application.exception.BusinessException;
import br.com.fiapmspedidos.domain.entity.ClienteDomainEntity;
import br.com.fiapmspedidos.domain.repository.ClienteDomainRepository;
import org.springframework.stereotype.Service;

@Service
public class ClienteDomainRepositoryImpl implements ClienteDomainRepository {

    private final MsCliente msCliente;

    public ClienteDomainRepositoryImpl(MsCliente msCliente) {
        this.msCliente = msCliente;
    }

    @Override
    public ClienteDomainEntity buscarClientePorId(ClienteDomainEntity cliente) {
        final var idCliente = cliente.getIdExterno();

        final var response = msCliente.getClientePorIds(
                "correlation-id",
                "flow-id",
                "application/json",
                idCliente);

        if (response.isEmpty()) {
            throw new BusinessException("Cliente não encontrado");
        }
        return ClienteDomainEntity.paraEntidadeDominio(response.get());
    }
}
