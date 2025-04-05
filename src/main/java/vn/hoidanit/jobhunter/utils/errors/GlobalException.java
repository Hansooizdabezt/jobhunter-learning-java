package vn.hoidanit.jobhunter.utils.errors;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalException {

    @ExceptionHandler(value = IdInvalidException.class)
    public ResponseEntity<String> handleBlogAlreadyExistsException(IdInvalidException idException) {
        return ResponseEntity.badRequest().body(idException.getMessage());
    }
}
