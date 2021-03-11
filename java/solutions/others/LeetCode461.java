package solutions.others;

public class LeetCode461 {

    /**
     * 思路：
     *  先对二者异或，然后计算结果的二进制位中1的个数。
     * @param x
     * @param y
     * @return
     */
    public int hammingDistance(int x, int y) {
        int tmp = x^y;
        int res = 0;
        // 计算tmp的二进制中1的个数
        while(tmp!=0){
            if(tmp%2==1)
                res++;
            tmp >>= 1;
        }
        return res;
    }

}
