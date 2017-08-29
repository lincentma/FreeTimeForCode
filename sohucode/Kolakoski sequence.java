//要求：给定一数组，输出其构造的Kolakoski序列的前n项。

import java.util.ArrayList;
import java.util.Scanner;

/**
 * Created by ml on 2017/8/28.
 */
public class Kolakoski {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        while (in.hasNext()) {
            int n = in.nextInt();
            int m = in.nextInt();
            int[] arr = new int[m];
            for(int i = 0; i < arr.length; i++) {
                arr[i] = in.nextInt();
            }
            int[] res = generate(arr, n);
//            for(int j = 0; j < n; j++) {
//                System.out.println(res[j]);
//            }
            solution(arr,n);
        }
        in.close();
    }

    private static int[] generate(int[] arr, int n) {
        //res为输出数组
        //设置为n溢出
        int[] res = new int[n + 1];
        //
        int arrLength = arr.length;

        //规律为相邻相同数字长度为数组的前面部分

        //相同数字长度
        int lenIndex = 1;
        //结果数组下标
        int resIndex = 0;
        //输入数组下标
        int arrIndex = 0;
        //记录计数
        int count = 0;
        //结果第一个元素为输入数组第一个元素
        res[0] = arr[0];

        while(resIndex < n){
            if(lenIndex < res[count]){
                //相同元素的长度为前一个元素的值，循环赋值相同的元素
                res[resIndex + 1] = res[resIndex];
                //lenIndex为1因为在不同元素的时候已经赋值
                lenIndex++;
                resIndex++;
            } else {
                //不同元素，输入数组元素不断交替
                //重置相同元素长度下标
                lenIndex = 1;
                //交替输入数组元素
                arrIndex++;
                //保证元素循环
                arrIndex %= arrLength;
                res[resIndex + 1] = arr[arrIndex];
                resIndex++;
                count++;
            }

        }
        return res;
    }


    //另外一种思路
    //via：http://blog.csdn.net/u011489043/article/details/77673843
    public static void solution(int[] array, int n) {
        int m = array.length;
        ArrayList<Integer> res = new ArrayList<>();
        int k = 0;// 用于标志array数组索引
        int count = 0;// 用于计数，控制循环次数
        while (count < n) {
            if (k == m)// 当数组里的数字都已经出现过一遍时，需要从头再开始
                k = 0;
            res.add(array[k]);// 每次先将第一个不同的数添加
            // System.out.println("" + res.get(count));
            // 关键就是控制下面循环添加的次数，因为该序列的数字，本身就是相邻相同数字的个数
            for (int i = 0; i < res.get(count) - 1; i++) {
                res.add(array[k]);
            }
            k++;
            count++;
        }
         for (k = 0; k < n; k++)
         System.out.println("" + res.get(k));
        //如果要求Kolakoski序列的第x项，构造完后res.get(x);即可
        //return res;
    }

}
