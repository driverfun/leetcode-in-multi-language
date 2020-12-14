import core.TreeNode;
import util.BinaryTreeFactory;

import java.lang.reflect.Method;

/**
 * 使用反射避免import一堆包，但想了想性能损失还不如直接import solutions.*
 */
public class SolutionTests {

    static String SOLUTIONSCLASS = "solutions.LeetCode112";
    public static void main(String[] args) throws Exception{

        // 准备测试用例
        String[] inputs0 = {"1", "null" ,"2", "3"};
        String[] inputs1 = {"2", "3" ,"null", "1"};
        String[] inputs2 = {"5","4","8","11","null","13","4","7","2","null","null","null","1"};

        // 封装数据
        TreeNode root = BinaryTreeFactory.getBinaryTreeFromStringArray(inputs2) ;
        Object[] suite = { root, 22 };

        // 反射构造测试对象
        Class clazz = Class.forName(SOLUTIONSCLASS);
        Object test = clazz.getDeclaredConstructor().newInstance();
        Method method = clazz.getDeclaredMethod("calculate",Object[].class);
        method.invoke(test, suite);

        // 留个地方打断点或输出结果
        System.out.print("stop for debug.");
    }

}
