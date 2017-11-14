package leetcode;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by ml on 2017/11/14.
 */
public class LongestSubstringWithoutRepeatingCharacters {
    public static void main(String[] args) {
        String test1= "abcabcbb";

        System.out.println(lengthOfLongestSubstring(test1));
        System.out.println(lengthOfLongestSubstring2(test1));
    }

    //dp算法可以表述为，dp[i]等于S[i - 1]里面，从与C[i]相同的后一个字符算起，到C[i]的长度。C[i]为i处的字符。如果S[i - 1]内没有C[i]，显然应该+1的。事实上indexOf(C[i]) = -1，所以后一个字符就是从0开始算，即+1。最后再计算所有dp[i]的最大值，即为maxLength。
    public static int lengthOfLongestSubstring(String s) {
        int maxLength = 0;
        int start = 0;
        for(int i = 0; i < s.length(); i++){
            start = start + s.substring(start, i).indexOf(s.charAt(i)) + 1;
            maxLength = Math.max(maxLength, i - start + 1);
        }
        return maxLength;
    }

    public static int lengthOfLongestSubstring2(String s) {
        int longestLength = 0;
        int currentLength = 0;
        int start = 0;
        Map<Character, Integer> charMap = new HashMap<Character, Integer>();
        for(int i = 0; i < s.length(); i++){
            if(!charMap.containsKey(s.charAt(i)) || charMap.get(s.charAt(i)) < start){
                charMap.put(s.charAt(i), i);
                currentLength++;
            }else{
                start = charMap.get(s.charAt(i)) + 1;
                charMap.put(s.charAt(i), i);
                longestLength = Math.max(longestLength, currentLength);
                currentLength = i - start + 1;
            }
        }
        longestLength = Math.max(longestLength, currentLength); //不能忘记，可能到了结尾也没重复字符，但是要去主动更新最长长度
        return longestLength;
    }
}
