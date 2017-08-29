package com.sohu2018;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * Created by ml on 2017/8/29.
 * 产品包装为 1*1 2*2 3*3 4*4 5*5 6*6
 * 箱子包装为6*6
 * 计算不同种类产品个数最少需要几个箱子
 * via http://www.acmerblog.com/POJ-1017-Packets-blog-226.html
 */
public class minbaoguonum {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        ArrayList<ArrayList<Integer>> input = new ArrayList<>();
        while (in.hasNext()) {
            String s = in.nextLine();
            String[] ss = s.split(" ");
            ArrayList<Integer> num = new ArrayList<>();
            int sum = 0;
            for (int i = 0; i < ss.length; i++) {
                num.add(Integer.parseInt(ss[i]));
                sum += Integer.parseInt(ss[i]);
            }
            if (sum == 0) {
                solution(input);
                break;
            } else {
                input.add(num);
            }
        }
        in.close();
    }

    private static void solution(ArrayList<ArrayList<Integer>> input) {
        int num = input.size();
        for (int i = 0; i < num; i++) {
            minBox(input.get(i));
        }
    }

    private static void minBox(ArrayList<Integer> input) {
        int boxNum = -1;
        if (input.size() != 6) {
            System.out.println(boxNum);
        } else {
            int num1 = input.get(0);
            int num2 = input.get(1);
            int num3 = input.get(2);
            int num4 = input.get(3);
            int num5 = input.get(4);
            int num6 = input.get(5);
            boxNum = 0;
            int temp = 0;
            while (num1 != 0 || num2 != 0 || num3 != 0 || num4 != 0 || num5 != 0 || num6 != 0) {
                //6*6 5*5 4*4 一个就会占用一个箱子
                //6*6
                boxNum = num6;
                //6*6 放完
                num6 = 0;
                //5*5 同时可以一个可以放入11个1*1
                boxNum += num5;
                num1 -= num5 * 11;
                //5*5 放完
                num5 = 0;
                //4*4 剩余空间2种选择，2*2 或者 1*1 其中最多可以放5个2*2,,一个2*2可以放4个1*1
                boxNum += num4;
                if (num2 >= num4 * 5) {
                    //全部放为2*2
                    num2 -= num4 * 5;
                } else {
                    //2*2放完还剩余空的数量
                    temp = num4 * 5 - num2;
                    num1 -= temp * 4;
                    num2 = 0;
                }
                //4*4 放完
                num4 = 0;
                //3*3  剩余空间2种选择 1*1 2*2
                //一个箱子正好可以放4个3*3 ,一个3*3可以放下1个2*2和5个1*1
                boxNum += num3 / 4;
                //剩余3*3
                temp = num3 % 4;
                if (temp != 0) {
                    //剩余不为0 添加一个箱子
                    boxNum += 1;
                    //分情况讨论
                    if (temp == 1) {
                        //最大化放2*2 可以放5个2*2, 剩余地方放7个1*1
                        if (num2 > 5) {
                            num2 -= 5;
                            num1 -= 7;
                        } else if (num2 >= 0) {
                            //2*2 可以放完
                            num2 = 0;
                            //放1*1个数
                            num1 -= 36 - 9 - 4 * num2;
                        }
                    } else if (temp == 2) {
                        //最大化放2*2 可以放3个2*2, 剩余地方放6个1*1
                        if (num2 > 3) {
                            num2 -= 3;
                            num1 -= 6;
                        } else if (num2 >= 0) {
                            //2*2 可以放完
                            num2 = 0;
                            //放1*1个数
                            num1 -= 36 - 18 - 4 * num2;
                        }
                    } else if (temp == 3) {
                        //最大化放2*2 可以放1个2*2, 剩余地方放5个1*1
                        if (num2 > 1) {
                            num2 -= 1;
                            num1 -= 5;
                        } else if (num2 >= 0) {
                            //2*2 可以放完
                            num2 = 0;
                            //放1*1个数
                            num1 -= 36 - 27 - 4 * num2;
                        }
                    }
                }
                //3*3 放完
                num3 = 0;

                //2*2
                if (num2 > 0) {
                    //一个箱子最低多放9个2*2
                    boxNum += num2 / 9;
                    temp = num2 % 9;
                    if (temp != 0) {
                        //剩余空间放1*1
                        boxNum += 1;
                        num1 -= 36 - 4 * temp;
                    }
                }
                //2*2 放完
                num2 = 0;

                //1*1
                if (num1 > 0) {
                    //一个箱子最低多放36个1*1
                    boxNum += num1 / 36;
                    temp = num1 % 36;
                    if (temp != 0) {
                        boxNum += 1;
                    }
                }
                //1*1 放完
                num1 = 0;

            }
            System.out.println(boxNum);
        }
    }
}
