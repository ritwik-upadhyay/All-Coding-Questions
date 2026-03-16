class Remove_Element {
    public int removeElement(int[] nums, int val) {
        int n = nums.length;
        int slow = 0;
        int k = 0;
        for(int fast=0;fast<n;fast++) {
            if(nums[fast]!=val) {
                int temp = nums[fast];
                nums[fast] = nums[slow];
                nums[slow] = temp;
                slow++;
                k++;
            }
        }
        return k;
    }
}