import java.util.Random;
import java.util.Date;

public class Timing {

    public static void main(String[] args) {
        ArrayList list = new ArrayList();
        Date before = new Date();
        for (int i = 0; i < 10000; ++i) {
            list.add(new Random().nextInt());
        }
        Date after = new Date();
        System.out.println("It took "
                           + (after.getTime() - before.getTime())
                           + " milliseconds for add to arrayList 10000 times");

        LinkedList list2 = new LinkedList();
        Date before2 = new Date();
        for (int i = 0; i < 10000; ++i) {
            list2.add(new Random().nextInt());
        }
        Date after2 = new Date();
        System.out.println("It took "
                           + (after2.getTime() - before2.getTime())
                           + " milliseconds for add to linkedList 10000 times");

        Date before3 = new Date();
        for (int i = 0; i < 10000; ++i) {
            list.remove(0);
        }
        Date after3 = new Date();
        System.out.println("It took "
                           + (after3.getTime() - before3.getTime())
                           + " milliseconds for remove from arrayList 10000 times");

        Date before4 = new Date();
        for (int i = 0; i < 10000; ++i) {
            list2.remove(0);
        }
        Date after4 = new Date();
        System.out.println("It took "
                           + (after4.getTime() - before4.getTime())
                           + " milliseconds for remove from linkedList 10000 times");

    }

}
