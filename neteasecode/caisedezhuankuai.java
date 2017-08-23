import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Created by ml on 2017/8/17.
 * 小易有一些彩色的砖块。每种颜色由一个大写字母表示。各个颜色砖块看起来都完全一样。现在有一个给定的字符串s,s中每个字符代表小易的某个砖块的颜色。小易想把他所有的砖块排成一行。如果最多存在一对不同颜色的相邻砖块,那么这行砖块就很漂亮的。请你帮助小易计算有多少种方式将他所有砖块排成漂亮的一行。(如果两种方式所对应的砖块颜色序列是相同的,那么认为这两种方式是一样的。)
 * 例如: s = "ABAB",那么小易有六种排列的结果:
 * "AABB","ABAB","ABBA","BAAB","BABA","BBAA"
 * 其中只有"AABB"和"BBAA"满足最多只有一对不同颜色的相邻砖块。
 */
 
 //这个题就是就找规律，手写罗列多种情况来找规律
 //桶排序计算每一个元素的个数
 //计算不同元素的种类
 //规律：
 //大于3种的不可能排列，为0
 //2种为两种情况，AAAABBBB或者BBBBAAAA
 //1种为一种情况
 
public class Main {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        while(in.hasNext()){
            String s = in.nextLine();
            char[] strArr = s.toCharArray();
            int res = cal(strArr);
            System.out.println(res);
        }
        in.close();
    }

    private static int cal(char[] strArr) {
        int num = 0;
        int[] arr = new int[26];
        for(int i = 0; i < strArr.length; i++) {
            arr[strArr[i] - 'A']++;
        }
        List<Integer> list = new ArrayList<>();
        for(int i = 0; i < arr.length; i++) {
            if(arr[i] > 0) {
                list.add(i);
            }
        }
        //
        if(list.size() >= 3) {
            num = 0;
        } else if(list.size() == 1) {
            num = 1;
        } else {
            num = 2;
        }
        return num;
    }
}
