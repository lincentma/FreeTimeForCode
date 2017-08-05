/*
public class RandomListNode {
    int label;
    RandomListNode next = null;
    RandomListNode random = null;
 
    RandomListNode(int label) {
        this.label = label;
    }
}
输入一个复杂链表（每个节点中有节点值，以及两个指针，一个指向下一个节点，另一个特殊指针指向任意一个节点）。
*/
public class Solution {
    public RandomListNode Clone(RandomListNode pHead)
    {
        if(pHead == null){
            return pHead;
        }
         
        RandomListNode node = pHead;
        RandomListNode next = null;
         
        while(node != null){
            next = node.next;  //记录原链表中当前节点的下一个节点
            node.next = new RandomListNode(node.label);   //copy当前节点，作为当前节点的下一个节点
             
            node.next.next = next; //将链表加入到链中
            node = next;           //跳到原链表中的下一个节点
        }
         
         
        //头指针复位
        node = pHead;
         
        //复制random指针
        while(node != null){
            next = node.next.next;   //next指向原链表的下一个节点
            node.next.random = node != null? node.random : null;  //复制random
             
            node = next;  //向下移动
        }
         
        //分化复制后的链表
        node = pHead;
        RandomListNode res = pHead.next;
        RandomListNode copyNode = null;
        while(node != null){
            next = node.next.next;
            copyNode = node.next;
             
            node.next = next;
            copyNode.next = next != null ? next.next : null;
             
            //指针向下移动
            node = next;
        }
        return res;
    }
         
}
