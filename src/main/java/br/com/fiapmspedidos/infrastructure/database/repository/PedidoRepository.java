package br.com.fiapmspedidos.infrastructure.database.repository;

import br.com.fiapmspedidos.infrastructure.database.entity.PedidoEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PedidoRepository extends JpaRepository<PedidoEntity, Long> {
}
