import core.ListNode;
import core.TreeNode;
import util.BinaryTreeFactory;
import util.LinkedListFactory;

import java.lang.reflect.Method;

/**
 * 使用反射避免import一堆包，也可以使用哪个算法就import solutions.*
 */
public class SolutionTests {

    static String SOLUTIONSCLASS = "solutions.dps.LeetCode"+"121";
    public static void main(String[] args) throws Exception{

        // 准备测试用例
        String[] inputs0 = {"1","3","2","5"};
        String[] inputs1 = {"2","1","3","null","4","null","7"};
        String[] inputs2 = {"5","4","8","11","null","13","4","7","2","null","null","5","1"};
        // 10,5,-3,3,2,null,11,3,-2,null,1
        String[] bstInputs0 = { "10", "5", "-3", "3", "2", "null", "11", "3", "-2", "null", "1"};
        // [3,4,5,1,3,null,1]
        String[] inputs5 = {"3", "4", "5", "1", "3", "null", "1"};
        String[] inputs3 = {"7", "3", "15","null", "null", "9", "20"};
        String[] dump0 = {"1","2","2","null","3","null","3"};

        // 封装数据
        TreeNode root1 = BinaryTreeFactory.getBinaryTreeFromStringArray(inputs0);
        TreeNode root2 = BinaryTreeFactory.getBinaryTreeFromStringArray(inputs1);
        String s = "abcaaaad";
        String p = "ab.a*d";
//        int root = 4;
        // 第一个参数转成Object，第二个参数转Object...
        Object[] item = {new int[]{4,7,2,1} };
        Object[] suite = {item};

        // 反射构造测试对象
        Class clazz = Class.forName(SOLUTIONSCLASS);
        Object test = clazz.getDeclaredConstructor().newInstance();
        Method method = clazz.getDeclaredMethod("calculate",Object[].class);
        method.invoke(test, suite);

        // 留个地方打断点或输出结果
        System.out.print("stop for debug.");
    }

}
