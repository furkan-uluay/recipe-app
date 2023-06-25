package com.furkanuluay.recipeservice.dto;

import java.time.LocalDateTime;
import lombok.Builder;
import lombok.Data;
import org.springframework.http.HttpStatus;

/**
 * @author Furkan Uluay
 */
@Builder
@Data
public class APIErrorDto {

    private LocalDateTime timestamp;
    private String errorMessage;

    private HttpStatus httpStatus;

    private String request;

    private String requestType;


}