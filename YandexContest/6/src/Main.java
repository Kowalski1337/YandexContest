import com.sun.xml.internal.bind.v2.runtime.reflect.Lister;
import javafx.util.MyPair;

import java.awt.*;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class Main {

    static class MyPair {
        int first;
        int second;

        MyPair(int first, int second) {
            this.first = first;
            this.second = second;
        }

        int getKey() {
            return first;
        }

        int getValue() {
            return second;
        }

        void setFirst(int first) {
            this.first = first;
        }

        void setSecond(int second) {
            this.second = second;
        }
    }

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        PrintWriter out = new PrintWriter(System.out);

        int n = in.nextInt();
        ArrayList<MyPair> part = new ArrayList<>();

        int[] a = new int[n];
        int[] b = new int[n];

        for (int i = 0; i < n; i++) {
            a[i] = in.nextInt();
        }

        for (int i = 0; i < n; i++) {
            b[i] = in.nextInt();
        }
        int curAns = 0;

        for (int i = 0; i < n / 2; i++) {
            curAns += part.get(i).first;
        }

        for (int i = n / 2; i < n; i++) {
            curAns += part.get(i).second;
        }

        for (int i = 0; i < m; i++) {
            int name = in.nextInt();
            int what = in.nextInt();

        }

    }
}
