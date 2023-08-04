package com.qsmy.test.algorithm;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/**
 * @author wwhm
 * @time 2023/7/31
 */
class Solution {
    public static List<List<Integer>> combinationSum2(int[] candidates, int target) {
        List<List<Integer>> ans = new ArrayList<>();
        if (candidates == null || candidates.length == 0) return ans;

        Arrays.sort(candidates);

        dfs(candidates, 0, target, new LinkedList<>(), ans);

        return ans;
    }

    // candidates[index ...]自由选择，搞定rest
    // index之前的路径不需要操心，已经放在了path中
    // 最终可行的路径放入ans
    // 每个位置的数字不能重复使用，说明这是一个01背包问题，只有要和不要两种选择
    // 因为题目规定解集不能包含重复的组合
    // 【去重处理】：先对数组排序；DFS递归尝试时，如果选择不要当前数字时，直接跳过所有与之重复的数字，去下一个不同的数字位置上继续尝试
    private static void dfs(int[] candidates, int index, int rest,
                            LinkedList<Integer> path, List<List<Integer>> ans) {
        if (rest == 0) { // 找到了一条可行路径，收集答案
            ans.add(new ArrayList<>(path));
            return;
        }
        // 中途若发现rest < 0，直接【剪枝】，返回
        // 如果rest > 0，但是已经没有数可选了，说明当前尝试的路径无效，返回
        if (rest < 0 || index >= candidates.length) {
            return;
        }

        int candidate = candidates[index]; // 当前数
        // 01背包：

        // 1）选择要当前数：
        path.addLast(candidate);
        dfs(candidates, index+1, rest - candidate, path, ans);
        path.removeLast();

        // 2）选择不要当前数：直接跳过所有与当前数重复的数字，找到下一个与当前数不重复数字的位置，继续尝试
        while (index < candidates.length && candidates[index] == candidate) index++;

        // 【剪枝】：如果发现下一个不同的数字已经大于rest了，说明后续没有可行方案了，直接返回就行了
        if (index < candidates.length && candidates[index] > rest) return;
        // 在不要当前candidate的情况下，继续尝试
        dfs(candidates, index, rest, path, ans);
    }
}