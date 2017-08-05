/*
对于一个字符串,请设计一个高效算法，找到字符串的最长无重复字符的子串长度。

给定一个字符串A及它的长度n，请返回它的最长无重复字符子串长度。保证A中字符全部为小写英文字符，且长度小于等于500。

测试样例：
"aabcb",5
返回：3
*/

import java.util.*;

public class DistinctSubstring {
    public int longestSubstring(String A, int n) {
        // write code here
        if (A == null || A.length() == 0) {
            return 0;
        }
        // 记录上一次字符出现位置
        int[] map = new int[256];
        for (int i = 0; i < map.length; i++) {
            map[i] = -1;
        }
        // 初始化
        int pre = -1;  // 记录当前无重复子串的起始位置，
        int max = 0;
        // 状态转移
        for (int i = 0; i < A.length(); i++) {
            // 当上次字符出现位置，在无重复子串之后时，更新。
            if (pre < map[A.charAt(i)]) {
                pre = map[A.charAt(i)];
            }
            // 最大的子串长度的计算，当前结尾的无重复子串长度为 i - pre
            max = Math.max(max, i - pre);
            map[A.charAt(i)] = i;
        }
        return max;
    }
}
