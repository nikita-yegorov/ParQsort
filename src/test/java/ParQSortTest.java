import java.util.Arrays;
import java.util.concurrent.ForkJoinPool;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;




class ParQSortTest {

    int[]GenArr() {
        int[] arr = new int[1000000];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = (int) (Math.random() * 1000);
        }
        return arr;
    }

    private int[] arr = GenArr();

    @Test
    void QSort() {
        int[] CheckingArr = arr.clone();
        QSort Qsort = new QSort();

        long start = System.currentTimeMillis();
        Qsort.sort(arr);
        long end = System.currentTimeMillis() - start;

        System.out.println("SeqVersion, Time: " + end);

        Arrays.sort(CheckingArr);
        assertArrayEquals(CheckingArr, arr);
    }


    @Test
    void QSParallel() {
        int[] CheckingArr = arr.clone();
        int t = 4;
        ParQSort ParQSort = new ParQSort(new ForkJoinPool(t));

        long start = System.currentTimeMillis();
        ParQSort.sort(arr);
        long end = System.currentTimeMillis() - start;

        System.out.println(t + " Threads; Time: "  + end);

        Arrays.sort(CheckingArr);
        assertArrayEquals(CheckingArr, arr);
    }
}