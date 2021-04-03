package PrimeCheker;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.stream.Collectors;

public class Prime {

    public void checkPrime(int... N) {
        System.out.println(Arrays.stream(N).filter(this::isPrime).sorted().mapToObj(String::valueOf).collect(Collectors.joining(" ")));
//        ArrayList<Integer> integers = new ArrayList<>();
//        for (int n : N) {
//            if (isPrime(n)) {
//                integers.add(n);
//            }
//        }
//        StringBuilder output = new StringBuilder();
//        for (int n :integers) {
//            output.append(String.valueOf(n));
//            output.append(" ");
//        }
//        System.out.println(output);
    }

    private boolean isPrime(int n) {
        if (n < 2) return false;
        for (int i = 2; i < n; i++) {
            if (n % i == 0) return false;
        }
        return true;
    }
}
