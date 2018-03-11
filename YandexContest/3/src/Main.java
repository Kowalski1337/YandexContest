import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;

public class Main {

    static class Node {
        int unique;
        Node[] edges;
        boolean isTerminal;

        Node() {
            edges = new Node[26];
            isTerminal = false;
            unique = -1;
        }

        Node getVertex(int ch) {
            return edges[ch - 'a'];
        }

        Node getUniueVertex() {
            return getVertex(unique);
        }

        void addEdge(int ch) {
            edges[ch - 'a'] = new Node();
        }

        boolean containsEdge(int ch) {
            return edges[ch - 'a'] != null;
        }
    }

    private static int nextInt(BufferedReader in) throws Exception {
        int ans = 0;
        int ch = in.read();

        while (ch < '0' || ch > '9') {
            ch = in.read();
        }

        while (ch >= '0' && ch <= '9') {
            ans = 10 * ans + ch - '0';
            ch = in.read();
        }
        return ans;
    }

    private static String nextWord(BufferedReader in) throws Exception {
        StringBuilder sb = new StringBuilder();
        int ch = in.read();
        while (!Character.isLetter(ch)) {
            ch = in.read();
        }

        while (Character.isLetter(ch)) {
            sb.append(ch);
        }

        return sb.toString();
    }


    public static void main(String[] args) throws Exception {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter out = new PrintWriter(System.out);

        Node root = new Node();

        int ans = 0;
        int n = nextInt(in);
        for (int i = 0; i < n; i++) {
            Node cur = root;
            Node terminal = null;
            Node whereLeave = null;
            int dAns = 0;
            int remember = 0;
            int ch = in.read();
            while (ch < 'a' || ch > 'z') {
                ch = in.read();
            }

            while (ch >= 'a' && ch <= 'z') {
                if (terminal != null) {
                    dAns++;
                } else {
                    ans++;
                }
                if (cur.containsEdge(ch)) {
                    cur = cur.getVertex(ch);
                    if (cur.isTerminal) {
                        terminal = cur;
                    }
                } else {
                    if (whereLeave == null) {
                        whereLeave = cur;
                        remember = ch;
                    } else {
                        cur.unique = ch;
                    }
                    cur.addEdge(ch);
                    cur = cur.getVertex(ch);
                }
                ch = in.read();
            }

            if (whereLeave != null) {
                ans += dAns;
                whereLeave.getVertex(remember).isTerminal = true;
                if (terminal != null) {
                    terminal.isTerminal = false;
                    if (whereLeave.unique != -1) {
                        whereLeave.getUniueVertex().isTerminal = true;
                    }
                }
            } else {
                if (terminal != null) {
                    if (cur.unique != -1) {
                        ans += dAns;
                        cur.getUniueVertex().isTerminal = true;
                        terminal.isTerminal = false;
                    }
                }
            }

        }
        out.print(ans);
        out.close();
    }
}
