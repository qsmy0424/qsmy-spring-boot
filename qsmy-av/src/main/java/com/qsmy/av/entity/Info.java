package com.qsmy.av.entity;

import lombok.Data;

import java.util.List;

/**
 * @author wwhm
 * @time 2023/8/4
 */
@Data
public class Info {
    private String code;
    private String title;
    private String author;
    private String releaseDate;
    private List<String> type;
}
