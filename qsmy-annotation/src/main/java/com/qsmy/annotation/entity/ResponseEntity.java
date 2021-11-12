package com.qsmy.annotation.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * @author qsmy
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ResponseEntity<T> {

    private Integer code;
    private String msg;
    private T data;
    private Date timestamp;
}
