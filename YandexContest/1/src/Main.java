import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        PrintWriter out = new PrintWriter(System.out);

        int n = in.nextInt();

        ArrayList<Integer> a = new ArrayList<>();
        ArrayList<Integer> b = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            a.add(in.nextInt());
            b.add(in.nextInt());
        }

        double N = 0;
        for (int i = 0; i < n; i++) {
            N += a.get(i) * b.get(i);
        }

        for (int i = 0; i < n; i++) {
            System.out.println((a.get(i)*b.get(i))/N);
        }
        out.close();
    }
}
