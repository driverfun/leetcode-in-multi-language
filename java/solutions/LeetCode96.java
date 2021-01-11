package solutions;

import util.SolutionsFacade;

public class LeetCode96 implements SolutionsFacade {

    public int numTrees(int n) {
        // 动规搞它：
        // 每个根节点的构造数为其左子树种类*右子树种数
        // 大于它的节点数为右子树，小于它的数目为左子树，因为本题目是个递增1的有序数组
        // O(1) =1, O(2)=O(1){1为根节点，无左子树}+O(1){2为根节点，无右子树}=2
        // O(3) = O(2){根节点是1}+ O(1)*O(1){根节点是2}+ O(2){根节点是3}=5

        if (n==0)
            return 0;
        int[] kinds = new int[n+1];
        kinds[0] = 1;
        kinds[1] = 1;
        if(n>1){
            for (int i =2 ;i<=n;i++){
                // 从1 开始算到n
                for (int j = 1; j<=i;j++){
                    // kinds[j-1]为左节点数
                    // kinds[i-j]为右节点数
                    kinds[i] += kinds[j-1]* kinds[i-j];
                }
            }
        }

        return kinds[n];
    }

    @Override
    public void calculate(Object... objects) {
        System.out.println(numTrees((int)objects[0]));
    }
}

