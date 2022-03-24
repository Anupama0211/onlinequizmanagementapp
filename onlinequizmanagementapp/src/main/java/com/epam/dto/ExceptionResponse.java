package com.epam.dto;


import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class ExceptionResponse {
    String timestamp;
    String error;
    String status;
    String path;
}
