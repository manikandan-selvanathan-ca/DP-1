public class LongestPalidromicSubstring {
    public String longestPalindrome(String s) {
        int n = s.length();
        if(n<=1) return s;
        
        boolean[][] dp = new boolean[n][n];
        
        for(int i=0;i<n;i++) {
            dp[i][i] = true;
        }
        
        int maxLength = 1;
        int maxStart = 0;
        
        for(int i=n-1;i>=0;i--) {
            for(int j=i+1;j<n;j++) {
                if(s.charAt(i) == s.charAt(j)) {
                    int length = j-i;
                    if(length == 1 || dp[i+1][j-1]) {
                        dp[i][j] = true;
                        if(length+1 > maxLength) {
                            maxStart = i;
                            maxLength = length+1;
                        }
                    }
                }
            }
        }
        
        
        return s.substring(maxStart, maxStart+maxLength);
        
    }

    public static void main(String[] args) {
        LongestPalidromicSubstring longestPalidromicSubstring = new LongestPalidromicSubstring();
        String result = longestPalidromicSubstring.longestPalindrome("babad");
        System.out.println("The result: "+result);
    }
}
