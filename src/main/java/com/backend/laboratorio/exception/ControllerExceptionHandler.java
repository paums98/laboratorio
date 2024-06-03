package com.backend.laboratorio.exception;


import com.backend.laboratorio.exception.Experimento.ExperimentoNotFoundException;
import com.backend.laboratorio.exception.Tecnica.TecnicaNotFoundException;
import com.backend.laboratorio.exception.Utiliza.RelacionExistenteException;
import com.backend.laboratorio.exception.Utiliza.UtilizaNotFoundException;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.security.SignatureException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ProblemDetail;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.authentication.AccountStatusException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.validation.FieldError;
import org.springframework.web.ErrorResponse;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class ControllerExceptionHandler
{
    private final Logger logger = LoggerFactory.getLogger(ControllerExceptionHandler.class);


    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Response> handlevalidationArgumentErrors(MethodArgumentNotValidException ex) {
        Map<String, String> errores = new HashMap<>();

        ex.getBindingResult().getAllErrors().forEach((error) ->
                {
                    String fieldName = ((FieldError) error).getField();
                    String erroMessage = error.getDefaultMessage();
                    errores.put(fieldName, erroMessage);
                }
        );

        return new ResponseEntity<Response>(Response.validationError(errores), HttpStatus.BAD_REQUEST);
    }

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(ExperimentoNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleNotFoundProductoErrors(ExperimentoNotFoundException ex){

        return new ResponseEntity<ErrorResponse>(
                (ErrorResponse) Response.generalError(HttpStatus.NOT_FOUND.value(), ex.getMessage()),
                HttpStatus.NOT_FOUND
        );

    }

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(TecnicaNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleNotFoundProductoErrors(TecnicaNotFoundException ex){

        return new ResponseEntity<ErrorResponse>(
                (ErrorResponse) Response.generalError(HttpStatus.NOT_FOUND.value(), ex.getMessage()),
                HttpStatus.NOT_FOUND
        );

    }

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(UtilizaNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleNotFoundProductoErrors(UtilizaNotFoundException ex){

        return new ResponseEntity<ErrorResponse>(
                (ErrorResponse) Response.generalError(HttpStatus.NOT_FOUND.value(), ex.getMessage()),
                HttpStatus.NOT_FOUND
        );

    }

    @ResponseStatus(HttpStatus.CONFLICT)
    @ExceptionHandler(RelacionExistenteException.class)
    public ResponseEntity<ErrorResponse> handleRelacionYaExiste(RelacionExistenteException ex) {
        return new ResponseEntity<>(
                (ErrorResponse) Response.generalError(HttpStatus.CONFLICT.value(), ex.getMessage()),
                HttpStatus.CONFLICT
        );
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ProblemDetail> handleSecurityException(Exception exception)
    {
        ProblemDetail errorDetail = null;

        // TODO send this stack trace to an observability tool
        logger.error(Arrays.toString(exception.getStackTrace()));

        if (exception instanceof BadCredentialsException) {
            errorDetail = ProblemDetail.forStatusAndDetail(HttpStatusCode.valueOf(HttpStatus.UNAUTHORIZED.value()),
                                                            exception.getMessage());
            errorDetail.setProperty("description", "The username or password is incorrect");

            return new ResponseEntity<>(errorDetail, HttpStatus.NOT_FOUND);
        }

        if (exception instanceof AccountStatusException) {
            errorDetail = ProblemDetail.forStatusAndDetail(HttpStatusCode.valueOf(HttpStatus.FORBIDDEN.value()),
                                                            exception.getMessage());
            errorDetail.setProperty("description", "The account is locked");

            return new ResponseEntity<>(errorDetail, HttpStatus.FORBIDDEN);
        }

        if (exception instanceof AccessDeniedException) {
            errorDetail = ProblemDetail.forStatusAndDetail(HttpStatusCode.valueOf(HttpStatus.FORBIDDEN.value()),
                                                            exception.getMessage());
            errorDetail.setProperty("description", "You are not authorized to access this resource");

            return new ResponseEntity<>(errorDetail, HttpStatus.FORBIDDEN);
        }

        if (exception instanceof SignatureException) {
            errorDetail = ProblemDetail.forStatusAndDetail(HttpStatusCode.valueOf(HttpStatus.FORBIDDEN.value()),
                                                            exception.getMessage());
            errorDetail.setProperty("description", "The JWT signature is invalid");

            return new ResponseEntity<>(errorDetail, HttpStatus.FORBIDDEN);
        }

        if (exception instanceof ExpiredJwtException) {
            errorDetail = ProblemDetail.forStatusAndDetail(HttpStatusCode.valueOf(HttpStatus.FORBIDDEN.value()),
                                                            exception.getMessage());
            errorDetail.setProperty("description", "The JWT token has expired");

            return new ResponseEntity<>(errorDetail, HttpStatus.FORBIDDEN);
        }

        if (errorDetail == null) {
            errorDetail = ProblemDetail.forStatusAndDetail(HttpStatusCode.valueOf(HttpStatus.FORBIDDEN.value()),
                                                            exception.getMessage());
            errorDetail.setProperty("description", "Unknown internal server error.");
        }

        return new ResponseEntity<>(errorDetail, HttpStatus.INTERNAL_SERVER_ERROR);
    }

}
