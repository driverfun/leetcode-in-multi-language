package solutions.others;

import util.SolutionsFacade;

public class LeetCode200 implements SolutionsFacade {

    public int[] parent;        // 记录节点的父节点
    public int[] size;          // 记录树的!!重量！！指一个节点下挂的节点总个数
    public int count;           // 记录


    /**
     * 思路：
     * 并查集算法，写过一次发现之所以叫这个名，因为它的核心函数就是union+find。
     * 在本题中我做了一个哈希映射将一个二维数组转化成一维问题解决。
     * @param grid
     * @return
     */
    public int numIslands(char[][] grid) {
        int hang = grid.length;
        int lie = grid[0].length;
        count = hang*lie;
        // 初始化数组
        parent  = new int[count];
        size = new int[count];
        for(int i = 0;i< count;i++){
            parent[i] = i;
            size[i] = 1;
        }

        for(int i=0;i<hang;i++){
            for(int j = 0;j<lie;j++){
                // 先检查一个节点四周是否有1，如果有再看是否已经连接，如果未连接则连接起来。
                if(grid[i][j]=='1'){
                    // 因为是从左到右、从上到下，故可以只向右检查、向下检查
                    // 但因为要检查0，所以不可以少算最右行、最下列

                    int a = hash(i,j,lie);
                    // if (i>=1 && grid[i-1][j]=='1'){
                    //     int b = hash(i-1,j,lie);
                    //     if(find(a)!=find(b))
                    //         union(a, b);
                    // }
                    // if (j>=1&&grid[i][j-1]=='1'){
                    //     int b = hash(i,j-1,lie);
                    //     if(find(a)!=find(b))
                    //         union(a, b);
                    // }

                    if ( i+1<hang&&grid[i+1][j]=='1' ){
                        int b = hash(i+1,j,lie);
                        if(find(a)!=find(b))
                            union(a, b);
                    }
                    if ( j+1<lie&&grid[i][j+1]=='1'){
                        int b = hash(i,j+1,lie);
                        if(find(a)!=find(b))
                            union(a, b);
                    }
                }
                // 因为最早初始化的时候我们并没有区分0和1的个数，而真正需要的答案实际是1的总数剪掉连接好的个数。
                // 所以我们这里逢0就减一，从而获得正确答案。
                else
                    count --;
            }
        }

        return count;
    }

    /**
     * 二维转一维的运算式
     * @param x
     * @param y
     * @param lieNum    列数，即每一行多长。
     * @return
     */
    public int hash(int x ,int y, int lieNum){
        return x*lieNum + y;
    }

    public void union(int a, int b){
        int pA=find(a), pB=find(b);
        if(size[pA]>size[pB]){
            parent[pB] = pA;
            size[pA] += size[pB];
        }
        else{
            parent[pA] = pB;
            size[pB] += size[pA];
        }
        count--;
    }

    public int find(int x){
        while(parent[x]!=x)
            x = parent[x];
        return x;
    }


    @Override
    public void calculate(Object... objects) {
        numIslands((char[][]) objects[0]);
        int s= 3;
    }
}
