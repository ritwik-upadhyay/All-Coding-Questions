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
     * Complete the 'gridChallenge' function below.
     *
     * The function is expected to return a STRING.
     * The function accepts STRING_ARRAY grid as parameter.
     */

    public static String gridChallenge(List<String> grid) {
    // Write your code here
        int n = grid.size();
    

    List<String> sortedGrid = new ArrayList<>();
    
    for (int i = 0; i < n; i++) {
        char[] arr = grid.get(i).toCharArray();
        Arrays.sort(arr);
        sortedGrid.add(new String(arr));
    }
    
    for (int col = 0; col < sortedGrid.get(0).length(); col++) {
        
        StringBuilder s = new StringBuilder();
        
        for (int row = 0; row < n; row++) {
            s.append(sortedGrid.get(row).charAt(col));
        }
        
        if (!sorted(s.toString())) {
            return "NO";
        }
    }
    
    return "YES";

}
    public static boolean sorted(String w) {
        for(int i=0;i<w.length()-1;i++) {
            if(w.charAt(i)>w.charAt(i+1))
                return false;
        }
        return true;
    }
}

public class Grid {
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

        int t = Integer.parseInt(bufferedReader.readLine().trim());

        IntStream.range(0, t).forEach(tItr -> {
            try {
                int n = Integer.parseInt(bufferedReader.readLine().trim());

                List<String> grid = IntStream.range(0, n).mapToObj(i -> {
                    try {
                        return bufferedReader.readLine();
                    } catch (IOException ex) {
                        throw new RuntimeException(ex);
                    }
                })
                    .collect(toList());

                String result = Result.gridChallenge(grid);

                bufferedWriter.write(result);
                bufferedWriter.newLine();
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        });

        bufferedReader.close();
        bufferedWriter.close();
    }
}
