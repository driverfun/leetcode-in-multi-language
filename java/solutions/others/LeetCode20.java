package solutions.others;

import java.util.Stack;

public class LeetCode20 {

    /**
     * 判断括号是否有效，看一眼就是栈，我是否以前做过？
     * @param s
     * @return
     */
    public boolean isValid(String s) {
        Stack<Character> stack = new Stack<>();
        for(int i =  0;i< s.length();i++){
            if(s.charAt(i)=='('|| s.charAt(i)=='{' || s.charAt(i)=='['){
                stack.push(s.charAt(i));
            }else if(s.charAt(i)==')' && stack.size()>0 && stack.peek()=='('){
                stack.pop();
            }else if(s.charAt(i)=='}' && stack.size()>0 && stack.peek()=='{'){
                stack.pop();
            }else if(s.charAt(i)==']' && stack.size()>0 && stack.peek()=='[') {
                stack.pop();
            }else
                return false;
        }
        if(stack.size()==0)
            return true;
        return false;
    }
}
