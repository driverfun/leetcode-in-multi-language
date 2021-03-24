package gists;

public class UnionFind {

    public int[] parent;        // 记录节点的父节点
    public int[] size;          // 记录树的!!重量！！指一个节点下挂的节点总个数
    public int count;           // 记录


    /**
     * 著名的！！并查集算法！！
     * 很有趣的算法！
     * @param n
     */
    public UnionFind(int  n){
        count = n;
        parent = new int[count];
        size = new int[count];
        for(int i=0;i<n;i++){
            parent[i]= i;           // 每个节点的父节点指向自己
            size[i]=1;              // 每棵树的初始重量是1
        }
    }

    /**
     * 连接a、b连个元素，即让它们的父节点相同
     * 小树连大大树，这样才平衡
     * @param a
     * @param b
     */
    public void union(int a, int b){
        int pA = find(a), pB = find(b);
        if(size[pA]>size[pB]){
            parent[pB] = pA;
            /**
             * 说明：
             *  为什么是小树并如大树，因为如果反过来，整棵树就会趋于“棍化“。如：
             *  A:  5    B:  6      C: 6                    C: 6             D:  7           E: 7
             *      |  ->   |   =      \        ;               \       ->       \      =       \
             *      3                   5                        5                               6
             *                            \                       \                               \
             *                             3                       3                               5
             *                                                                                      \
             *                                                                                       3
             *  所以，重量值要叠加到要被加入的根节点上。
             */
            size[pA] += size[pB];
        }else{
            parent[pA] = pB;
            size[pB] += size[pA];
        }
        count--;
    }

    /**
     * 判断a、b元素是否相连，即判断二者父节点是否相同
     * @param a
     * @param b
     * @return
     */
    public boolean connected(int a, int b){
        return find(a)==find(b);
    }

    /**
     * 返回剩余的独立节点个数
     * @return
     */
    public int isolations(){
        return count;
    }


    /**
     * 找到x节点所在树的根节点
     * @param x
     * @return
     */
    public int find(int x){
        // 顶级父节点的值一定是自身。
        while(parent[x]!=x){
            x = parent[x];
        }
        return x;
    }

    public static void main(String[] args){
        UnionFind solution = new UnionFind(4);
        solution.union(1,2);
        solution.union(3,0);
        solution.union(1,3);
        System.out.println(solution.isolations());
    }
}
