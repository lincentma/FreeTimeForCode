
/** 
 * 题目描述：拉姆学单词  
 * 拉姆刚刚开始学习英文字母，并已经注意有趣的单词阵列。他想确定他是否能将最足单词排列在一个列表中， 
 * 使得该列表中任何单词的首字母与其前面单词的尾字母相同。你能编写一个计算机程序来帮助拉姆吗？ 
 *  
 * 类WordListOrder（中兴模拟题）的方法canArrangesWords的输入应包含一个单词数组arr。  
 * 如果列表中单词可按照要求的方式排列，返回1，否则，返回-1. 
 * 在这种排列中，第一个单词的首字母和最后一个单词的尾字母可以为任意字母，不需要满足约束条件。
 */
 
 // 思路1：统计首尾字符个数比较差值
 // 思路2：字符数组全排列进行比较
 
public class WordSolitaire {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        while(scan.hasNext()) {
            int n = scan.nextInt();
            String[] words = new String[n];
            for (int i = 0; i < n; i++) {
                words[i] = scan.next();
            }

            System.out.println(solve(n, words));
        }
        scan.close();
    }   

    private static int solve(int n, String[] words) {
        //用两个数组分别记录所有单词的首字母和尾字母，及相应字母重复出现的次数
        int[] heads = new int[26];
        int[] tails = new int[26];

        while(--n >= 0) {
            String s = words[n];
            heads[s.charAt(0) - 97]++;//数组下标对应26个小写字母，元素值代表该字母出现在单词首位的次数
            tails[s.charAt(s.length() - 1) - 97]++;
        }

        int count = 0;
        for(int j = 0; j < 26; ++j) {
            int diff = Math.abs(heads[j] - tails[j]);
            if(diff > 1)
                //如果当前字母作为单词的首字母和尾字母的次数之差超过1，
                //则不能构成单词接龙
                return "No";
            else if(diff == 1) {
                //如果当前字母作为单词的首字母和尾字母的次数之差等于1，
                //但在此之前已经出现过满足这种情况的其他2个字母，则也不能构成单词接龙
                if(++count > 2)
                    return 0;
            }
        }

        return 1;
    }
}


public class WordList {  
  
    public static boolean flag;  
    public static void main(String[] args) {  
        //String[] arr = {"1","2","3"};  
        //String[] arr = {"xia","aq","qot","tou"};  
        Scanner sc = new Scanner(System.in);  
        int n = sc.nextInt();  
        String[] arr = new String[n];  
        for(int i=0; i<n; i++){  
            arr[i] = sc.next();  
        }  
          
        canArrangeWords(arr,0,arr.length);  
        if(flag == true){  
            System.out.println(1);  
        }else {  
            System.out.println(-1);  
        }  
          
    }  
  
    private static void canArrangeWords(String[] arr, int start, int len) {  
        if(start == len-1){  
            int i=1;  
            for(; i<len; i++){  
                if(arr[i-1].charAt(arr[i-1].length()-1)!=arr[i].charAt(0)){  
                    break;  
                }  
            }  
            if(i==len){  
                //测试用的代码  
                flag = true;  
            }  
        }else {  
            for(int i=start; i<len; i++){//两次交换保持原来的样子  
                swap(arr, start, i);  
                canArrangeWords(arr, start+1, len);  
                swap(arr, start, i);  
            }  
        }  
          
    }  
  
    /** 
     * 交换数组中i和j处的两个元素 
     * */  
    private static void swap(String[] arr, int i, int j) {  
        String temp = new String();  
        temp = arr[i];  
        arr[i] = arr[j];  
        arr[j] = temp;        
    }     
}  
