package leetcode;

/**
 * Created by ml on 2017/11/15.
 */
public class ReverseInteger {
    public static void main(String[] args) {
        int test1 = -3321;
        int test2 = 120;

        System.out.println(reverseInteger(test1));
        System.out.println(reverseInteger(test2));
    }

    private static int reverseInteger(int n) {
        boolean flag = false;
        flag = n < 0 ? true : false;
        int abs = Math.abs(n);
        int res = 0;
        while(abs > 0 ) {
            if(res != 0 &&  Integer.MAX_VALUE / res < 10) {
                return 0;
            }
            int temp = abs % 10;
            abs = abs / 10;
            res = res * 10 + temp;
        }

        res =  flag == true ? -res : res;
        return res;
    }
}
