package com.project.geomin.test;
public class FindMinimumInRotatedSortedArray {
    public int findMin(int[] nums) { // 사용자가 작성한 코드

        if (nums == null || nums.length == 0) return -1;

        int left = 0;
        int right = nums.length - 1;

        while (left <= right) {
            if (nums[left] <= nums[right]) return nums[left];

            int m = (left + right) / 2;

            if (nums[m] >= nums[left]) left = m + 1;
            else right = m;
        }

        return -1;
    }
}