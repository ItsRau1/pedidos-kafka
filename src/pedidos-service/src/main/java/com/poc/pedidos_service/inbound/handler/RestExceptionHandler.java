package com.poc.pedidos_service.inbound.handler;

import com.poc.pedidos_service.inbound.dto.ErrorDTO;
import com.poc.pedidos_service.inbound.dto.ResponseErrorDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.common.KafkaException;
import org.apache.kafka.common.errors.SerializationException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.List;
import java.util.concurrent.CompletionException;
import java.util.concurrent.TimeoutException;

@Slf4j
@RestControllerAdvice
@RequiredArgsConstructor
public class RestExceptionHandler {

	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<ResponseErrorDTO> handleMethodArgumentNotValidException(MethodArgumentNotValidException ex) {
		var message = "Erro ao processar entidade.";
		var status = 400;
		log.error(message, ex);
		var errors = ex.getBindingResult()
			.getFieldErrors()
			.stream()
			.map(fieldError -> new ErrorDTO(fieldError.getDefaultMessage()))
			.toList();
		return ResponseEntity.status(status).body(new ResponseErrorDTO(status, message, errors));
	}

	@ExceptionHandler(CompletionException.class)
	public ResponseEntity<ResponseErrorDTO> handleCompletionException(CompletionException ex) {
		var message = "Erro ao se conectar com a fila.";
		var status = 502;
		log.error(message, ex);
		var errors = List.of(new ErrorDTO(ex.getMessage()));
		return ResponseEntity.status(status).body(new ResponseErrorDTO(status, message, errors));
	}

}
