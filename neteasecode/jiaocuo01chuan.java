import java.util.Scanner;

/**
 * Created by ml on 2017/8/17.
 * 如果一个01串任意两个相邻位置的字符都是不一样的,我们就叫这个01串为交错01串。例如: "1","10101","0101010"都是交错01串。
 * 小易现在有一个01串s,小易想找出一个最长的连续子串,并且这个子串是一个交错01串。小易需要你帮帮忙求出最长的这样的子串的长度是多少。
 * 输入描述:
 * 输入包括字符串s,s的长度length(1 ≤ length ≤ 50),字符串中只包含'0'和'1'
 * <p>
 * <p>
 * 输出描述:
 * 输出一个整数,表示最长的满足要求的子串长度。
 * <p>
 * 输入例子1:
 * 111101111
 * <p>
 * 输出例子1:
 * 3
 */

//这个最开始以为是动态规划
//做的时候发现就是遍历
//动态规划还需要加强
public class Main {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        while (in.hasNext()) {
            String chuan = in.nextLine();
            int result = findChuan(chuan);
            System.out.println(result);
        }
        in.close();
    }

    private static int findChuan(String chuan) {
        char[] str = chuan.toCharArray();
        int[] dp = new int[str.length];

        //init
        //dp[i]表示以数组中i位置元素结尾的最长子串的长度
        dp[0] = 0;
        int max = 0;
        for(int i = 1; i < str.length; i++) {
           dp[i] = (str[i] != str[i - 1]) ? dp[i - 1] + 1 : 0;
           if(dp[i] > max) {
               max = dp[i];
           }
        }
        return max + 1;
    }

}
