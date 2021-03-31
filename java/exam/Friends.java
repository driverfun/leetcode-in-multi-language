package exam;

public class Friends {

    public boolean isGoodFriends (String string1, String string2) {
        // write code here
        if(string1.equals(string2)|| string1.length()!=string2.length())
            return false;

        // 设置一个哈希表，asc码32-126，即空格-波浪号之间是所有的可打印字符。
        // 126-32+1= 95
        int[] hash = new int[95];
        for(int i=0;i<string1.length();i++){
            hash[string1.charAt(i)-' '] ++;
        }
        for(int i=0;i<string2.length();i++){
            int d = string2.charAt(i)-' ';
            if(hash[d]>0)
                hash[d]--;
            else
                return false;
        }
        for(int s = 0;s<hash.length;s++){
            if(hash[s]>0)
                return false;
        }
        return true;
    }

    public static void main(String[] args){
        Friends f = new Friends();
        boolean res = f.isGoodFriends("abcerd", "bdcfea");
        int s =3;
    }

}
