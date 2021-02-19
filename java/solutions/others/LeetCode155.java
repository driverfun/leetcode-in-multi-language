package solutions.others;

import java.util.Stack;

public class LeetCode155 {

    private Stack<Integer> stack;
    private Stack<Integer> dp;

    /** initialize your data structure here. */
    /**
     *  思路：本题目使用辅助空间存储最小值是确定的，重点在于怎么存储。
     *  思考发现这一问题可理解为：求当前序列（栈）中的最小值，这是妥妥的动规问题。
     *  因此，转移方程为：dp[i]= min(dp[i-1],x)。这样，dp数组每个元素对应栈中同等元素数量时的最小值。
     */
    public LeetCode155() {
        // 原始栈用于存放整数元素
        stack = new Stack<>();
        // dp栈用于储存最小值
        dp = new Stack<>();
    }

    public void push(int x) {
        stack.push(x);
        if(dp.size()>0){
            if(dp.peek()>x){
                dp.push(x);
            }
            else{
                dp.push(dp.peek());
            }
        }
        else{
            dp.push(x);
        }
    }

    public void pop() {
        stack.pop();
        dp.pop();
    }

    public int top() {
        return stack.peek();
    }

    public int getMin() {
        return dp.peek();
    }

}
