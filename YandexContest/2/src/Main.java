import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class Main {

    static class Event {
        int timeH;
        int timeM;
        int duration;
        ArrayList<String> partitions;

        Event(int timeH, int timeM, int duration) {
            this.duration = duration;
            this.timeH = timeH;
            this.timeM = timeM;
            this.partitions = new ArrayList<>();
        }

        public void addPartition(String partition) {
            partitions.add(partition);
        }

        public void printEvent(PrintWriter writer) {
            writer.format("%d:%d %d ", timeH, timeM, duration);
            for (String partition : partitions) {
                writer.format("%s ", partition);
            }
        }
    }

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        PrintWriter out = new PrintWriter(System.out);

        HashMap<String, ArrayList<Event>>[] hashMaps = new HashMap[365];
        for (int i = 0; i < 365; i++) {
            hashMaps[i] = null;
        }

        int n = in.nextInt();

        out.close();
        in.nextLine();
        for (int i = 0; i < n; i++) {
            String[] s = in.nextLine().split(" ");
            if (s[0].equals("PRINT")) {
                int day = Integer.parseInt(s[1]) - 1;
                String name = s[2];

                if (hashMaps[day] != null && hashMaps[day].containsKey(name)) {
                    for (Event event : hashMaps[day].get(name)) {
                        event.printEvent(out);
                    }
                }
            } else {
                int day = Integer.parseInt(s[1]) - 1;
                int timeH = Integer.parseInt(s[2].split(":")[0]);
                int timeM = Integer.parseInt(s[2].split(":")[1]);
                int duration = Integer.parseInt(s[3]);
                if (hashMaps[day] == null) {
                    hashMaps[day] = new HashMap<>();
                }
                Event event = new Event(timeH, timeM, duration);
                ArrayList<String> partitions = new ArrayList<>();
                for (int j = 5; j < s.length; j++) {
                    event.addPartition(s[j]);
                    partitions.add(s[j]);
                }


                partitions.forEach(partition -> {
                    if (hashMaps[day].containsKey(partition)) {
                        hashMaps[day].get(partition).add(event);
                    } else {
                        ArrayList<Event> events = new ArrayList<>();
                        events.add(event);
                        hashMaps[day].put(partition, events);
                    }
                });

            }
        }
    }
}
