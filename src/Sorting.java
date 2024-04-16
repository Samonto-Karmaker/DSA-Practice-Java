public class Sorting {

    public void bubbleSort(int[] arr) {
        int n = arr.length;
        for (int i = 0; i < n - 1; i++) {
            for (int j = 0; j < n - 1 - i; j++) {
                if (arr[j] > arr[j + 1]) {
                    int temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;
                }
            }
        }
    }
    public void selectionSort(int[] arr) {
        int n = arr.length;
        for (int i = 0; i < n - 1; i++) {
            int minIndex = i;
            int min = arr[i];
            for (int j = i + 1; j < n; j++) {
                if (arr[j] < min) {
                    min = arr[j];
                    minIndex = j;
                }
            }
            arr[minIndex] = arr[i];
            arr[i] = min;
        }
    }
    public void insertionSort(int[] arr) {
        int n = arr.length;
        for (int i = 1; i < n; i++) {
            int key = arr[i];
            int j = i - 1; // Last element of the sorted part of the array
            // finding the right position of key in the sorted part of the array
            while (j >= 0 && arr[j] > key) {
                arr[j + 1] = arr[j];
                j--;
            }
            arr[j + 1] = key;
        }
    }

    public void mergeSort(int[] arr) {
        mergeSort(arr, 0, arr.length - 1);
    }
    private void mergeSort(int[] arr, int left, int right) {
        if (left < right) {
            int middle = (left + right) / 2;
            mergeSort(arr, left, middle);
            mergeSort(arr, middle + 1, right);
            merge(arr, left, middle, right);
        }
    }
    private void merge(int[] arr, int l, int m, int r) {
        int[] temp = new int[arr.length];
        int i = l, j = m + 1, k = l;
        while (i <= m && j <= r) {
            if (arr[i] < arr[j]) {
                temp[k] = arr[i];
                i++;
            } else {
                temp[k] = arr[j];
                j++;
            }
            k++;
        }
        while (i <= m) {
            temp[k] = arr[i];
            k++;
            i++;
        }
        while (j <= r) {
            temp[k] = arr[j];
            k++;
            j++;
        }
        if (r + 1 - l >= 0) System.arraycopy(temp, l, arr, l, r + 1 - l);
    }

    public void quickSort(int[] arr) {
        quickSort(arr, 0, arr.length - 1);
    }
    private void quickSort(int[] arr, int left, int right) {
        if (left < right) {
            int pivot = partition(arr, left, right);
            quickSort(arr, left, pivot - 1);
            quickSort(arr, pivot + 1, right);
        }
    }
    private int partition(int[] arr, int left, int right) {
        int pivot = arr[left];
        int i = left, j = right;
        while (i < arr.length && j >= 0 && i < j) {
            while (i < arr.length && arr[i] <= pivot) i++;
            while (j >= 0 && arr[j] > pivot) j--;
            if (i < arr.length && j >= 0 && i < j) {
                int temp = arr[i];
                arr[i] = arr[j];
                arr[j] = temp;
            }
        }
        arr[left] = arr[j];
        arr[j] = pivot;
        return j;
    }

}
