package solutions.dps;

import util.SolutionsFacade;

public class LeetCode121 implements SolutionsFacade {

    /**
     * 思路（动规？）：将本问题看作在一个连续数组中n个子序的最大差值问题
     * 设置min为某个区间的最低点，profit为利润，遍历时，数组当前值如果小于min，则说明有更好的买入时机，刷新min为该值；
     * 否则，用当前值减掉min计算利润，它比profit大时则更新profit。
     *
     * @param prices
     * @return
     */
    public int maxProfit(int[] prices) {
        int minium = prices[0];
        int maxium = 0;
        int profits = 0;
        // 遍历数组
        for(int i = 1;i< prices.length;i++){
            if(prices[i]> maxium)
                maxium = prices[i];
            // 更新最小值
            if(prices[i]<minium){
                // 计算一次利润
                profits = (maxium-minium)> profits?(maxium-minium) : profits;
                minium = prices[i];
                maxium = 0;
            }
        }
        profits = (maxium-minium)> profits?(maxium-minium) : profits;
        return profits;
    }


    @Override
    public void calculate(Object... objects) {
        maxProfit((int[])objects[0]);
    }
}
