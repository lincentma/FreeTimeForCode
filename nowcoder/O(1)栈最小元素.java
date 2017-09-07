package com;

import java.util.Stack;

/**
 * Created by ml on 2017/9/7.
 * 原栈中，每次添加一个新元素时，就和辅助栈的栈顶元素相比较，如果新元素小，就把新元素的值放到辅助栈和原栈中，如果新元素大，就把元素放到原栈中；出栈时，如果原栈跟辅助栈元素相同，都弹出，否则只弹出原栈栈顶元素.
 */
public class Stackmin {
    public static void main(String[] args) {

        AdvancedStack<Integer> stack = new AdvancedStack<Integer>();
        stack.push(5);
        System.out.println(stack.getMin());
        stack.push(7);
        System.out.println(stack.getMin());
        stack.push(3);
        System.out.println(stack.getMin());
        stack.push(9);
        System.out.println(stack.getMin());
        stack.push(3);
        System.out.println(stack.getMin());
        stack.pop();
        System.out.println(stack.getMin());


    }


    static class AdvancedStack<T extends Comparable> {
        Stack<T> stackNormal = new Stack<T>();
        Stack<T> stackMin = new Stack<T>();
        Stack<T> stackMax = new Stack<T>();

        public void push(T e) {
            stackNormal.push(e);

            //最小栈为空或者push的值小于最小栈的栈顶元素
            if (stackMin.isEmpty() || e.compareTo(stackMin.peek()) < 0) {
                stackMin.push(e);
            } else if (e.compareTo(stackMin.peek()) > 0) {
                stackMin.push(stackMin.peek());
            }

            if (stackMax.isEmpty() || e.compareTo(stackMin.peek()) > 0) {
                stackMax.push(e);
            } else if (e.compareTo(stackMax.peek()) < 0) {
                stackMax.push(stackMax.peek());
            }
        }


        public T pop() {
            if (!stackNormal.isEmpty() && !stackMin.isEmpty() && !stackMax.isEmpty()) {
                T e = stackNormal.pop();
                stackMin.pop();
                stackMax.pop();
                return e;
            } else {
                return null;
            }
        }

        public T getMin() {
            return stackMin.peek();
        }

        public T getMax() {
            return stackMax.peek();
        }

    }

}
