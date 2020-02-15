package ExchangeSort;

/**
 * @author: KingHarem
 * @Date: 9 Fre, 2020 15:46
 * @Description:
 */
public class QuickSort {
    public static int[] Sort(int[] array) {
//        for (int a=0;a<array.length;a++) {
//            System.out.printf("%4d", array[a]);
//        }
//        System.out.println();
        if (array.length > 2) {
            int i = 0;
            int j = array.length - 1;
            int index = 0;
            int mid = array[0];
            while (i < j) {
                for (; j > i; j--) {
                    if (array[j] < mid) {
                        array[index] = array[j];
                        array[j] = mid;
                        index = j;
                        break;
                    }
                }
                for (; i < j; i++) {
                    if (array[i] > mid) {
                        array[index] = array[i];
                        array[i] = mid;
                        index = i;
                        break;
                    }
                }
            }
            // 递归左右子数组
            int[] subLeft = new int[index];
            for (int k = 0; k < subLeft.length; k++) {
                subLeft[k] = array[k];
            }
            subLeft = Sort(subLeft);
            int[] subRight = new int[array.length - index - 1];
            for (int k = 0; k < subRight.length; k++) {
                subRight[k] = array[index + k + 1];
            }
            subRight = Sort(subRight);

            for (int k=0;k<subLeft.length;k++) {
                array[k] = subLeft[k];
            }
            for (int k = 0; k < subRight.length; k++) {
                array[index + k + 1] = subRight[k];
            }
        }
        return array;
    }
}
