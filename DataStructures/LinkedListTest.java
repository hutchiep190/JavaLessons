public class LinkedListTest{
    public static void main(String[] args) {
        List list = new ArrayList();
        list.add(12);
        System.out.println("First element is " + list.get(0) + ".");
        list.add(9);
        System.out.println("Second element is " + list.get(1) + ".");
        list.add(list.get(0)+list.get(1));
        System.out.println("Third element is " + list.get(2) + ".");
       System.out.println("The size of this list is " + list.size() + ".");
       list.remove(1);
       System.out.println("The size of this list after removing the second value is " + list.size() + ".");
       System.out.println("The fact that the list contains 9 is: " + list.contains(9) + ".");
       System.out.println("The fact that the list contains 12 is: " + list.contains(12) + ".");
       System.out.println("The fact that the list contains 21 is: " + list.contains(21) + ".");
       System.out.println("The fact that the list contains 300 is: " + list.contains(300) + ".");
       System.out.println(list);
       System.out.println("The list's sum is " + list.sum() + ".");
       List linkedList = new LinkedList();
       linkedList.add(30);
       linkedList.add(24);
       System.out.println(linkedList);
       linkedList.set(1, 64);
       System.out.println(linkedList);
    }
}