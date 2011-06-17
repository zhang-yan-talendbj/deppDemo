package deppDemo;

/**
 * Created by IntelliJ IDEA.
 * User: Admin
 * Date: 11-6-16
 * Time: 下午4:44
 * To change this template use File | Settings | File Templates.
 * [二分查找法]
 * <p/>
 * 二分查找法
 * 二分查找又称折半查找，它是一种效率较高的查找方法。
 * 【二分查找要求】：1.必须采用顺序存储结构
 *                 2.必须按关键字大小有序排列。
 * 【优缺点】折半查找法的优点是比较次数少，查找速度快，平均性能好;
 *          其缺点是要求待查表为有序表，且插入删除困难。
 *          因此，折半查找方法适用于不经常变动而查找频繁的有序列表。
 * 【算法思想】首先，假设表中元素是按升序排列，将表中间位置记录的关键字与查找关键字比较，如果两者相等，则查找成功；
 *            否则利用中间位置记录将表分成前、后两个子表，如果中间位置记录的关键字大于查找关键字，
 *            则进一步查找前一子表，否则进一步查找后一子表。
 *            重复以上过程，直到找到满足条件的记录，使查找成功，或直到子表不存在为止，此时查找不成功。
 */
public class BinarySearch {
    /**
     * 二分查找算法
     *
     * @param srcArray 有序数组
     * @param des      查找元素
     * @return des的数组下标，没找到返回-1
     */


    public static int binarySearch(int[] srcArray, int des)

    {
        for(int i=0;i<srcArray.length;i++)
        {
            int begin=0;
            int end=srcArray.length;
            int middle=(begin+end)/2;
            while(middle<end){

            }
        }
       return -1;
    }


    public static void main(String[] args)

    {
        int[] src = new int[]{1, 3, 5, 7, 8, 9};
        System.out.println(binarySearch(src, 3));
    }


}