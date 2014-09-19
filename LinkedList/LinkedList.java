public class LinkedList{
    private LinkedList link;
    private int data;
    public LinkedList(){
        link = null;
    }
    public void add(int data){
        if(link == null){
            link = new LinkedList();
            link.data = data;
            
        }else{
            link.add(data);
        }
        
    }
    public int get(int index){
        if(index == 0){
            return link.data;
        }
        return link.get(index-1);
    }
    public int size(){
        if(link == null){
            return 0;
        }
        return link.size()+1;
    }
    public void remove(int index){
        if(index == 0){
            link = link.link;
        }else{
            link.remove(index-1);
        }
    }
}