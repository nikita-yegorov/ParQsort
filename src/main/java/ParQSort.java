import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveTask;
import java.util.Random;


public class ParQSort {

    private final ForkJoinPool forkJoinPool;

    public ParQSort(ForkJoinPool forkJoinPool) {
        this.forkJoinPool = forkJoinPool;
    }

    public void sort(int[] arr){
        forkJoinPool.invoke(new ParQSortTask(0, arr.length - 1, arr));
    }

    public static class ParQSortTask extends RecursiveTask<Integer> {

        private final int[] arr;
        private final int l;
        private final int r;


        public ParQSortTask(int l, int r, int[] arr) {
            this.arr = arr;
            this.l = l;
            this.r = r;
        }

        private int div(int l, int r, int[] arr) {
            int left = l;
            int right = r;
            int index = new Random().nextInt(right - left) + left;
            int temp = arr[right];
            arr[right] = arr[index];
            arr[index] = temp;
            right--;
            while (left <= right) {
                if (arr[right] >= arr[r]) {
                    right--;
                    continue;
                }
                if (arr[left] <= arr[r]) {
                    left++;
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

        @Override
        protected Integer compute() {
            if (l >= r) return null;
            int mid = div(l, r, arr);
            ParQSortTask left = new ParQSortTask(this.l, mid - 1, arr);
            ParQSortTask right = new ParQSortTask(mid + 1, this.r, arr);
            left.fork();
            right.compute();
            left.join();
            return null;
        }
    }
}