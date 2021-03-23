package solutions.bts;

import util.SolutionsFacade;

import java.util.ArrayList;
import java.util.List;

public class LeetCode22  implements SolutionsFacade {

    /**
     * 思路：
     * 每次新增字符时，左括号可以随便加，并不影响结果，因为这只会造成欠费（结合入栈的思想）
     * 但新增右括号时，必须是欠费大于0的情况，否则势必造成最后的结果无效。
     * 如此，遵循以上规则的情况下枚举所有可能就是结果。
     * @param n
     * @return
     */
    public List<String> generateParenthesis(int n) {
        List<String> res = new ArrayList<>();
        backtrack(n, n, 0, new StringBuilder(),res);
        return res;
    }

    public void backtrack(int positive, int negative, int debt, StringBuilder sb, List<String> res){
        if(positive ==0 && negative==0){
            res.add(sb.toString());
        }
        if(positive>0){
            sb.append('(');
            backtrack(positive-1, negative, debt+1, sb, res);
            sb.deleteCharAt(sb.length()-1);
        }
        if(negative>0 && debt>0){
            sb.append(')');
            backtrack(positive, negative-1, debt-1, sb, res);
            sb.deleteCharAt(sb.length()-1);
        }
    }

    @Override
    public void calculate(Object... objects) {
        List<String> res = generateParenthesis((int )objects[0]);
        int s = 3;
    }
}
