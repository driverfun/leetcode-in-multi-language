package util;

public interface SolutionsFacade {

    /**
     * 该接口有三步工作：
     * 1. 对object入参做对象类型转换
     * 2. 调用解题方法
     * 3. 输出结果或留空语句方便打断点
     *
     * @param objects 可变参数对象
     */
    void calculate(Object ...objects);

}
