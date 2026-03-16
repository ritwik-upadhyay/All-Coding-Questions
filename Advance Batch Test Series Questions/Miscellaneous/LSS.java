import java.io.*;
import java.util.*;

public class LSS {

    public static String LSS(int n, int k, int[] nums)
    {
        ArrayDeque<Integer> res = new ArrayDeque<>();
        int i = 0;
        while(i<n) {
            while(!res.isEmpty() && k>0 && nums[i]<res.peekLast()) {
                res.pollLast();
                k--;
            }
            res.add(nums[i]);
            i++;
        }
        while(k>0) {
    res.pollLast();
    k--;
}
        String s = "";
        while(!res.isEmpty()) {
            s = s + res.poll() + " ";
        }
        return s;
    }
    
    public static void main(String[] args) {
       Scanner sc = new Scanner(System.in);
        if (!sc.hasNextInt()) return;
        
        int n = sc.nextInt();
        int k = sc.nextInt();
        int[] nums = new int[n];
        for (int i = 0; i < n; i++) {
            nums[i] = sc.nextInt();
        }
        
       System.out.println( LSS(n, k, nums));
        
    }
}