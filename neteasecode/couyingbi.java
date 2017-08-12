import java.util.Scanner;

/**
 * Created by ml on 2017/8/12.
 */
 
 
 /**
 * 如果我们有面值为1元、3元和5元的硬币若干枚，如何用最少的硬币凑够n元？
 */
 
public class couyingbi {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        int[] coins = {1, 3, 5};
        int res = calCoin(n, coins);
        System.out.println(res);

    }

    private static int calCoin(int n, int[] coins) {
        //init
        int[] dp = new int[n + 1];
        dp[0] = 0;
        //i表示当前金额
        for (int i = 1; i <= n; i++) {
            dp[i] = Integer.MAX_VALUE;// 初始化数组中的每个值都是最大的整数
            //对于当前状态：可以拿的硬币的要求是，硬币大小不超过总的钱
            //对于当前每一次拿到硬币的情况
            //去寻找凑够当前金额 - 当前所拿硬币的最优解，加上本次拿的一个硬币，构成当前的最优解
            //然后遍历所有的情况取最小值
            for (int j = 0; j < coins.length; j++) {
                if (i >= coins[j] && dp[i] > dp[i - coins[j]] + 1) {
                    dp[i] = dp[i - coins[j]] + 1;
                }
            }
//            System.out.println(dp[i]);

        }
        //输出最后结果a
        return dp[n];
    }
}
