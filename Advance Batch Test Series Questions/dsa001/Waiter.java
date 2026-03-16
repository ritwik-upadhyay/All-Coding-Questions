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
     * Complete the 'waiter' function below.
     *
     * The function is expected to return an INTEGER_ARRAY.
     * The function accepts following parameters:
     *  1. INTEGER_ARRAY number
     *  2. INTEGER q
     */

    public static List<Integer> waiter(List<Integer> number, int q) {
    // Write your code here
        Stack<Integer> pile = new Stack<>();
        for(int n : number) {
            pile.push(n);
        }
        List<Integer> prime = prime(q);
        int i = 0;
        Stack<Integer> answers = new Stack<>();
        while(q!=0) {
            Stack<Integer> A = new Stack<>();
            Stack<Integer> B = new Stack<>();
            while(!(pile.isEmpty())) {
                int n = pile.pop();
                if(n%prime.get(i)==0) {
                    B.push(n);
                }
                else {
                    A.push(n);
                }
            }
            while(!(B.isEmpty())) {
                answers.push(B.pop());
            }
            pile = A;
            i++;
            q--;
        }
        while(!(pile.isEmpty())) {
            answers.push(pile.pop());
        }
        List<Integer> result = new ArrayList<>();
        while(!(answers.isEmpty())) {
            pile.push(answers.pop());
        }
        while(!(pile.isEmpty())) {
            result.add(pile.pop());
        }
        return result;

    }
    public static List<Integer> prime(int q) {
    List<Integer> prime = new ArrayList<>();
    int n = 2;
    prime.add(n);
    while(prime.size()!=q) {
        n++;
        boolean r = checkPrime(n);
        if(r==true) prime.add(n);
    }
    return prime;
}

public static boolean checkPrime(int n) {
    int c = 0;
    for(int i=1;i<=n;i++) {
        if(n%i==0) {
            c++;
        }
    }
    if(c==2) {
        return true;
    }
    return false;
}
    
}
    

public class Waiter {
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

        String[] firstMultipleInput = bufferedReader.readLine().replaceAll("\\s+$", "").split(" ");

        int n = Integer.parseInt(firstMultipleInput[0]);

        int q = Integer.parseInt(firstMultipleInput[1]);

        List<Integer> number = Stream.of(bufferedReader.readLine().replaceAll("\\s+$", "").split(" "))
            .map(Integer::parseInt)
            .collect(toList());

        List<Integer> result = Result.waiter(number, q);

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
