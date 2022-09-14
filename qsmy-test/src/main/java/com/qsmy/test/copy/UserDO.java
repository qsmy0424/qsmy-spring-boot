package com.qsmy.test.copy;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

/**
 * @author qsmy
 * @time 2022/9/13
 */

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class UserDO {

    private Long id;

    private String name;

    private Integer gender;

    private String password;

    private LocalDate gmtCreate;

    private LocalDate gmtModified;
}