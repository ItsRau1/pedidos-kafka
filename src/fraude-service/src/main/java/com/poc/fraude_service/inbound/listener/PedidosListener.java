package com.poc.fraude_service.inbound.listener;

import com.poc.kafka_schemas.avro.PedidoAvro;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class PedidosListener {

    @KafkaListener(topics = "${kafka.topic.pedidos}", groupId = "${spring.kafka.consumer.group-id}")
    public void processarPedido(@Payload PedidoAvro pedido,
                                @Header(KafkaHeaders.RECEIVED_PARTITION) int partition,
                                @Header(KafkaHeaders.OFFSET) long offset) {

        log.info("Mensagem recebida do tópico de pedidos!");
        log.info("Processando pedido ID: {}", pedido.getId());
        log.info("Usuario ID: {}", pedido.getUsuarioId());
        log.info("ProdutoId: {}", pedido.getProdutoId());
        log.info("Quantidade: {}", pedido.getQuantidade());
        log.info("Partição: {}, Offset: {}", partition, offset);
    }

}
