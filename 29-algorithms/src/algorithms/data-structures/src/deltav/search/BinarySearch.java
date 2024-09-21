package deltav.search;

public class BinarySearch {
    public static int binarySearch(int[] nums, int target) {
        int i = 0, j = nums.length - 1;
        while (i <= j) {
            int med = i + (j - i) / 2;
            if (nums[med] > target)
                j = med - 1;
            else if (nums[med] < target)
                i = med + 1;
            else
                return med;
        }
        return -1;
    }

    public static void main(String[] args) {
        int[] nums = new int[]{0, 1, 3, 5, 7, 9, 11, 13, 15, 17, 19, 23, 25, 29, 31};
        // 11
        int targetIndex = BinarySearch.binarySearch(nums, 3);
        System.out.println("targetIndex = " + targetIndex);
    }
}
