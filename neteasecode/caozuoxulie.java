import java.util.Scanner;

/**
 * Created by ml on 2017/8/17.
 * 小易有一个长度为n的整数序列,a_1,...,a_n。然后考虑在一个空序列b上进行n次以下操作:
 * 1、将a_i放入b序列的末尾
 * 2、逆置b序列
 * 小易需要你计算输出操作n次之后的b序列。
 * 输入

4
1 2 3 4
输出

4 2 1 3
 */

 
 //就是找规律
 //遇到题先不慌，躲在纸上画出自己能想到的情况，并尝试总结出规律

 //左神规律
 //双端队列实现
 //第一元素插入；第二个元素插在第一个元素的左边；第三个元素插在一个元素的右边；第四个元素插入在第一个元素的左边
 
public class Main {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        while(in.hasNext()){
            int N = in.nextInt();
            int[] intArray = new int[N];
            for(int i = 0; i < N;i++) {
                intArray[i] = in.nextInt();
            }
            findCircle(intArray);
        }
        in.close();
    }

    private static void findCircle(int[] intArray) {
        //1
        int num = 0;
        for(int i = intArray.length - 1; i >= 0; i = i - 2) {
            if(num == intArray.length) {
                System.out.print(intArray[i]);
            } else {
                System.out.print(intArray[i]);
                System.out.print(" ");
            }
            num++;
        }
        if(num < intArray.length) {
            if(intArray.length % 2 == 1) {
                for(int i = 1; i < intArray.length; i = i + 2) {
                    if(num == intArray.length - 1) {
                        System.out.print(intArray[i]);
                    } else {
                        System.out.print(intArray[i]);
                        System.out.print(" ");
                    }
                    num++;
                }
            }
            if(intArray.length % 2 == 0) {
                for(int i = 0; i < intArray.length; i = i + 2) {
                    if(num == intArray.length - 1) {
                        System.out.print(intArray[i]);
                    } else {
                        System.out.print(intArray[i]);
                        System.out.print(" ");
                    }
                    num++;
                }
            }
        }

        //
    }
}
