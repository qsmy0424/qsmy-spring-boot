package com.qsmy.annotation.config;

import cn.hutool.json.JSONUtil;
import com.qsmy.annotation.entity.ResponseEntity;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.lang.NonNull;
import org.springframework.lang.Nullable;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

import java.util.Date;

/**
 * @author qsmy
 */
@Slf4j
@ControllerAdvice
public class CustomResponse implements ResponseBodyAdvice<Object> {
    @Override
    public boolean supports(@NonNull MethodParameter returnType, @NonNull Class<? extends HttpMessageConverter<?>> converterType) {
        // return returnType.getGenericParameterType() != ResponseEntity.class;
        return true;
    }

    @Override
    public Object beforeBodyWrite(
            @Nullable Object body,
            @NonNull MethodParameter returnType,
            @NonNull MediaType selectedContentType,
            @NonNull Class<? extends HttpMessageConverter<?>> selectedConverterType,
            @NonNull ServerHttpRequest request,
            @NonNull ServerHttpResponse response
    ) {
        if (body == null || body instanceof ResponseEntity) {
            if (body != null) {
                ((ResponseEntity<?>) body).setMsg("aaaaaa");
            }
            return body;
        }
        ResponseEntity<Object> responseEntity = ResponseEntity.builder()
                .code(200)
                .msg("123456")
                .timestamp(new Date()).build();

        if (body instanceof String) {
            responseEntity.setMsg(body.toString());
            return JSONUtil.toJsonStr(responseEntity);
        }

        return responseEntity;
    }
}
