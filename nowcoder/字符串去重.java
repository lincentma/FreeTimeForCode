package com;

/**
 * Created by ml on 2017/9/6.
 * 1.一个只包含小写字母的字符串，去重生成一个只包含单一字母的字符串。例如“abadcab”变成"abdc"，只让用最多一个额外的int变量
 */
public class quchongstr {

    public static void main(String[] args) {
        System.out.println(removerepeatedchar("aabbcc"));
    }

    public static String removerepeatedchar(String s) {
        if (s == null)
            return s;
        if (s.length() == 1) {
            return s;
        }
        //初始化int，总共32位，前26位为0；
        //这里可以使用32位整数来表示每个单词的字母存在情况。我们知道小写字母a~z有26位，因此一个32位整数的低26位可以用于标记a~z各个字母是否出现，比如最低位标记'a'是否出现；第二位标记'b'是否出现…以此类推
        int flag = 0;
        for (int i = 0; i < s.length(); i++) {
            flag |= 1 << (s.charAt(i) - 'a');
        }
        StringBuffer bf = new StringBuffer();
        for (int i = 0; i <= 25; i++) {
            if (GetBit(flag, i) == 1) {
                bf.append((char) ('a' + i));
            }
        }
        return bf.toString();
    }
    //获取每一位的值
    private static int GetBit(int number, int index) {
        if (index < 0 || index > 31) {
            return 0xff;
        }
        return (number >> index) & 1;
    }

}
