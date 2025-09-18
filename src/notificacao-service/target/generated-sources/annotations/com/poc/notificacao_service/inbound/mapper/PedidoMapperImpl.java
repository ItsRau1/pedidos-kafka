package com.poc.notificacao_service.inbound.mapper;

import com.poc.kafka_schemas.avro.PedidoAvro;
import com.poc.notificacao_service.core.entity.Pedido;
import java.time.Instant;
import java.time.LocalDateTime;
import java.util.TimeZone;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-09-18T15:28:32-0300",
    comments = "version: 1.6.3, compiler: javac, environment: Java 21.0.7 (Amazon.com Inc.)"
)
@Component
public class PedidoMapperImpl implements PedidoMapper {

    @Override
    public Pedido toEntity(PedidoAvro pedidoAvro) {
        if ( pedidoAvro == null ) {
            return null;
        }

        Pedido.PedidoBuilder pedido = Pedido.builder();

        pedido.id( pedidoAvro.getId() );
        pedido.usuarioId( pedidoAvro.getUsuarioId() );
        pedido.produtoId( pedidoAvro.getProdutoId() );
        pedido.quantidade( pedidoAvro.getQuantidade() );

        pedido.timestamp( LocalDateTime.ofInstant(Instant.ofEpochMilli(pedidoAvro.getTimestamp()), 
                TimeZone.getDefault().toZoneId()) );

        return pedido.build();
    }
}
