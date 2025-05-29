package com.isc.exception;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.isc.dtos.ErrorResponseDto;
import com.isc.execptions.ResponseExecption;

import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.ConstraintViolationException;

@RestControllerAdvice
public class ControllerAdvice {
	@ModelAttribute
    public void setResponseContentType(HttpServletResponse response) {
        response.setContentType(MediaType.APPLICATION_JSON_VALUE);
    }

    // Crear un objeto de respuesta con el mensaje de error y el código de estado

    @ResponseStatus()
    @ExceptionHandler(value = ResponseExecption.class)
    public ResponseEntity<ErrorResponseDto> handlerResponseExecption(ResponseExecption ex) {

        ErrorResponseDto errorResponseDto = new ErrorResponseDto();

        errorResponseDto.setCause(ex.getClass().getName());
        errorResponseDto.setMessage(ex.getMessage());
        errorResponseDto.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.name());
        errorResponseDto.setCode(HttpStatus.INTERNAL_SERVER_ERROR.value());

        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorResponseDto);
    }

    @ResponseStatus()
    @ExceptionHandler(value = NoSuchElementException.class)
    // IllegalArgumentException - if id is null.
    public ResponseEntity<ErrorResponseDto> handlerNoSuchElementException(NoSuchElementException ex) {

        ErrorResponseDto errorResponseDto = new ErrorResponseDto();

        errorResponseDto.setCause(ex.getClass().getName());
        errorResponseDto.setMessage(ex.getMessage());
        errorResponseDto.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.name());
        errorResponseDto.setCode(HttpStatus.INTERNAL_SERVER_ERROR.value());

        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorResponseDto);
    }

    @ResponseStatus(code = HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(value = IllegalArgumentException.class)
    public ResponseEntity<ErrorResponseDto> handlerIllegalArgumentException(IllegalArgumentException ex) {
        // IllegalArgumentException-in case the given entities or one of its entities is null.

        ErrorResponseDto errorResponseDto = new ErrorResponseDto();

        errorResponseDto.setCause(ex.getClass().getName());
        errorResponseDto.setMessage(ex.getMessage());
        errorResponseDto.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.name());
        errorResponseDto.setCode(HttpStatus.INTERNAL_SERVER_ERROR.value());

        return ResponseEntity
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(errorResponseDto);
    }
    
    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<ErrorResponseDto> handleValidationException(MethodArgumentNotValidException ex) {
        ErrorResponseDto errorResponseDto = new ErrorResponseDto();
        errorResponseDto.setCause(ex.getClass().getName());
        errorResponseDto.setMessage(ex.getMessage());
        errorResponseDto.setStatus(HttpStatus.BAD_REQUEST.name());
        errorResponseDto.setCode(HttpStatus.BAD_REQUEST.value());
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(errorResponseDto);
    }

    @ExceptionHandler(ConstraintViolationException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<ErrorResponseDto> handleConstraintViolation(ConstraintViolationException ex) {
    	 ErrorResponseDto errorResponseDto = new ErrorResponseDto();
         errorResponseDto.setCause(ex.getClass().getName());
         errorResponseDto.setMessage("Parameter validation failed");
         errorResponseDto.setStatus(HttpStatus.BAD_REQUEST.name());
         errorResponseDto.setCode(HttpStatus.BAD_REQUEST.value());
        return ResponseEntity.badRequest().body(errorResponseDto);
    }
    
    @ExceptionHandler(DataIntegrityViolationException.class)
    @ResponseStatus(HttpStatus.CONFLICT)
    public ResponseEntity<ErrorResponseDto> handleDataIntegrityViolation(DataIntegrityViolationException ex) {
        ErrorResponseDto error = new ErrorResponseDto();
        error.setCause(ex.getClass().getName());
        error.setMessage("Conflicto con los datos enviados. Posiblemente una clave única o foránea.");
        error.setStatus(HttpStatus.CONFLICT.name());
        error.setCode(HttpStatus.CONFLICT.value());
        return ResponseEntity.status(HttpStatus.CONFLICT).body(error);
    }

    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ResponseEntity<ErrorResponseDto> handleGeneralException(Exception ex) {
        ErrorResponseDto error = new ErrorResponseDto();
        error.setCause(ex.getClass().getName());
        error.setMessage("Ha ocurrido un error inesperado.");
        error.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.name());
        error.setCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(error);
    }

    @ExceptionHandler(RuntimeException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ResponseEntity<ErrorResponseDto> handleRuntimeException(RuntimeException ex) {
        ErrorResponseDto error = new ErrorResponseDto();
        error.setCause(ex.getClass().getName());
        error.setMessage(ex.getMessage() != null ? ex.getMessage() : "Error en tiempo de ejecución.");
        error.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.name());
        error.setCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(error);
    }

}
