# 五大排序算法 #

# 目录 #
 - 交换排序
     - 冒泡排序
     - 快速排序
 - 选择排序
     - 选择排序
     - 堆排序
 - 插入排序
     - 插入排序
     - 希尔排序
 - 归并排序
 - 基数排序
 
 # 时间复杂度概述 #
                平均时间复杂度    最差情形       空间复杂度        稳定性
    冒泡排序       O(n^2)         O(n^2)         O(1)            稳定

    快速排序      O(nlogn)        O(n^2)        O(nlogn)        不稳定

    选择排序       O(n^2)         O(n^2)         O(1)           不稳定

    堆排序        O(nlogn)       O(nlogn)        O(1)           不稳定

    插入排序       O(n^2)         O(n^2)         O(1)            稳定

    希尔排序      O(nlogn)        O(n^s)         O(1)           不稳定

    归并排序      O(nlogn)       O(nlogn)        O(1)            稳定

    基数排序     O(nlogR(B))    O(nlogR(B))      O(n)            稳定
 
 # 交换排序 #
 ## 冒泡排序 ##
 （略）
 ## 快速排序 ##
### 步骤 ###
1. 先从数列中取出一个数作为基准数；
2. 分区过程，将比这个数大的数全放到它的右边，小于或等于它的数全放到它的左边；
3. 再对左右区间重复第二步，直到各区间只有一个数。

### Core Code ###
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

# 选择排序 #
## 选择排序 ##
（略）
## 堆排序 ##
### 堆 ###
是一种完全二叉树，但是以数组形式存放，节省空间。
> 完全二叉树中
> - 一个结点的子结点：左结点 = array[2*i+1]；右结点 = array[2*i+2]
> - 一个结点的父结点： array[i/2 -1]
### 大顶堆 ###
每个结点都大于其子结点。
### 小顶堆 ###
每个结点都小于其子结点。
### 步骤（以大顶堆为例） ###
1. 将待排序数组按照层遍历顺序放到二叉树（逻辑上，实际以下标操作）；
2. 以层序检查每个结点是否小于其子节点，若是，则交换，并递归检查子树是否满足大顶堆；
3. 交换根节点和最后一个叶节点；
4. 重复1-3步骤次数等于数组长度。
### Core Code ###
#### 步骤2（递归检查） ####

    for (int i = len/2-1; i >= 0; i--) {
        if ((2 * i + 1) < len && heap[i] < heap[2 * i + 1])    /* 根节点小于左子树 */ {
            temp = heap[i];
            heap[i] = heap[2 * i + 1];
            heap[2 * i + 1] = temp;
            /* 检查交换后的左子树是否满足大顶堆性质 如果不满足 则重新调整子树结构 */
            if ((2 * (2 * i + 1) + 1 < len && heap[2 * i + 1] < heap[2 * (2 * i + 1) + 1]) || (2 * (2 * i + 1) + 2 < len && heap[2 * i + 1] < heap[2 * (2 * i + 1) + 2])) {
                Heap(heap, len);
            }
        }
        if ((2 * i + 2) < len && heap[i] < heap[2 * i + 2])    /* 根节点小于右子树 */ {
            temp = heap[i];
            heap[i] = heap[2 * i + 2];
            heap[2 * i + 2] = temp;
            /* 检查交换后的右子树是否满足大顶堆性质 如果不满足 则重新调整子树结构 */
            if ((2 * (2 * i + 2) + 1 < len && heap[2 * i + 2] < heap[2 * (2 * i + 2) + 1]) || (2 * (2 * i + 2) + 2 < len && heap[2 * i + 2] < heap[2 * (2 * i + 2) + 2])) {
                Heap(heap, len);
            }
        }
    }

# 插入排序 #
## 插入排序 ##
将数组分为前后两端，前半段为有序数组，后半段为无序数组，不断将无序数组中元素插入到有序数组中，直到整个数组有序。
> 插入排序适用于数据量小，数组基本有序的情况。

### Core Code ###
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

## 希尔排序 ##
对数组分组再进行插入排序，分组的方式为等距采样，并且间距从array.length/2开始衰减一半至1。
### Core Code ###
#### 分组间距（gap）衰减 ####

    for (int gap=array.length;gap > 0;gap /= 2) {
        GapSort(array, gap);
    }

其中`GapSort(int[] array, int gap)`是以gap采样进行插入排序，且`ShellSort.GapSort(array, 1)=InsertSort.Sort(array)`。
#### `GapSort`中下标处理 ####

    for (int i=0;i<array.length;i++) {
        // j变化单位为gap，注意这里是递减
        for (int j=i-gap;j>=0;j -= gap) {
            if (array[j] <= array[i]) {
                int tmp = array[i];
                // 相应这里k按照gap递减
                for (int k=i;k>j;k -= gap) {
                    array[k] = array[k-gap];
                }
                array[j] = tmp;
                break;
            }
        }
    }

# 归并排序 #
归并排序需要一个与原数组相同长度的数组做辅助来排序。

## 步骤 ##
1. 递归分解数组，直到子数组只有一个元素，则每个子数组都是有序的；
2. 合并子数组，每两个等长子数组分别用两个索引记录以将该子数组哪一个元素放入合并的新数组中。

## Core Code ##
### 递归分解 ###
`Sort(int[] array, int leftPoint, int rightPoint)`：

    if(L == R) {
        return;
    }
    // mid = (R + L) / 2
    int mid = L + ((R - L) >> 1);
    sort(arr, L, mid);
    sort(arr, mid + 1, R);
    merge(arr, L, mid, R);
### 合并 ###
`merge(int[] array, int leftPoint, int midPoint, int rightPoint)`：

    int[] temp = new int[R - L + 1];
    int i = 0;
    int p1 = L;
    int p2 = mid + 1;
    // 比较左右两部分的元素，哪个小，把那个元素填入temp中
    while(p1 <= mid && p2 <= R) {
        temp[i++] = arr[p1] < arr[p2] ? arr[p1++] : arr[p2++];
    }
    // 上面的循环退出后，把剩余的元素依次填入到temp中
    // 以下两个while只有一个会执行
    while(p1 <= mid) {
        temp[i++] = arr[p1++];
    }
    while(p2 <= R) {
        temp[i++] = arr[p2++];
    }
    // 把最终的排序的结果复制给原数组
    for(i = 0; i < temp.length; i++) {
        arr[L + i] = temp[i];
    }

# 基数排序 #
## 步骤 ##
1. 将所有待比较数值统一为同样的数位长度，数位较短的数前面补零；
2. 从个位开始比较并放入对应桶中；
3. 将桶中数据复制回数组，并提高比较位数后重复1-2，直到比较完所有位。
## Core Code ##
### 建立基数表 ###
R为基数，一般为10。

    List<LinkedList<Integer>> buckets = new ArrayList<>();
    for(int i = 0 ; i < R ; i ++)
        buckets.add(new LinkedList<>());
        
### 取出对应位数上数字并放入对应桶中 ###
    for(int j = 0 ; j < a.length  ; j ++) {
        //       滤除小数    取出i+1位一下全部          i位以下全部拉到小数
        int key = (int) (a[j] % Math.pow(10, i + 1) / Math.pow(10, i));
        buckets.get(key).add(a[j]);
    }