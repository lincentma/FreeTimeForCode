import java.util.Arrays;
import java.util.Scanner;

/**
 * Created by ml on 2017/8/17.
 * 如果一个数列S满足对于所有的合法的i,都有S[i + 1] = S[i] + d, 这里的d也可以是负数和零,我们就称数列S为等差数列。
 * 小易现在有一个长度为n的数列x,小易想把x变为一个等差数列。小易允许在数列上做交换任意两个位置的数值的操作,并且交换操作允许交换多次。但是有些数列通过交换还是不能变成等差数列,小易需要判别一个数列是否能通过交换操作变成等差数列
 */
 
 //暴力求解
 //直接调用Arrays.sort()对数组排序
 //遍历判读是否每一个元素与前一个元素间隔d相等
 //相等true不想等false
 
 //左神方法
 //等差数列求和公式:Sn=(a1+an)n/2 Sn=na1+n(n-1)d/2; (d为公差)
 //遍历数组一次，就可以得到最小值a1，第二小值a2，以及d=a2-a1,数组个数n，数组和sum
 //计算Sn - na1，除以d，判读是否与n(n-1)/2是否相等即可。

public class Main {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        while(in.hasNext()){
            int N = in.nextInt();
            int[] intArr = new int[N];
            for(int i = 0; i < N;i++) {
                intArr[i] = in.nextInt();
            }
            boolean result = boolDengCha(intArr);
            if(result == true){
                System.out.println("Possible");
            }else{
                System.out.println("Impossible");
            }
        }
        in.close();
    }

    private static boolean boolDengCha(int[] intArr) {
        boolean flag = true;
        Arrays.sort(intArr);
        int d = intArr[1] - intArr[0];
        for(int i = 1; i < intArr.length;i++) {
            if (d != intArr[i] - intArr[i - 1]) {
                flag = false;
            }
        }
        return flag;
    }
}
