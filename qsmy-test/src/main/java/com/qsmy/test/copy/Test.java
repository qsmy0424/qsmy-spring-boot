package com.qsmy.test.copy;

import net.sf.cglib.beans.BeanCopier;

import java.time.LocalDate;

/**
 * @author qsmy
 * @time 2022/9/13
 */
public class Test {

    public static void main(String[] args) {
//        BeanCopier beanCopier = BeanCopier.create(UserDO.class, UserDTO.class, false);
        UserDO userDO = UserDO.builder()
                .id(1L)
                .name("qsmy")
                .gmtCreate(LocalDate.now())
                .gender(1)
                .password("qqqqq")
                .build();
        UserDTO userDTO = new UserDTO();
        BeanCopyUtils.copy(userDO, userDTO);
        System.out.println(userDTO);
    }
}
