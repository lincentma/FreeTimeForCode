import java.util.*;

/*
public class TreeNode {
    int val = 0;
    TreeNode left = null;
    TreeNode right = null;
    public TreeNode(int val) {
        this.val = val;
    }
}*/

/*
有一棵二叉树，请设计一个算法，按照层次打印这棵二叉树。
给定二叉树的根结点root，请返回打印结果，结果按照每一层一个数组进行储存，所有数组的顺序按照层数从上往下，且每一层的数组内元素按照从左往右排列。保证结点数小于等于500。
*/

public class TreePrinter {
    
    public int[][] printTree(TreeNode root) {
        // write code here
        ArrayList<ArrayList<Integer>> res = new ArrayList<ArrayList<Integer>>();
        
        //层次遍历
        res = levelOrderBottom(root);
        
        //ArrayList 转为 int[]
        int row = res.size();
        int[][] result = new int[row][];
        for (int i = 0; i < row; i++) {
            int[] d = new int[res.get(i).size()];
            for(int j = 0;j<res.get(i).size();j++) {
                d[j] = res.get(i).get(j);
            }
            result[i] = d;
        }
        return result;
    }
    
    public static int depth(TreeNode root) {
        if(root == null) return 0;
        
        int l = depth(root.left);
        int r = depth(root.right);
          
        int dep = l > r ? l : r;
        return dep+1;
    }
    
    public static int levelNode(TreeNode root, int level, ArrayList<Integer> l) {
        if(root == null) return 0;
        if(level == 1){
            l.add(root.val);
        }  
        else{  
            levelNode(root.left, level-1, l);
            levelNode(root.right, level-1, l);
        }
          
        return 0;
    } 
    
    public static ArrayList<ArrayList<Integer>> levelOrderBottom(TreeNode root) {
        ArrayList<ArrayList<Integer>> list = new ArrayList<ArrayList<Integer>>();  
        int dep = depth(root);  
        for(int i=1; i<=dep; i++){
            ArrayList<Integer> l = new ArrayList<Integer>();
            levelNode(root, i, l);
            list.add(l);
        }  
        return list;
    }
}
