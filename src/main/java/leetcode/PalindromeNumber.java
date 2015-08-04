package leetcode;

/**
 *  https://leetcode.com/problems/palindrome-number/
 *
 * Determine whether an integer is a palindrome. Do this without extra space.
 *
 * Created by xzbang on 2015/4/13.
 */
public class PalindromeNumber {
    public boolean isPalindrome(int x) {
        if(x<0){
            return false;
        }
        if(x % 10 == 0 && x != 0){
            return false;
        }
        int a = 0;
        while(a<x){
            a=a*10+x%10;
            x=x/10;
        }
        return a==x||a/10==x;
    }

    public static void main(String[] args) {
        int x=1011001;
        PalindromeNumber solution = new PalindromeNumber();
        System.out.println(solution.isPalindrome(x));
    }
}
