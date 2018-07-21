package com.example.springbootdemo.system.exception;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * @author fly
 * 自定义异常
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class CustomException extends RuntimeException {
    private Integer code;
    private String message;

}