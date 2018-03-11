import java.io.PrintWriter;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        PrintWriter out = new PrintWriter(System.out);

        long k = in.nextInt();
        long m = in.nextInt();
        long d = in.nextInt() - 1;

        long a = 0;

        for (; d < 7; d++) {
            if (m + (d < 5 ? k : 0) - a - 1 >= 0) {
                m += (d < 5 ? k : 0) - a - 1;
                a++;
            } else {
                out.print(a);
                out.close();
                return;
            }
        }

        long l = 0;
        long r = (long) 1e13;

        if (m + k < a + 1) {
            out.print(a);
            out.close();
            return;
        }

        while (l < r - 1) {
            long mid = (long)(l + (r - l) / 2);
            if ((double)(2 * a + 1 + 7 * mid) * 7 * mid / 2 >= (double)5 * k * mid + m) {
                r = mid;
            } else {
                l = mid;
            }
        }
        m += 5.0 * k * l - (double)((2 * a + 7 * l + 1) * 7 * l )/ 2;
        a += l * 7;

        for (int i = 0; i < 7; i++){
            if (m + (i < 5 ? k : 0) - a - 1 >= 0) {
                m += (i < 5 ? k : 0) - a - 1;
                a++;
            } else {
                break;
            }
        }

        out.print(a);
        out.close();
    }
}
