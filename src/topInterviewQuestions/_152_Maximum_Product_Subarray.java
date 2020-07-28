public class _152_Maximum_Product_Subarray {
    public int maxProduct(int[] a) {
        int prd = 1, max = Integer.MIN_VALUE;
        int n = a.length;

        for (int i=0;i<n;i++) {
            prd *= a[i];
            if (prd == 0)
                prd = a[i];
            max = Math.max(prd, max);
        }

        prd = 1;
        for (int i=n-1;i>=0;i--) {
            prd *= a[i];
            if (prd == 0)
                prd = a[i];
            max = Math.max(prd, max);
        }

        return max;
    }

    // 方法二
    public int maxProduct2(int[] nums) {
        if(nums == null || nums.length == 0) return 0;
        int max = 1;
        int min = 1;
        int pro = Integer.MIN_VALUE;
        for(int i = 0; i < nums.length; i++) {
            if(nums[i] < 0){
                int tmp = max;
                max = min;
                min = tmp;
            }
            max = Math.max(nums[i], max*nums[i]);
            min = Math.min(nums[i], min*nums[i]);
            pro = Math.max(pro, max);
        }

        return pro;
    }

}
