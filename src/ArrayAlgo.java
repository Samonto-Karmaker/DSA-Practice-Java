public class ArrayAlgo {

    // Time complexity: O(n), Space complexity: O(1)
    // boyer-moore voting algorithm
    public static int moreThanHalf(int[] arr) {
        int idx = 0;
        int freq = 1;
        for (int i = 1; i < arr.length; i++) {
            if (arr[i] == arr[idx]) {
                freq++;
            } else {
                freq--;
            }
            if (freq == 0) {
                idx = i;
                freq = 1;
            }
        }

        freq = 0;
        for (int j : arr) {
            if (j == arr[idx]) {
                freq++;
            }
        }

        return freq > arr.length / 2 ? arr[idx] : -1;
    }

    // Time complexity: O(n), Space complexity: O(1)
    // kadane's algorithm
    // but this algorithm can't handle all negative numbers
    public static int maxSumSubArray(int[] arr) {
        int maxSum = Integer.MIN_VALUE;
        int sum = 0;
        for (int i : arr) {
            sum += i;
            maxSum = Math.max(maxSum, sum);
            if (sum < 0) {
                sum = 0;
            }
        }
        return maxSum;
    }

    // Stock buy and sell, 1 transaction
    // Time complexity: O(n), Space complexity: O(1)
    // Intuition: find the minimum price and try to sell at each price
    public static int maxProfit1(int[] prices) {
        int minPrice = Integer.MAX_VALUE;
        int maxProfit = 0;
        for (int price : prices) {
            minPrice = Math.min(minPrice, price);
            maxProfit = Math.max(maxProfit, price - minPrice);
        }
        return maxProfit;
    }

    // Stock buy and sell, infinite transactions
    // Time complexity: O(n), Space complexity: O(1)
    // Intuition: buy at valley and sell at peak
    public static int maxProfit2(int[] prices) {
        int maxProfit = 0;
        for (int i = 1; i < prices.length; i++) {
            maxProfit += Math.max(0, prices[i] - prices[i - 1]); // Only sell when profit is positive
        }
        return maxProfit;
    }

    // Trapping Rain Water I
    // Time complexity: O(n), Space complexity: O(n)
    // Intuition: find the max height on the left and right of each element
    // and the water trapped at each element is min(leftMax, rightMax) - height
    public static int trappingRainWater(int[] arr) {
        int n = arr.length;
        int[] left = new int[n];
        int[] right = new int[n];
        int ans = 0;

        left[0] = arr[0];
        for (int i = 1; i < n; i++) {
            left[i] = Math.max(left[i - 1], arr[i]); // find the max height on the left
        }

        right[n - 1] = arr[n - 1];
        for (int i = n - 2; i >= 0 ; i--) {
            right[i] = Math.max(right[i + 1], arr[i]); // find the max height on the right
        }

        for (int i = 0; i < n; i++) {
            ans += Math.min(left[i], right[i]) - arr[i];
        }

        return ans;
    }
}
