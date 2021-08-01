public class CoinChange {


    //TC: O(MN) where M is number of coins and N is the amount
    //SC: O(MN) for using DP Matrix.
    public int coinChange(int[] coins, int amount) {
        if (coins == null || coins.length == 0)
          return -1;

      int[][] dp = new int[coins.length + 1][amount + 1];

      // Initial fill
      for (int i = 0; i < dp[0].length; i++) {
          dp[0][i] = amount+1;
      }

      for (int i = 0; i < dp.length; i++) {
          dp[i][0] = 0;
      }
      // logic
      for (int i = 1; i < dp.length; i++) {
          for (int j = 1; j < dp[0].length; j++) {
              // case 0
              int caseOne = dp[i - 1][j];
              if (j >= coins[i - 1]) {
                  // case 1
                  int caseTwo = dp[i][j - coins[i - 1]] +1;
                  dp[i][j] = Math.min(caseOne, caseTwo);
              } else {
                  dp[i][j] = caseOne;
              }
          }
      }
      int result = dp[dp.length-1][dp[0].length-1];
      if(result >= amount+1) {
          return -1;
      }
      return result;
  }

    public int coinChangeBF(int[] coins, int amount) {
        return helper(coins, amount, 0);
    }

    private int helper(int[] coins, int amount, int count) {
        // base
        if (amount == 0)
            return count;
        if (amount < 0)
            return -1;

        // logic
        int minCount = Integer.MAX_VALUE;
        for (int coin : coins) {
            int current = helper(coins, amount - coin, count + 1);
            if (current > 0) {
                minCount = Math.min(minCount, current);
            }
        }
        return minCount == Integer.MAX_VALUE ? -1 : minCount;
    }

    public static void main(String[] args) {
        CoinChange coinChange = new CoinChange();
        int minCoins = coinChange.coinChange(new int[] { 1, 2, 5 }, 11);
        System.out.println("The minimum number of coins to make the sum: " + minCoins);
    }
}
