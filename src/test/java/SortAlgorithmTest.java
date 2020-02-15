import ExchangeSort.QuickSort;
import InsertSortAlgorithm.InsertSort;
import InsertSortAlgorithm.ShellSort;
import SelectSortAlgorithm.HeapSort;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * @author: KingHarem
 * @Date: 9 Fre, 2020 12:52
 * @Description: Test sort algorithm
 */
public class SortAlgorithmTest {
    long start;
    long end;
    private int[] waitingSort;

    @Before
    public void Preparation() {
        waitingSort = new int[5000];
        for (int cc=0;cc<waitingSort.length;cc++) {
            waitingSort[cc] = (int)(Math.random()*1001);
        }
        System.out.println("Array for sort: ");
        for (Integer i : waitingSort)
            System.out.printf("%4d", i);
        System.out.println();
        start = System.currentTimeMillis();
    }

    @Test
    public void QuickTest() {
        System.out.println("Quick");
        QuickSort.Sort(waitingSort);
    }

    @Test
    public void InsertTest() {
        System.out.println("Insert");
        InsertSort.Sort(waitingSort);
    }

    @Test
    public void ShellTest() {
        System.out.println("Shell");
//        ShellSort.GapSort(waitingSort, 1);
        ShellSort.Sort(waitingSort);
    }

    @Test
    public void HeapTest() {
        System.out.println("Heap");
        for (int i=waitingSort.length;i>0;i--) {
            HeapSort.Heap(waitingSort, i);
            HeapSort.Switch(waitingSort, i);
        }
    }

    @Test
    public void ConvergeTest() {
        System.out.println("Converge Sort");
        ConvergeSort.Sort(waitingSort);
    }

    @Test
    public void RadixTest() {
        System.out.println("Radix");
        RadixSort rs = new RadixSort(waitingSort);
        rs.radixSort();
    }

    @After
    public void Reset() {
        end = System.currentTimeMillis();
        System.out.println("Array sorted: ");
        for (Integer i : waitingSort)
            System.out.printf("%4d", i);
        System.out.println();
        System.out.println("Time Consumption: "+(end - start));
        System.out.println();
    }
}
