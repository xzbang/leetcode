/**
 * Given an integer n, return the number of trailing zeroes in n!.

 Note: Your solution should be in logarithmic time complexity.
 *
 * Created by xzbang on 2015/4/13.
 */
public class FactorialTrailingZeroes {
    public int trailingZeroes(int n) {
        return ((n<5) ? 0 : n/5+trailingZeroes(n/5));
    }

    public static void main(String[] args) {
        FactorialTrailingZeroes factorialTrailingZeroes = new FactorialTrailingZeroes();
        int n=100;
        System.out.println(factorialTrailingZeroes.trailingZeroes(n));
    }
}
