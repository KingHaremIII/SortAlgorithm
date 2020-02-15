package InsertSortAlgorithm;

/**
 * @author: KingHarem
 * @Date: 9 Fre, 2020 13:50
 * @Description:
 */
public class InsertSort {
    public static void Sort(int[] array) {
        // insert each element into the sorted array.
        for (int i=1;i<array.length;i++) {
            // search for position to insert
            for (int j=0;j<i;j++) {
                if (array[j] > array[i]) {
                    int tmp = array[i];
                    for (int k=i;k>j;k--) {
                        array[k] = array[k-1];
                    }
                    array[j] = tmp;
                    break;
                }
            }
        }
    }
}
