package co.com.imdb.api.error;

import co.com.imdb.model.error.exception.BadRequestException;
import co.com.imdb.model.error.exception.ErrorResponse;
import co.com.imdb.model.error.exception.HorarioPeliculaNotFoundException;
import co.com.imdb.model.error.exception.MovieNotFoundException;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.http.ResponseEntity;
//import org.springframework.security.access.AccessDeniedException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.reactive.result.method.annotation.ResponseEntityExceptionHandler;

import java.net.URI;
import java.time.OffsetDateTime;
import java.util.List;

@RestControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {
    @ExceptionHandler(RuntimeException.class)
    protected
    ResponseEntity<ErrorResponse> handleRuntimeException(RuntimeException ex) {
        var errorResponse = buildErrorResponse(HttpStatus.INTERNAL_SERVER_ERROR, ex.getMessage());
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(errorResponse);
    }

    @ExceptionHandler(MovieNotFoundException.class)
    protected ResponseEntity<ErrorResponse> handleEmployeeNotFoundException(MovieNotFoundException ex) {
        var errorResponse = buildErrorResponse(HttpStatus.NOT_FOUND,
                                               String.format("movie with id %s not found", ex.getMovie())
        );
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(errorResponse);
    }

    @ExceptionHandler(HorarioPeliculaNotFoundException.class)
    protected ResponseEntity<ErrorResponse> handleCustomError(HorarioPeliculaNotFoundException ex) {
        var errorResponse = buildErrorResponse(HttpStatus.NOT_FOUND,
                                               String.format("movie with id %s not found", ex.getMovie())
        );
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(errorResponse);
    }

    @ExceptionHandler(BadRequestException.class)
    protected
    ProblemDetail handleBadCurrencyException(RuntimeException ex) {
        var problemDetail = ProblemDetail.forStatusAndDetail(HttpStatus.BAD_REQUEST, ex.getMessage());
        problemDetail.setTitle("invalid currency");
        problemDetail.setProperty("traceId", RandomStringUtils.randomAlphanumeric(10));
        problemDetail.setType(URI.create("http://cedesistemas.edu.co/errors/invalid-currency"));
        problemDetail.setProperty("errors", List.of(ErrorDetails.API_RETORN_BAD_REQUEST));
        return problemDetail;
    }

    /**@ExceptionHandler(AccessDeniedException.class)
    protected ResponseEntity<ErrorResponse> handleAccessDeniedException(AccessDeniedException ex) {
        var errorResponse = buildErrorResponse(HttpStatus.FORBIDDEN, ex.getMessage());
        return ResponseEntity.status(HttpStatus.FORBIDDEN).body(errorResponse);
    }*/

    private static ErrorResponse buildErrorResponse(HttpStatus status, String message) {
        return ErrorResponse.builder()
                .traceId(RandomStringUtils.randomAlphanumeric(10))
                .message(message)
                .timestamp(OffsetDateTime.now())
                .build();
    }
}
