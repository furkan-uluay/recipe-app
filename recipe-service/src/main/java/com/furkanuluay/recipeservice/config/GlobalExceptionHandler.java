package com.furkanuluay.recipeservice.config;

import com.furkanuluay.recipeservice.dto.APIErrorDto;
import com.furkanuluay.recipeservice.exception.CategoryNotFoundException;
import com.furkanuluay.recipeservice.exception.RecipeAlreadyExistException;
import com.furkanuluay.recipeservice.exception.RecipeNotFoundException;
import jakarta.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

  @ExceptionHandler(RecipeAlreadyExistException.class)
  public ResponseEntity<APIErrorDto> handleRecipeAlreadyException(
      RecipeAlreadyExistException ex,
      HttpServletRequest request) {

    log.error(
        "RecipeAlreadyException: {} for {}", ex.getLocalizedMessage(), request.getRequestURI());

    APIErrorDto errorDto =
        APIErrorDto.builder()
            .timestamp(LocalDateTime.now())
            .errorMessage("Recipe already exist with same title ")
            .httpStatus(HttpStatus.CONFLICT)
            .request(request.getRequestURI())
            .requestType(request.getMethod())
            .build();

    return new ResponseEntity<>(errorDto, HttpStatus.CONFLICT);
  }

  @ExceptionHandler(RecipeNotFoundException.class)
  public ResponseEntity<APIErrorDto> handleRecipeNotFoundException(
      RecipeNotFoundException ex, HttpServletRequest request) {
    log.error(
        "RecipeNotFoundException: {} for {}", ex.getLocalizedMessage(), request.getRequestURI());

    APIErrorDto errorDto =
        APIErrorDto.builder()
            .timestamp(LocalDateTime.now())
            .errorMessage("Recipe not found")
            .httpStatus(HttpStatus.NOT_FOUND)
            .request(request.getRequestURI())
            .requestType(request.getMethod())
            .build();

    return new ResponseEntity<>(errorDto, HttpStatus.NOT_FOUND);
  }

  @ExceptionHandler(CategoryNotFoundException.class)
  public ResponseEntity<APIErrorDto> handleCategoryNotFoundException(
      CategoryNotFoundException ex, HttpServletRequest request) {
    log.error(
        "CategoryNotFoundException: {} for {}", ex.getLocalizedMessage(), request.getRequestURI());

    APIErrorDto errorDto =
        APIErrorDto.builder()
            .timestamp(LocalDateTime.now())
            .errorMessage("Category not found")
            .httpStatus(HttpStatus.NOT_FOUND)
            .request(request.getRequestURI())
            .requestType(request.getMethod())
            .build();

    return new ResponseEntity<>(errorDto, HttpStatus.NOT_FOUND);
  }

  @ExceptionHandler(MethodArgumentNotValidException.class)
  public ResponseEntity<APIErrorDto> handleValidationException(
          MethodArgumentNotValidException ex, HttpServletRequest request) {
    log.error("ValidationException: {} for {}", ex.getLocalizedMessage(), request.getRequestURI());

    BindingResult bindingResult = ex.getBindingResult();
    FieldError fieldError = bindingResult.getFieldError();
    String errorMessage = fieldError != null ? fieldError.getDefaultMessage() : "Validation error";

    APIErrorDto errorDto =
            APIErrorDto.builder()
                    .timestamp(LocalDateTime.now())
                    .errorMessage(errorMessage)
                    .httpStatus(HttpStatus.BAD_REQUEST)
                    .request(request.getRequestURI())
                    .requestType(request.getMethod())
                    .build();

    return new ResponseEntity<>(errorDto, HttpStatus.BAD_REQUEST);
  }


  @ExceptionHandler(Exception.class)
  public ResponseEntity<APIErrorDto> handleException(Exception ex, HttpServletRequest request) {
    log.error("Exception: {} for {}", ex.getLocalizedMessage(), request.getRequestURI());
    ex.printStackTrace();

    APIErrorDto errorDto =
        APIErrorDto.builder()
            .timestamp(LocalDateTime.now())
            .errorMessage(ex.getLocalizedMessage())
            .httpStatus(HttpStatus.INTERNAL_SERVER_ERROR)
            .request(request.getRequestURI())
            .requestType(request.getMethod())
            .build();

    return new ResponseEntity<>(errorDto, HttpStatus.INTERNAL_SERVER_ERROR);
  }
}
