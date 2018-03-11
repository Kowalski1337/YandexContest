import java.io.PrintWriter;
import java.sql.Time;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Scanner;

public class Main {

    static class EventTime {
        int hours;
        int minutes;
        int duration;

        EventTime(int hours, int minutes, int duration) {
            this.hours = hours;
            this.minutes = minutes;
            this.duration = duration;
        }

        int getTimeInMinutes() {
            return hours * 60 + minutes;
        }

        boolean compareTo(EventTime eventTime) {
            return this.getTimeInMinutes() >= eventTime.getTimeInMinutes() + eventTime.duration ||
                    eventTime.getTimeInMinutes() >= this.getTimeInMinutes() + this.duration;
        }

    }

    static class Event {
        EventTime eventTime;
        ArrayList<String> partitions;

        Event(EventTime eventTime, ArrayList<String> partitions) {
            this.eventTime = eventTime;
            this.partitions = partitions;
        }

        void printEvent(PrintWriter writer) {
            writer.print(eventTime.hours < 10 ? "0" + eventTime.hours : eventTime.hours);
            writer.print(':');
            writer.print(eventTime.minutes < 10 ? "0" + eventTime.minutes : eventTime.minutes);
            writer.print(" " + eventTime.duration + " ");
            for (String partition : partitions) {
                writer.format("%s ", partition);
            }
            writer.println();
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

        in.nextLine();
        for (int i = 0; i < n; i++) {
            String[] s = in.nextLine().split(" ");
            /*for (String s1 : s){
                System.err.print(s1 + '!');
            }*/
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

                ArrayList<String> partitions = new ArrayList<>();
                partitions.addAll(Arrays.asList(s).subList(5, s.length));
              //  System.out.print(partitions.size());

                Event event = new Event(new EventTime(timeH, timeM, duration), partitions);

                boolean needAdd = true;
                ArrayList<String> why = new ArrayList<>();
                if (hashMaps[day] == null) {
                    hashMaps[day] = new HashMap<>();
                } else {
                    for (String partition : partitions) {
                        if (hashMaps[day].containsKey(partition)) {
                            for (Event event1 : hashMaps[day].get(partition)) {
                                if (!event.eventTime.compareTo(event1.eventTime)) {
                                    needAdd = false;
                                    why.add(partition);
                                    break;
                                }
                            }
                        }
                    }
                }

                if (needAdd) {
                    out.println("OK");
                    for (String partition : partitions) {
                        if (hashMaps[day].containsKey(partition)) {
                            hashMaps[day].get(partition).add(event);
                        } else {
                            ArrayList<Event> events = new ArrayList<>();
                            events.add(event);
                            hashMaps[day].put(partition, events);
                        }
                    }
                } else {
                    out.println("FAIL");
                    for (String partition : why) {
                        out.print(partition + " ");
                    }
                    out.println();
                }

            }
        }
        out.close();
    }
}
