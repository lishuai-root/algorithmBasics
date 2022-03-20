package leetcode;

/**
 * @description:
 * @author: LISHUAI
 * @createDate: 2021/8/8 13:50
 * @version: 1.0
 */

public class LeetCode_42 {

    public static void main(String[] args) {

    }

    /**
     * 遍历数组，计算当前元素可以蓄多少水
     * <p>
     * 当前元素两边最大值中最小的元素减去当前元素就是当前元素的蓄水量
     *
     * @param height
     * @return
     */
    public static int trap(int[] height) {

        int result = 0;

        int[] mLeft = new int[height.length];

        int[] mRight = new int[height.length];

        mLeft[0] = height[0];

        mRight[height.length - 1] = height[height.length - 1];

        /**
         *  计算当前元素左边最大值
         */
        for (int i = 1; i < height.length; i++) {

            mLeft[i] = Math.max(mLeft[i - 1], height[i]);
        }

        /**
         *  计算当前元素右边最大值
         */
        for (int i = height.length - 2; i >= 0; i--) {

            mRight[i] = Math.max(mRight[i + 1], height[i]);
        }

        /**
         *  计算当前元素蓄水量
         */
        for (int i = 1; i < height.length - 1; i++) {

            result += Math.max(Math.min(mLeft[i], mRight[i]) - height[i], 0);
        }

        return result;
    }


    /**
     * 动态寻找两侧最大值，
     *
     * @param height
     * @return
     */
    public static int trap_02(int[] height) {

        int result = 0;

        int left = 1, right = height.length - 2;
        /**
         * mLeft : left指针左侧的最大值
         *
         * mRight : right指针右侧最大值
         */
        int mLeft = height[0], mRight = height[height.length - 1];

        while (left <= right) {

            if (mLeft <= mRight) {

                /**
                 *  如果mLeft小于mRight说明left指针左侧的最大值已经找到，计算left指针的蓄水量
                 *
                 *  右侧还有没有找的部分，但是没找的部分里面最大值只会比mRight大
                 *
                 *  因此left两侧最大值中的较小值就是mLeft
                 *
                 *  如果mLeft和mRight相等，两侧都可以更新
                 */
                result += Math.max(mLeft - height[left], 0);

                /**
                 *  更新左侧最大值
                 */
                mLeft = Math.max(mLeft, height[left++]);
            } else {

                result += Math.max(mRight - height[right], 0);

                /**
                 *  更新右侧最大值
                 */
                mRight = Math.max(mRight, height[right--]);
            }

        }

        return result;
    }
}
