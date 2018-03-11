import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        PrintWriter out = new PrintWriter(System.out);

        ArrayList<ArrayList<Integer>> graph = new ArrayList<>();

        int n = in.nextInt();
        int m = in.nextInt();

        for (int i = 0; i < n; i++){
            graph.add(new ArrayList<>());
        }

        for (int i = 0; i < m; i++){
            int a = in.nextInt();
            int b = in.nextInt();
            graph.get(a).add(b);
            graph.get(b).add(a);
        }


    }
}
