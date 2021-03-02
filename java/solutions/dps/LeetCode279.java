package solutions.dps;

import util.SolutionsFacade;

public class LeetCode279 implements SolutionsFacade {

    /**
     * 状态方程： dp[n] = min(dp[n-1]+1, dp[n-4]+1, dp[n-9]+1 ...)
     * 这是另一个换零钱问题，只不过随着n的增大，可用于换得纸币种类在增多，我们称这些币种为基数。
     * 可以看到，在dp数组迭代的过程中：
     * 1.每迈过整数的平方就会多一种基数，此时要多加一个基数分解目标数；
     * 2.每到整数平方的值为1，因为新基数1个就可以换它。
     * @param n
     * @return
     */
    public int numSquares(int n) {

        int[] dp = new int[n+1];
        int base = 1;
        for(int i = 1;i<=n;i++){
            if(i== base*base){// 新基数诞生！
                dp[i] = 1;
                base+=1;
            }else{
                // 用每个基数算一次
                int min = 9999999;
                for(int j = 1;j<=base && i>=j*j; j++){
                    min = Math.min(min, dp[i-j*j]+1);
                }
                dp[i] = min;
            }
        }
        return dp[n];

    }



    /**
     * 官方解法：
     * 一、暴力递归法（省略）
     *
     * 二、动态规划（如我所写）
     *
     * 三、贪心算法（代码省略）
     * （详见：[https://leetcode-cn.com/problems/perfect-squares/solution/wan-quan-ping-fang-shu-by-leetcode/]）
     *      思路：既然要求可以组合成目标数的最小数目，我们从小到大依次尝试可能的分割数，比如1次分割很好判断（直接看目标树n在不在
     *  平方和数列中/或者开根号再平方是否与n相等），2次分割：用n依次减去平方和再判断余数是否可被1次分割，3次分割：用n依次减去平
     *  方和再判断能否被2次分割（这其实又是动规的思路了）。
     *
     *
     * 四、N叉树（对解法三的抽象、优化）
     *      思路：分析上一解法，实际的递归求解过程是个N叉树，如n=13,则分别计算f(13-1)、 f(13-4)、f(13-9)。此时就可以套BFS，
     *  将同层节点缓存计算，当某一层的某个节点值为true（风格成功）时，其高度就是结果。
     *
     *
     * 五、数学推导：
     *      拉格朗日的四平方和定理将值域限定到1-4中，又有Lengendre的三平方和定理唯一确定了值为3的确定情况，但同时也说明了它的反例
     *  结果必为4，剩下值为1、2的情况试试便知，如都不成功，则唯一可能性为3（此法源自数论，自认为超纲）。
     *
     */
    @Override
    public void calculate(Object... objects) {
        numSquares((int)objects[0]);
    }
}
