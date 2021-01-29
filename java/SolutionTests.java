import core.ListNode;
import core.TreeNode;
import util.BinaryTreeFactory;
import util.LinkedListFactory;

import java.lang.reflect.Method;

/**
 * 使用反射避免import一堆包，也可以使用哪个算法就import solutions.*
 */
public class SolutionTests {

    static String SOLUTIONSCLASS = "solutions.others.LeetCode"+"2";
    public static void main(String[] args) throws Exception{

        // 准备测试用例
        String[] inputs0 = {"3", "1", "4", "null", "2"};
        String[] inputs1 = {"1", "2", "3", "null", "null", "4", "5", "6", "7"};
        String[] inputs2 = {"5","4","8","11","null","13","4","7","2","null","null","5","1"};
        // 10,5,-3,3,2,null,11,3,-2,null,1
        String[] bstInputs0 = { "10", "5", "-3", "3", "2", "null", "11", "3", "-2", "null", "1"};
        // [3,4,5,1,3,null,1]
        String[] inputs5 = {"3", "4", "5", "1", "3", "null", "1"};
        String[] inputs3 = {"7", "3", "15","null", "null", "9", "20"};
        String[] dump0 = {"1"};

        // 封装数据
        TreeNode root = BinaryTreeFactory.getBinaryTreeFromStringArray(inputs1) ;
        ListNode l1 = LinkedListFactory.getLinkedListFromIntegerArray(new int[]{2,4,3});
        ListNode l2 = LinkedListFactory.getLinkedListFromIntegerArray(new int[]{5,6,4});
//        int root = 4;
        // 第一个参数转成Object，第二个参数转Object...

        Object[] item = {l1, l2};
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
