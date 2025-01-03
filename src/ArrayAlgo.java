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

}
