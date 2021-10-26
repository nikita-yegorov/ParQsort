import java.util.Random;

public class QSort {

    public void sort(int[] arr) {
        sort(arr, 0, arr.length - 1);
    }

    private int div(int l, int r, int[] arr) {
        int left = l;
        int right = r;
        int id = new Random().nextInt(right - left) + left;
        int temp = arr[right];
        arr[right] = arr[id];
        arr[id] = temp;
        right--;
        while (left <= right) {
            if (arr[left] <= arr[r]) {
                left++;
                continue;
            }
            if (arr[right] >= arr[r]) {
                right--;
                continue;
            }
            temp = arr[right];
            arr[right] = arr[left];
            arr[left] = temp;
            right--;
            left++;
        }
        temp = arr[right + 1];
        arr[right + 1] = arr[r];
        arr[r] = temp;
        return right + 1;
    }

    private void sort(int[] arr, int l, int r) {
        if(r <= l) return;
        int mid = div(l, r, arr);
        sort(arr, l, mid - 1);
        sort(arr, mid + 1, r);
    }
}