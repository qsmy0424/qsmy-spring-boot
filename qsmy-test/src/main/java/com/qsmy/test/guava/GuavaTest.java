package com.qsmy.test.guava;

import com.google.common.collect.*;
import org.apache.commons.lang3.StringUtils;
import org.checkerframework.checker.nullness.qual.Nullable;

import java.util.*;
import java.util.stream.Collectors;

/**
 * @author qsmy
 */
public class GuavaTest {

    public static void main(String[] args) {
        List<Integer> list = Lists.newArrayList();
        List<Integer> list1 = Lists.newArrayList(1, 2, 3);
        System.out.println(list1);
        // 反转list
        System.out.println(Lists.reverse(list1));

        // 一个key映射多个value的map
        Multimap<String, Integer> map = ArrayListMultimap.create();
        map.put("int", 1);
        map.put("int", 2);
        System.out.println(map);

        System.out.println(map.get("int"));
        Map<String, Collection<Integer>> stringCollectionMap = map.asMap();
        System.out.println(stringCollectionMap);


        // BiMap    value也不能重复的HashMap
        BiMap<String, String> hashBiMap = HashBiMap.create();
        hashBiMap.put("qsmy", "wwhm");
        hashBiMap.forcePut("a", "wwhm");    // 输出{a=wwhm}

        System.out.println(hashBiMap);
        // 既然value不能重复，何不实现个翻转key/value的方法
        BiMap<String, String> inverse = hashBiMap.inverse();
        System.out.println(inverse);    // 输出{wwhm=a}


        // Table 两个key的HashMap
        HashBasedTable<Integer, String, String> hashBasedTable = HashBasedTable.create();
        hashBasedTable.put(19, "男", "qsmy");
        hashBasedTable.put(20, "男", "wwhm");
        hashBasedTable.put(20, "女", "wwhm1");
        // 查看行数据
        System.out.println(hashBasedTable.row(20)); // 输出：{男=wwhm, 女=wwhm1}
        System.out.println(hashBasedTable); // 输出：{19={男=qsmy}, 20={男=wwhm, 女=wwhm1}}

        // 查看列数据
        System.out.println(hashBasedTable.column("男")); // 输出：{19=qsmy, 20=wwhm}

        //
        Multiset<String> multiset = HashMultiset.create();
        multiset.add("apple");
        multiset.add("apple");
        multiset.add("apple");
        multiset.add("orange");

        // 计数
        System.out.println(multiset.count("apple")); // 输出 3

        // 查看去重的元素
        System.out.println(multiset.elementSet()); // 输出：[orange, apple]

        // 查看没有去重的元素
        multiset.forEach(System.out::println);
    }
}
