public class SortTest{
    public static void main(String[] args) {
        List list = new ArrayList();
        list.add(2);
        list.add(43);
        list.add(80036);
        list.add(7819);
        list.add(3390);
        System.out.println(list);
        System.out.println("Sorting the list...");
        ListSorter listSorter = new SelectionListSorter(list);
        listSorter.sort();
        System.out.println(list);
    }
}