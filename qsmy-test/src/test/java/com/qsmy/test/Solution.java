package com.qsmy.test;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * @author qsmy
 */
class Solution {
    public static int[] twoSum(int[] nums, int target) {
        int[] result = new int[2];
        Map<Integer, Integer> map = new HashMap<>(nums.length);
        for (int i = 0, temp; i < nums.length; i++) {
            temp = target - nums[i];
            if (map.containsKey(temp)) {
                result[0] = map.get(temp);
                result[1] = i;
                break;
            }
            map.put(nums[i], i);
        }
        return result;
    }

    public static boolean containsDuplicate(int[] nums) {
        Set<Integer> set = new HashSet<>();
        for (int num : nums) {
            if (!set.add(num)) {
                return true;
            }
        }
        return false;
    }

    public static int findLHS(int[] nums) {
        Map<Integer, Integer> map = new HashMap<>(nums.length);
        for (int num : nums) {
            map.merge(num, 1, Integer::sum);
        }
        int size = 0;
        for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
            if (map.containsKey(entry.getKey() + 1)) {
                size = Math.max(entry.getValue() + map.get(entry.getKey() + 1), size);
            }
        }
        return size;
    }



    public static void main(String[] args) {
        int[] arr = new int[]{1,1,1,1};
        System.out.println(findLHS(arr));
    }
}