package com.qsmy.av.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.List;

/**
 * @author wwhm
 * @time 2023/8/4
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Info {
    private String code;
    private String title;
    private String author;
    private String releaseDate;
    private String type;
    private String duration;
    private String score;
}
