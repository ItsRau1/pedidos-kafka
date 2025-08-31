package com.poc.pedidos_service.outbound.kafka.mappers;

import com.poc.kafka_schemas.avro.PedidoAvro;
import com.poc.pedidos_service.core.domain.entity.Pedido;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface PedidoMapper {

    PedidoAvro toAvro(Pedido pedido);

}
