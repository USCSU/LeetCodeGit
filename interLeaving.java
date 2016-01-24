package LeetCode;

/**
 * Created by Trace_route on 12/14/14.
 */
public class interLeaving {
    public static boolean isInterleave(String s1, String s2, String s3) {
        if(s1.length()+s2.length()!=s3.length()) return false;
        return helper(s1,s2,s3,0,0,0);
    }
    public static boolean helper(String s1,String s2,String s3,int i, int j, int k){
        if(i==s1.length() && j ==s2.length() && k==s3.length()) return true;
        boolean match = false;
        if(i<s1.length() && s1.charAt(i) == s3.charAt(k)){
            match = helper(s1,s2,s3,i+1,j,k+1);
        }
        if(!match && j<s2.length() && s2.charAt(j) == s3.charAt(k)){
            match = helper(s1,s2,s3,i,j+1,k+1);
        }
        return match;
    }
    public static boolean isleaving(String s1, String s2, String s3){
        if(s1 == null||s2==null|s3 ==null) return false;
        if(s1.length()+s2.length()!=s3.length()) return false;
        return isLeavingHelper(s1,0,s2,0,s3,0);
    }
    public static boolean isLeavingHelper(String s1, int i, String s2, int j, String s3, int k){
        if(i == s1.length() && j ==s2.length()&&k==s3.length()) return true;
        boolean ret=false;
        if(i<s1.length() && s1.charAt(i)==s3.charAt(k))
            ret = isLeavingHelper(s1,i+1,s2,j,s3,k+1);
        if(!ret && j<s2.length()&&s2.charAt(j)==s3.charAt(k))
            ret = isLeavingHelper(s1,i,s2,j+1,s3,k+1);
        return ret;
    }
    public static boolean isInterLeave(String s1, String s2, String s3) {
        if(s1.length()+s2.length()!=s3.length()) return false;
        if(s1==null||s2==null||s3==null) return false;
        boolean [][] dp = new boolean[s1.length()+1][s2.length()+1];
        dp[0][0] = true;
        for(int i =1;i<s1.length()+1;i++){
            if(s1.charAt(i-1) == s3.charAt(i-1) && dp[i-1][0])
                dp[i][0] = true;
        }

        for(int j=1;j<s2.length()+1;j++){
            if(s2.charAt(j-1) == s3.charAt(j-1) && dp[0][j-1])
                dp[0][j] = true;
        }

        for(int i = 1;i<s1.length()+1;i++){
            for(int j=1;j<s2.length()+1;j++){
                if(s1.charAt(i-1) == s3.charAt(i+j-1) && dp[i-1][j])
                    dp[i][j] = true;
                if(s2.charAt(j-1) == s3.charAt(i+j-1) && dp[i][j-1])
                    dp[i][j] = true;
            }
        }

        for(int i = 0;i<s1.length()+1;i++){
            System.out.println();
            for(int j=0;j<s2.length()+1;j++) {
                System.out.print(dp[i][j]?" T ":" F ");
            }
        }
        return dp[s1.length()][s2.length()];
    }
    public static boolean interleavingDp(String s1, String s2, String s3){
        if(s1.length()+s2.length()!=s3.length()) return false;
        if(s1==null||s2==null||s3==null) return false;
        boolean [][] dp = new boolean[s1.length()+1][s2.length()+1];
        dp[0][0] = true;
        for(int i =1;i<dp.length;i++)
            dp[i][0] = s1.charAt(i-1)==s3.charAt(i-1) && dp[i-1][0];

        for(int i=1;i<dp[0].length;i++)
            dp[0][i] = s2.charAt(i-1)==s3.charAt(i-1)&&dp[0][i-1];

        for(int i=1;i<dp.length;i++){
            for (int j =1;j<dp[0].length;j++){
                if(s1.charAt(i-1) == s3.charAt(i+j-1) && dp[i-1][j]) dp[i][j] = true;
                if(s2.charAt(j-1) == s3.charAt(i+j-1) && dp[i][j-1]) dp[i][j] = true;
            }
        }
        return dp[s1.length()][s2.length()];
    }
    public static void main(String[] args){
        System.out.println(isInterleave("aabcc","dbbca","aadbbcbcac"));
        System.out.println(isleaving("aabcc","dbbca","aadbbcbcac"));
        isInterLeave("aabcc","dbbca","aadbbcbcac");
        System.out.println(interleavingDp("aabcc","dbbca","aadbbcbcac"));
    }
}
