package solutions.bts;

import util.SolutionsFacade;

import java.util.ArrayList;
import java.util.List;

public class LeetCode17 implements SolutionsFacade {

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
        backtrack(dicts, digits, 0, "", res);

        return res;
    }

    public void backtrack(String[] dicts, String target, int index, String tmp, List<String> res){
        if(index == target.length()){
            if(!tmp.equals(""))
                res.add(tmp);
            return;
        }
        for(int i = 0;i<dicts[target.charAt(index)-'0'].length();i++){
            backtrack(dicts, target, index+1, tmp+dicts[target.charAt(index)-'0'].charAt(i), res);
        }
    }



    @Override
    public void calculate(Object... objects) {
        List<String> res = letterCombinations((String)objects[0]);
        int t = 3;
    }
}
