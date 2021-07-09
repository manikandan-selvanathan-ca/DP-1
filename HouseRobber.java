import java.util.*;

public class HouseRobber {

    //Recursive
    //Sub Problem: Two option - Choose and Not Choose
    //Overlapping Sub Problems : Same houses combination multiple times.
    //Base condition : If we reach the end or cross all the houses.
    
    //In choose adding tgo by two steps then adding the current house's value
    //In Not choosing go by one step
    //Goes till first house.

    public int robTopDownBF(int[] house) {
        if(house == null || house.length == 0) return 0;
        return helperBF(house, house.length -1);
    }

    private int helperBF(int[] nums, int index) {
        //base 
        if(index < 0) return 0;

        //logic
        int choose = helperBF(nums, index-2) +nums[index];
        int notChoose = helperBF(nums, index-1);
        return Math.max(choose, notChoose);
    }



    //TC: O(N) -Iterating the entire houses only once
    //SC: O(N) - Memor array for caching the sub problems.
    int[] memo;
    public int robTopDownOptimal(int[] house) {
        if(house == null || house.length == 0) return 0;
        memo = new int[house.length];
        Arrays.fill(memo, -1);
        return helperOptimal(house, house.length -1,  memo);
    }

    private int helperOptimal(int[] nums, int index, int[] memo) {
        //base 
        if(index < 0) return 0;

        if(memo[index] >=0) return memo[index];

        //logic
        int choose = helperOptimal(nums, index-2, memo) +nums[index];
        int notChoose = helperOptimal(nums, index-1, memo);
        int result =  Math.max(choose, notChoose);
        memo[index] = result;
        return result;
    }

    //TC: O(N) -Iterating the entire houses only once
    //SC: O(N) - DP array for caching the sub problems.

    //First row is obevious if i'm not choosing it will be 0 and choosing the first element.
    //From second row onwards i will be checking for choosing - If i choose i can't choose the previous so i should get the maximum of previous choose and not choose
    //If not choose, I should get the value from the choosing of last house.
    private int robBottomUp(int[] house) {
        if(house == null || house.length == 0) return 0;

        int[][] dp = new int[house.length][2];
        dp[0][0] = 0;
        dp[0][1] = house[0];
        for(int i=0;i<house.length;i++) {
            dp[i][0] = Math.max(dp[i-1][0], dp[i-1][1]);
            dp[i][1] = dp[i-1][0];
        }
        return dp[house.length-1][1];
    }
    
    public static void main(String[] args) {
        HouseRobber house = new HouseRobber();
        int result = house.robBottomUp(new int[] { 2, 7, 9, 3, 1 });
        System.out.println("The result: " + result);
    }
}