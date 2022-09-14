package com.qsmy.mybatis.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

/**
 * @author qsmy
 * @time 2022/9/14
 */

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class User implements Serializable {

    private static final long serialVersionUID = -7559127240683246130L;

    private Long id;
    private String name;
    private String password;
    private String salt;
    private String email;
    private String phoneNumber;
    private Integer status;
    private Date createTime;
    private Date lastLoginTime;
    private Date lastUpdateTime;
}
