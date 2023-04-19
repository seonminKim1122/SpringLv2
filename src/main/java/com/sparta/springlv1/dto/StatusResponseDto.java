package com.sparta.springlv1.dto;


import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public class StatusResponseDto {
    private String msg;
    private HttpStatus status;
}
