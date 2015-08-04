package algorithm;

/**
 * 字符串匹配算法：寻找字符串s是否包含在字符串t中，如果包含，返回起始偏移量，否则返回-1
 *
 *两个可以改善效率的地方（获取next数组的时候）：
 * 1、当前匹配长度为next值，即跳转值（下一个位置的匹配位置）；
 * 2、从前往后匹配，如果遇到相同的字母，将他们的跳转位置也设为相同，
 * 否则设为子串中当前匹配位置的偏移量（即上个位置跳到哪里，现在就跳到哪里）
 *
 * Created by xzbang on 2015/7/2.
 */
public class KMP {
    public void getNext(String s,int[] next){
        int n = s.length();
        next[0]=-1;
        int i=0,j=-1;
        while(i<n){
            if(j==-1||s.charAt(i)==s.charAt(j)){
                ++i;
                ++j;
                if(i<n)break;//注意可能越界
                if(s.charAt(i)!=s.charAt(j)){
                    next[i]=j;
                }else{
                    next[i]=next[j];
                }
            }else{
                j=next[j];
            }
        }
    }

    public int match(String t,String s){

        int m = t.length();
        int n = s.length();
        int[] next = new int[n];
        getNext(s,next);
        int i = 0,j = -1;
        while(i<m&&j<n){
            if(j==-1||t.charAt(i)==s.charAt(j)){
                ++i;
                ++j;
            }else{
                j=next[j];
            }
        }
        if(j==n) {
            return i - j;
        }else{
            return -1;
        }
    }

    public static void main(String[] args) {
        String t = "abababaacbac";
        String s = "abaaacba";
        KMP kmp = new KMP();
        System.out.println(kmp.match(t,s));
    }
}
