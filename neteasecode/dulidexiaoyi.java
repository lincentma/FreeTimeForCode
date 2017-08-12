import java.util.Scanner;

/**
 * Created by ml on 2017/8/12.


/**
小易为了向他的父母表现他已经长大独立了,他决定搬出去自己居住一段时间。一个人生活增加了许多花费:
 小易每天必须吃一个水果并且需要每天支付x元的房屋租金。
 当前小易手中已经有f个水果和d元钱,小易也能去商店购买一些水果,商店每个水果售卖p元。
 小易为了表现他独立生活的能力,希望能独立生活的时间越长越好,小易希望你来帮他计算一下他最多能独立生活多少天。
        输入描述:
        输入包括一行,四个整数x, f, d, p(1 ≤ x,f,d,p ≤ 2 * 10^9),以空格分割


        输出描述:
        输出一个整数, 表示小易最多能独立生活多少天。

        输入例子1:
        3 5 100 10

        输出例子1:
        11
 */
public class dulidexiaoyi {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int x = in.nextInt();
        int f = in.nextInt();
        int d = in.nextInt();
        int p = in.nextInt();
        int res = calMaxDay2(x,f,d,p);
        System.out.println(res);
    }


    //通过代码
    public static int  calMaxDay2(int x, int f, int d, int p) {
        int days = 0;
        //1、先判断可以租多少天，小于f则只能住钱对应的天数
        if (d/x <= f) {
            days = d/x;
            return days;
        }
        //2、d/x > f,也就是吃完自己拥有的水果还可以住
        //这个时候的坑在于当前cost如果再和x+p做判读的话，就会溢出
        //先相减，防止溢出
        int remainMoney = d - f * x;
        if (remainMoney >=0) {
            //包含两部分：1、吃完自己拥有的 2、花完自己剩下的
            days += f + remainMoney / (x + p);
        }
        return days;
    }

    //自己写的通过率为90%的代码
    //不同过的用例1 2000000000 2000000000 2000000000
    //int 范围为java中的int类型存储长度为32bit.所以范围是“-2^32”到“2^32-1”;也就是“-2147483648”到“2147483647”
    //执行到cost += (x+p);越界
    //cost=-294967295，此处出现问题
    public static int  calMaxDay(int x, int f, int d, int p) {
        int days = 0;
        int cost = 0;
        //error
        if (d < x) {
            return days;
        }

        while (cost <= d) {
            while (f >0) {
                f--;
                cost += x;
                if (cost <= d) {
                    days++;
                }
            }
            cost += (x+p);
//            System.out.println("cost=" + cost);
//            System.out.println("f=" + f);
//            System.out.println(cost <= d);
//            System.exit(-1);
            if (cost <= d) {
                days++;
            } else {
                System.out.println(cost);
            }
        }
        return days;
    }
}
