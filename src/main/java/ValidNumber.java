/**
 * https://leetcode.com/problems/valid-number/
 *
 Validate if a given string is numeric.

 Some examples:
 "0" => true
 " 0.1 " => true
 "abc" => false
 "1 a" => false
 "2e10" => true
 Note: It is intended for the problem statement to be ambiguous.
 You should gather all requirements up front before implementing one.

 Update (2015-02-10):
 The signature of the C++ function had been updated.
 If you still see your function signature accepts a const char * argument,
 please click the reload button  to reset your code definition.
 *
 *解题思路：语言相关
 * 先小写化和去前后空白字符
 * '+'和'-'只能出现在第一个或者e后的第一个；
 * 必须在e前和e后都有数字；
 * '.'出现时必须没有出现过'.'和'e'；
 * 'e'出现时必须没有出现过'e'和已经出现过数字；
 *
 * Created by xzbang on 2015/5/25.
 */
public class ValidNumber {
    public boolean isNumber(String s) {
        if(s==null||s.length()==0)return false;
        boolean point = false;
        boolean e = false;
        boolean number = false;
        s=s.toLowerCase().trim();
        int len = s.length();
        for(int i = 0;i < len;i++){
            char ch = s.charAt(i);
            if('0'<=ch&&ch<='9'){
                number=true;
            }else if(ch=='.'){
                if(point||e)
                    return false;
                else
                    point=true;
            }else if(ch=='e'){
                if(e||!number)//e9 is error
                   return false;
                else {
                    e = true;
                    number=false;
                }
            }else if(ch=='-'||ch=='+'){
                if(i!=0&&s.charAt(i-1)!='e')
                    return false;
            }else
                return false;
        }
        return number;
    }

    public static void main(String[] args) {
        ValidNumber validNumber = new ValidNumber();
        String s = "e7";
        System.out.println(validNumber.isNumber(s));
    }
}
