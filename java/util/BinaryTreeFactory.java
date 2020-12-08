package util;

import core.TreeNode;

/**
 *  二叉树的生成工厂，后期代码会添加统一的工厂接口
 *  暂时直接调用
 */

public class BinaryTreeFactory {

    /**
     * 静态方法： 生成一棵二叉树
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
        TreeNode iter = nodeArray[0];
        for ( int i=1; i< inputs.length; i++){
            // 奇数号，则是左节点
            if (i%2!=0){
                iter.left = nodeArray[i];
            }
            // 偶数号，则是右节点，并更新父节点
            else{
                iter.right = nodeArray[i];
                int x = 0;
                while(nodeArray[i/2+x]==null)
                    x += 1;
                iter = nodeArray[i/2+x];
            }
        }

        return nodeArray[0];
    }

}
