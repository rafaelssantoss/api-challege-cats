package br.com.project.animals.api.advice;

import br.com.project.animals.domain.BaseResponse;
import br.com.project.animals.exception.CatException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ExceptionController {

    @ExceptionHandler(CatException.NotFound.class)
    public ResponseEntity<BaseResponse> notFound(RuntimeException exception) {
        return new ResponseEntity(BaseResponse.builder()
                .message(exception.getMessage())
                .build(),
                HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(CatException.BadRequest.class)
    public ResponseEntity<BaseResponse> badRequest(RuntimeException exception) {
        return new ResponseEntity(BaseResponse.builder()
                .message(exception.getMessage())
                .build(),
                HttpStatus.BAD_REQUEST);
    }
}