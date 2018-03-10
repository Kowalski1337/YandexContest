import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;

public class Main {

    static class Node {
        Node[] edges;
        boolean isTerminal;

        Node(boolean isTerminal) {
            edges = new Node[26];
            this.isTerminal = isTerminal;
        }

        Node getVertex(int ch) {
            return edges[ch - 'a'];
        }

        void addEdge(int ch, boolean isTerminal) {
            edges[ch - 'a'] = new Node(isTerminal);
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

        Node root = new Node(false);

        int ans = 0;
        int n = nextInt(in);
        for (int i = 0; i < n; i++) {
            Node cur = root;
            int ch = in.read();
            int dAns = 0;
            boolean wasInTerminal = false;
            Node terminalVertex;
            while (ch < 'a' || ch > 'z') {
                ch = in.read();
            }

            while (ch >= 'a' && ch <= 'z') {
                ans++;
                if (cur.containsEdge(ch)) {
                    cur = cur.getVertex(ch);
                    if (cur.isTerminal) {
                        wasInTerminal = true;
                        break;
                    }
                } else {
                    break;
                }
                ch = in.read();
            }
            ch = in.read();

            if (wasInTerminal) {
                while (ch >= 'a' && ch <= 'z') {
                    dAns++;
                    if (cur.containsEdge(ch)) {
                        cur = cur.getVertex(ch);
                    } else {
                        for (int j = 0; j < 26; j++) {
                            if (cur.edges[j] != null) {
                                cur.edges[j].isTerminal = true;
                                break;
                            }
                        }
                        ans += dAns;
                        wasInTerminal = false;
                        break;
                    }
                    ch = in.read();
                }
            }

            if (!wasInTerminal) {
                cur.addEdge(ch, true);
                cur = cur.getVertex(ch);
                ch = in.read();
                while (ch >= 'a' && ch <= 'z') {
                    ans++;
                    cur.addEdge(ch, false);
                    cur = cur.getVertex(ch);
                    ch = in.read();
                }
            }
        }
        out.print(ans);
        out.close();
    }
}
