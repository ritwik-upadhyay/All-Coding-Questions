import java.io.*;
import java.util.*;

public class Optimised_Warehouse {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        int n = sc.nextInt();
        int k = sc.nextInt();

        long[] cost = new long[n];

        for(int i = 0; i < n; i++){
            cost[i] = sc.nextLong();
        }

        // DP array
        long[] dp = new long[n];

        // Monotonic deque (stores indexes)
        ArrayDeque<Integer> dq = new ArrayDeque<>();

        // first hub must be activated
        dp[0] = cost[0];
        dq.addLast(0);

        for(int i = 1; i < n; i++){

            /* =========================
               WRITE YOUR LOGIC HERE
               =========================

               Steps you should implement:

               1. Remove hubs from front that are out of range (distance > K)

               2. Compute dp[i] using the best previous hub

               3. Maintain monotonic deque
                  (remove worse dp values from back)

               4. Insert current index i into deque

            */
            while(!dq.isEmpty() && dq.peekFirst()<i-k) {
                dq.pollFirst();
            }
            dp[i] = cost[i] + dp[dq.peekFirst()];
            while(!dq.isEmpty() && dp[dq.peekLast()] >= dp[i])
            dq.pollLast();
            dq.addLast(i);

        }

        // last hub must be activated
        System.out.println(dp[n-1]);
    }
}