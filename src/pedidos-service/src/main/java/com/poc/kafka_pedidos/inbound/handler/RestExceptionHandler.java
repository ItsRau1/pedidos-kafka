package com.poc.kafka_pedidos.inbound.handler;

import com.poc.kafka_pedidos.inbound.dto.ErrorDTO;
import com.poc.kafka_pedidos.inbound.dto.ResponseErrorDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

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

}
