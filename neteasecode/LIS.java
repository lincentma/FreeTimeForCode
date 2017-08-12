/**
 * Created by ml on 2017/8/13.
 */

import java.util.Scanner;

/**
 * 一个序列有N个数：A[1],A[2],…,A[N]，求出最长非降子序列的长度。
 */

public class LIS {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int[] nums = {5, 3, 4, 8, 6, 7, 2};
        int res = calLIS(nums);
        System.out.println(res);

    }

    //    从小到大来去寻找关键的“状态”和“状态转移方程”
    //    求最长非降子序列的长度，子状态是可以理解为是所求字符串的子字符串的最长非降子序列的长度，也就是随着字符串长度的增加
    //    定义d(i)，表示前i个数中以A[i]结尾的最长非降子序列的长度。其中j<i,A[j]<=A[i]
    //    前1个数的LIS长度d(1)=1(序列：5)
    //    前2个数的LIS长度d(2)=1(序列：3；3前面没有比3小的)
    //    前3个数的LIS长度d(3)=2(序列：3，4；4前面有个比它小的3，所以d(3)=d(2)+1)
    //    前4个数的LIS长度d(4)=3(序列：3，4，8；8前面比它小的有3个数，所以 d(4)=max{d(1),d(2),d(3)}+1=3)
    //    状态转移方程：d(i) = max{1, d(j)+1},其中j<i,A[j]<=A[i]
    //    想要求d(i)，就把i前面的各个子序列中， 最后一个数不大于A[i]的序列长度加1，然后取出最大的长度即为d(i)。 当然了，有可能i前面的各个子序列中最后一个数都大于A[i]，那么d(i)=1， 即它自身成为一个长度为1的子序列。
    private static int calLIS(int[] nums) {

        int maxLen = 0;
        int[] dp = new int[nums.length];

        //初始化
        for (int i = 0; i < dp.length; i++) {
            dp[i] = 1;
        }

        for (int i = 0; i < dp.length; i++) {
            for (int j = 0; j < i; ++j) {
                //划重点
                //LIS并没有说连续，所以可以遍历查找元素小于当前元素，然后将遍历到的比当前元素小的元素结尾的长度+1即可，这里也就明白了为什么要把状态d(i)定义为这个样子的原因。
                //逐次覆盖，遍历所有之前的d(i)，后覆盖的长度因为多了元素，是比之前的d(i)要长，满足最长非降子序列的长度定义。
                if (nums[j] <= nums[i] && dp[j] + 1 > dp[i]) {
                    dp[i] = dp[j] + 1;
                }
            }
            //找dp[]中的最大值
            if (dp[i] > maxLen) maxLen = dp[i];
        }

        return maxLen;
    }
}
