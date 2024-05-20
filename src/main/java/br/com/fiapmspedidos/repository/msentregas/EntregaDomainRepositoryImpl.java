package br.com.fiapmspedidos.repository.msentregas;

import br.com.fiapmspedidos.application.exception.BusinessException;
import br.com.fiapmspedidos.domain.entity.EntregaDomainEntity;
import br.com.fiapmspedidos.domain.repository.EntregaDomainRepository;
import org.springframework.stereotype.Service;

@Service
public class EntregaDomainRepositoryImpl implements EntregaDomainRepository {

    private final MsEntregas msEntregas;

    public EntregaDomainRepositoryImpl(MsEntregas msEntregas) {
        this.msEntregas = msEntregas;
    }


    @Override
    public EntregaDomainEntity cadastrarEntrega(EntregaDomainEntity entrega) {

        final var entregaRequest = EntregaRequestDTO.builder()
                .idCliente(entrega.getIdCliete())
                .idPedido(entrega.getIdPedido())
                .enderecoEntrega(entrega.getEnderecoEntrega())
                .build();

        final var entregaResponse = msEntregas.cadastrarEntregas(entregaRequest);


        if (entregaResponse.isEmpty()) {
            throw new BusinessException("Erro ao buscar entrega");
        }

        return EntregaDomainEntity.paraEntidadeDominio(entregaResponse.get());
    }
}
