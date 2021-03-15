package solutions.bts;

import util.SolutionsFacade;

import java.util.ArrayList;
import java.util.List;

public class LeetCode17 implements SolutionsFacade {

    /**
     * 思路：没啥好说的，直接回溯，不用剪枝。
     *
     * 注意：
     * 优化点在于StringBuilder的利用，而不是字符串累加的方式，前者在叠加次数很多时优势明显！
     *
     * @param digits
     * @return
     */
    public List<String> letterCombinations(String digits) {
        // 1. generate dicts
        String pool = "abcdefghijklmnopqrstuvwxyz";
        int step = 0 ;
        String[] dicts = new String[10];
        for(int i = 2;i<=9;i++){
            if(i==7 || i ==9){
                dicts[i] = pool.substring(step,step+4);
                step += 4;
            }
            else{
                dicts[i] = pool.substring(step,step+3);
                step += 3;
            }
        }
//        String[] dicts = new String[]{"","","abc","def","ghi","jkl","mno","pqrs","tuv","wxyz"};

        // 2. combine digits
        List<String> res = new ArrayList<>();
//        backtrack(dicts, digits, 0, "", res);
        backtrack(dicts, digits, 0, new StringBuilder(),  res);

        return res;
    }

//    public void backtrack(String[] dicts, String target, int index, String tmp, List<String> res){
    public void backtrack(String[] dicts, String target, int index, StringBuilder tmp, List<String> res){
        if(index == target.length()){
            if(tmp.length()!=0)
                res.add(tmp.toString());
            return;
        }
        int t = target.charAt(index)-'0';
        for(int i = 0;i<dicts[t].length();i++){
            tmp.append(dicts[t].charAt(i));
            backtrack(dicts, target, index+1, tmp, res);
            tmp.deleteCharAt(tmp.length()-1);
        }
    }



    @Override
    public void calculate(Object... objects) {
        List<String> res = letterCombinations((String)objects[0]);
        int t = 3;
    }
}
