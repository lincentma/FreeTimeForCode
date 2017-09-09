

/**
 * Created by ml on 2017/9/9.
 小易有一个长度为N的正整数数列A = {A[1], A[2], A[3]..., A[N]}。
牛博士给小易出了一个难题:
对数列A进行重新排列,使数列A满足所有的A[i] * A[i + 1](1 ≤ i ≤ N - 1)都是4的倍数。
小易现在需要判断一个数列是否可以重排之后满足牛博士的要求。 
输入描述:
输入的第一行为数列的个数t(1 ≤ t ≤ 10),
接下来每两行描述一个数列A,第一行为数列长度n(1 ≤ n ≤ 10^5)
第二行为n个正整数A[i](1 ≤ A[i] ≤ 10^9)


输出描述:
对于每个数列输出一行表示是否可以满足牛博士要求,如果可以输出Yes,否则输出No。

输入例子1:
2
3
1 10 100
4
1 2 3 4

输出例子1:
Yes
No
 */

import java.util.Scanner;

/**
 * Created by ml on 2017/9/9.
 */
public class Main {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        while (in.hasNext()) {
            int t = in.nextInt();
            int[][] res = new int[t][];
            for(int i = 0; i< t; i++) {
                int n = in.nextInt();
                int[] A = new int[n];
                for(int j = 0; j< A.length;j++) {
                    A[j] = in.nextInt();
                }
                res[i] = A;
            }
            for(int k = 0; k < t; k++) {
                System.out.println(cal1(res[k]));
            }
        }
        in.close();
    }

    private static String cal1(int[] voice) {
        boolean flag = false;
        int count4 = 0;
        int count2 = 0;
        int count1 = 0;
        for(int i = 0; i < voice.length; i++) {
            if(voice[i] % 4 == 0) {
                count4++;
            } else if(voice[i] % 2 == 0) {
                count2++;
            }else {
                count1++;
            }
        }
        if(count4 + count2 == voice.length) {
            flag = true;
        }
        if(count4 >= count1 - 1) {
            flag = true;
        }
        String res = flag == true ? "Yes" : "No";
        return res;
    }
}
