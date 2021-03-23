package solutions.bts;

import util.SolutionsFacade;

public class LeetCode79 implements SolutionsFacade {

    private int hang;
    private int lie;
    private boolean res;

    /**
     * 利用一张跟原数组同等大小的辅助二维表记录走过的路径。
     * @param board
     * @param word
     * @return
     */
    public boolean exist(char[][] board, String word) {
        hang = board.length;
        lie = board[0].length;
        res = false;
        boolean[][] helper = new boolean[hang][lie];
        for(int i =0 ;i<hang;i++)
            for(int j = 0;j<lie;j++)
                if(word.length()>0 && word.charAt(0)==board[i][j]){
                    backtrack(board, i, j, word, 1, helper);
                    if(res==true)
                        return res;
                }
        return res;
    }

    public void backtrack(char[][] board, int i, int j,String word, int idx, boolean[][] path){
        if(idx==word.length()){
            res = true;
            return;
        }
        // 添加当前节点
        path[i][j]=true;
        // 寻找下一节点（可能性有多个）
        if(i-1>=0 && path[i-1][j]==false && word.charAt(idx)==board[i-1][j] && !res)
            backtrack(board, i-1, j, word, idx+1, path);
        if(i+1<hang && path[i+1][j]==false && word.charAt(idx)==board[i+1][j] && !res)
            backtrack(board, i+1, j, word, idx+1, path);
        if(j-1>=0 && path[i][j-1]==false && word.charAt(idx)==board[i][j-1] && !res)
            backtrack(board, i, j-1, word, idx+1, path);
        if(j+1<lie && path[i][j+1]==false && word.charAt(idx)==board[i][j+1] && !res)
            backtrack(board, i, j+1, word, idx+1, path);
        // 清除当前节点
        path[i][j]=false;
    }


    @Override
    public void calculate(Object... objects) {
        boolean res = exist((char[][])objects[0], (String)objects[1]);
        int s = 3;
    }
}
