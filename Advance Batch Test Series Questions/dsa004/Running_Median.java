import java.io.*;
import java.math.*;
import java.security.*;
import java.text.*;
import java.util.*;
import java.util.concurrent.*;
import java.util.function.*;
import java.util.regex.*;
import java.util.stream.*;
import static java.util.stream.Collectors.joining;
import static java.util.stream.Collectors.toList;

class Result {

    /*
     * Complete the 'runningMedian' function below.
     *
     * The function is expected to return a DOUBLE_ARRAY.
     * The function accepts INTEGER_ARRAY a as parameter.
     */

    public static List<Double> runningMedian(List<Integer> a) {
    // Write your code here
        PriorityQueue<Integer> lowers = new PriorityQueue<>(Collections.reverseOrder()); // max heap
    PriorityQueue<Integer> highers = new PriorityQueue<>(); // min heap

    List<Double> result = new ArrayList<>();

    for(int num : a) {

        // Step 1: Add number to correct heap
        if(lowers.isEmpty() || num < lowers.peek())
            lowers.add(num);
        else
            highers.add(num);

        // Step 2: Balance heaps
        if(lowers.size() - highers.size() > 1)
            highers.add(lowers.poll());
        else if(highers.size() - lowers.size() > 1)
            lowers.add(highers.poll());

        // Step 3: Compute median
        if(lowers.size() == highers.size())
            result.add((lowers.peek() + highers.peek()) / 2.0);
        else if(lowers.size() > highers.size())
            result.add((double) lowers.peek());
        else
            result.add((double) highers.peek());
    }

    return result;
    }
    

}

public class Running_Median {
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

        int aCount = Integer.parseInt(bufferedReader.readLine().trim());

        List<Integer> a = IntStream.range(0, aCount).mapToObj(i -> {
            try {
                return bufferedReader.readLine().replaceAll("\\s+$", "");
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        })
            .map(String::trim)
            .map(Integer::parseInt)
            .collect(toList());

        List<Double> result = Result.runningMedian(a);

        bufferedWriter.write(
            result.stream()
                .map(Object::toString)
                .collect(joining("\n"))
            + "\n"
        );

        bufferedReader.close();
        bufferedWriter.close();
    }
}
