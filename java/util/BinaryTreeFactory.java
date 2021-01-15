package util;

import core.TreeNode;

import java.util.LinkedList;
import java.util.Queue;

/**
 *  二叉树的生成工厂，后期代码会添加统一的工厂接口
 *  暂时直接调用
 */

public class BinaryTreeFactory {

    /**
     * 静态方法： 生成一棵二叉树
     * 原思路：根据TreeNode[]数组中的下标做对应，即每一代的相邻子节点下标除2等于他们的父节点下标（这样实现好麻烦）
     * 新思路：用队列存储TreeNode，观察发现输入顺序其实是树的层序遍历，因此直接让它们出队就行了。
     *
     * @param inputs 以字符串数组形式输入的测试数据，例：{“-10”, "9", "20", "null", "null", "15","7"}
     * @return 二叉树的root节点
     */
    public static TreeNode getBinaryTreeFromStringArray(String[] inputs){

        // 1. 生成节点数组
        TreeNode[] nodeArray = new TreeNode[inputs.length];
        for(int i=0; i< nodeArray.length; i++){
            if (! inputs[i].equals("null")) {
                nodeArray[i] = new TreeNode(Integer.parseInt(inputs[i]));
            }
            else{
                nodeArray[i] = null;
            }
        }

        // 2. 遍历数组，并建立关联
        int parent = -1;
        TreeNode iter = null;
        for ( int i=1; i< inputs.length; i++){
            while(iter == null){
                parent += 1;
                iter = nodeArray[parent];
            }
            // 每次添加两个节点
            iter.left = nodeArray[i];
            i += 1;
            if(i== inputs.length)
                break;
            iter.right = nodeArray[i];
            // 移动父节点下标
            parent += 1;
            iter = nodeArray[parent];
        }
        return nodeArray[0];
    }

}
