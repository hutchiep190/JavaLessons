import java.util.Random;
import java.util.Date;

public class Timing {

    private static int numberOfInts = 3000;

    public static void main(String[] args) {
        ArrayList list = new ArrayList();
        LinkedList list2 = new LinkedList();
        
        timeAdd(list);
        timeAdd(list2);

        timeRemove(list);
        timeRemove(list2);
    }

    public static void timeAdd(List list){
        Date before = new Date();
        addRandomInts(list);
        Date after = new Date();
        System.out.println("It took "
                           + (after.getTime() - before.getTime())
                           + " milliseconds for add to linkedList " + numberOfInts + " times");
    }

    public static void timeRemove(List list){
        Date before = new Date();
        removeInts(list);
        Date after = new Date();
        System.out.println("It took "
                           + (after.getTime() - before.getTime())
                           + " milliseconds for remove from linkedList " + numberOfInts + " times");

    }

    public static void addRandomInts(List list){
        for (int i = 0; i < numberOfInts; ++i) {
            list.add(new Random().nextInt());
        }
    }

    public static void removeInts(List list){
        for (int i = 0; i < numberOfInts; ++i) {
            list.remove(0);
        }
    }

}
