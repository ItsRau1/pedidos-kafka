package com.poc.fraude_service.core.usecase;

import com.poc.fraude_service.core.domain.entity.Pedido;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.Objects;

@Slf4j
@Component
@RequiredArgsConstructor
public class ProcessarPedido {

    private final String LOG_PREFIX = "[PROCESSAR-PEDIDO] - ";

    public void execute(Pedido pedido) {
        log.info("{}Iniciando processamento de pedido. [PEDIDO: {}]", LOG_PREFIX, pedido.getId());
        var isFraude = verificarFraude(pedido);
        if(isFraude) {
            log.info("{}Pedido identificado como fraude. [PEDIDO: {}]", LOG_PREFIX, pedido.getId());
            // TODO: Adicionar envio para fila pedidosInvalidos
        }
        log.info("{}Pedido valido. [PEDIDO: {}]", LOG_PREFIX, pedido.getId());
        // TODO: Adicionar envio para fila pedidosValidos
        // TODO: Adicionar testes
    }

    private Boolean verificarFraude(Pedido pedido) {
        log.info("{}Verificando se pedido é fraude. [PEDIDO: {}]", LOG_PREFIX, pedido.getId());
        Objects.requireNonNull(pedido.getId());
        return isPar(LocalDateTime.now().getNano());
    }

    private Boolean isPar(int value) {
        return value % 2 == 0;
    }

}

