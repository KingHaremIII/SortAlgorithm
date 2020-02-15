package InsertSortAlgorithm;

/**
 * @author: KingHarem
 * @Date: 9 Fre, 2020 12:51
 * @Description: Shell sort algorithm
 */
public class ShellSort {
    public static void Sort(int[] array) {
        for (int gap=array.length;gap > 0;gap /= 2) {
            GapSort(array, gap);
        }
    }

    public static void GapSort(int[] array, int gap) {
        for (int i=0;i<array.length;i++) {
            for (int j=i-gap;j>=0;j -= gap) {
                if (array[j] <= array[i]) {
                    int tmp = array[i];
                    for (int k=i;k>j;k -= gap) {
                        array[k] = array[k-gap];
                    }
                    array[j] = tmp;
                    break;
                }
            }
        }
    }
}
