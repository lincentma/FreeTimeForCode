package com.nowcoder.huawei2017;

import java.util.Scanner;

/**
 * Created by ml on 2017/8/23.
 * 题目：海滩上有一堆桃子，五只猴子来分。第一只猴子把这堆桃子凭据分为五份，多了一个，这只猴子把多的一
 个扔入海中，拿走了一份。第二只猴子把剩下的桃子又平均分成五份，又多了一个，它同样把多的一个扔入海中
 ，拿走了一份，第三、第四、第五只猴子都是这样做的，问海滩上原来最少有多少个桃子？ 
 程序分析：

 桃子总数为total个，

 (1)第一个猴子分完5份后，每个猴子拿的桃子个数为：(total-1)/5;

 (2)第一个猴子拿走一份后，剩余的桃子为tota_01 = total-(total-1)/5；然后第二个猴子又将剩余的桃子分为5份，即每个猴子拿(total_01-1)/5;

 (3) 第三个猴子同理：将剩余的桃子total_02 = total - (total-1)/5 - (total_01-1)/5  分为5份；每个猴子拿(total_02-1)/5；

 (4) 第四个猴子同理：将剩余的桃子total_03 = total - (total-1)/5 - (total_01-1)/5 - (total_02-1)/5  分为5份；每个猴子拿(total_03-1)/5；

 (5) 第五个猴子同理：将剩余的桃子total_04 = total - (total-1)/5 - (total_01-1)/5 - (total_02-1)/5 - (total_03-1)/5   分为5份；每个猴子拿(total_04-1)/5；
 */
public class peachnum {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        while (in.hasNextInt()) {
            int N = in.nextInt();
            System.out.println(calNum(N));
        }
        in.close();
    }

    private static int calNum(int n) {
        int t = 1;
        for(int i = 0; i < n; i++) {
            t = t * n + 1;
        }
        return t;
    }
}
