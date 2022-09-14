package com.qsmy.test.copy;

import net.sf.cglib.beans.BeanCopier;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author qsmy
 * @time 2022/9/13
 */
public class BeanCopyUtils {

    private BeanCopyUtils() {}

    private static final Map<Class<?>, Map<Class<?>, BeanCopier>> BEAN_COPIER_MAP = new ConcurrentHashMap<>();

    public static <S, T> void copy(S source, T target) {
        Class<?> sourceClass = source.getClass();
        Class<?> targetClass = target.getClass();
        BeanCopier beanCopier = getBeanCopier(sourceClass, targetClass);
        beanCopier.copy(source, target, null);
    }

    private static <S, T> BeanCopier getBeanCopier(Class<S> sourceClass, Class<T> targetClass) {
        Map<Class<?>, BeanCopier> map =
                BEAN_COPIER_MAP.computeIfAbsent(sourceClass, v -> new ConcurrentHashMap<>(4));
        return map.compute(targetClass, (k, v) -> {
            if (v == null) {
                return BeanCopier.create(sourceClass, targetClass, false);
            }
            return v;
        });
    }
}
