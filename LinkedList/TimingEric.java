import java.util.Random;
import java.util.Date;

public class TimingEric {

    private static final int TIMES = 3000;

    public static int timeAdd(List list, int n) {
        Date before = new Date();
        for (int i = 0; i < n; ++i) {
            list.add(new Random().nextInt());
        }
        Date after = new Date();
        return (int)(after.getTime() - before.getTime());
    }

    public static int timeRemove(List list, int n) {
        Date before = new Date();
        for (int i = 0; i < n; ++i) {
            list.remove(0);
        }
        Date after = new Date();
        return (int)(after.getTime() - before.getTime());
    }

    public static void logResult(int time, String action, int times) {
        System.out.println("It took " + time + " milliseconds for " + action
                           + " arrayList " + times + " times");
    }

    public static void main(String[] args) {
        List arrayList = new ArrayList();
        List linkedList = new LinkedList();
        int time;

        time = timeAdd(arrayList, TIMES);
        logResult(time, "add to", TIMES);

        time = timeAdd(linkedList, TIMES);
        logResult(time, "add to", TIMES);

        time = timeRemove(arrayList, TIMES);
        logResult(time, "remove from", TIMES);

        time = timeRemove(linkedList, TIMES);
        logResult(time, "remove from", TIMES);
    }

}
