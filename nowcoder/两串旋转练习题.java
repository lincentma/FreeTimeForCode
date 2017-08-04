/*
如果对于一个字符串A，将A的前面任意一部分挪到后边去形成的字符串称为A的旋转词。比如A="12345",A的旋转词有"12345","23451","34512","45123"和"51234"。对于两个字符串A和B，请判断A和B是否互为旋转词。

给定两个字符串A和B及他们的长度lena，lenb，请返回一个bool值，代表他们是否互为旋转词。

测试样例：
"cdab",4,"abcd",4
返回：true
*/


import java.util.*;

public class Rotation {
    public boolean chkRotation(String A, int lena, String B, int lenb) {
        // write code here
        String strAll1 = A + A;
        String strAll2 = B + B;
        
        //KMP匹配
        boolean flag1 = KMP(strAll2,A);
        boolean flag2 = KMP(strAll1,B);
        
        //朴素匹配法
		//boolean flag1 = plain(strAll2,A);
        //boolean flag2 = plain(strAll1,B);
        
        if (flag1 == true && flag2 == true) {
            return true;
        }
        
        return false;
    }
    
    public static boolean KMP(String source, String pattern) {
        int i = 0, j = 0;
        char[] src = source.toCharArray();
        char[] ptn = pattern.toCharArray();
        int sLen = src.length;
        int pLen = ptn.length;
        
        int[] next = getNext(pattern);
        while (i < sLen && j < pLen) {
            // 如果j = -1,或者当前字符匹配成功(src[i] = ptn[j]),都让i++,j++
            if (j == -1 || src[i] == ptn[j]) {
                i++;
                j++;
            } else {
                // 如果j!=-1且当前字符匹配失败,则令i不变,j=next[j],即让pattern模式串右移j-next[j]个单位
		// 移动位数 = 已匹配的字符数 - 对应的部分匹配值
		// j = j - (j - next[j])
                j = next[j];
            }
        }
        if (j == pLen) {
            if (i - j < sLen) {
            	return true;
            }
        }
        return false;
    }
    
    public static int[] getNext(String str) {
        int[] next = new int[str.length()];
        int i = 0;
        int j = -1;
        next[i] = j;// next数组中next[0]为-1
        
        while(i<str.length()-1){
            if(j==-1 || str.charAt(i)==str.charAt(j)){
                i++;
                j++;
                next[i] = j;
            } else {
                j = next[j]; //若字符不相等，则j值进行回溯。
            }
        }
        return next;
        
    }
    
    private static boolean plain(String source, String pattern) {
        int sourceLength=source.length();
        int patternLength=pattern.length();
        
        for(int i=0;i<=(sourceLength-patternLength);i++){
            String str=source.substring(i, i+patternLength);
            if(str.equals(pattern)){
                return true;
            }
        }
        return false;
    }
}
