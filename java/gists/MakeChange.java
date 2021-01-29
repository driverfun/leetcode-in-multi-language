package gists;

public class MakeChange {

    /**
     * 设置3种零钱金额：1、2、5元，最小面额，寻找最少零钱数量
     * @param n
     * @return
     */
    public static int changeMoney(int n){
        int[] path = new int[n+1];
        if(n<1)
            return 0;
        path[1] = 1;
        if(n>=2)
            path[2] = 1;
        if(n>=5)
            path[5] = 1;
        for(int i=3;i<=n;i++){
            if(i==5)
                continue;
            int min = Math.min(path[i-1]+1, path[i-2]+1);
            if(i>5)
                min = Math.min(min, path[i-5]+1);
            path[i] = min;
        }
        return path[n];
    }
    
    
    /**
     * 看PDF时想起了换零钱问题（校招时某家公司的面试题），用动规写一版
     * 经典名言：动规也是一种暴力枚举解法，但它利用重复子问题的运筹学思想保存了局部计算结果，从而减少了计算量，亦是一种空间换时间的解法。
     * @param args
     */
    public static void main(String[] args){

        for(int i = 1; i<=10;i++)
            System.out.println("输入金额" + i+", 需要零钱数目："+ changeMoney(i));
        
    }
    
}
