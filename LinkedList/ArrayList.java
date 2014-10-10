public class ArrayList implements List {
    private int[] data;
    private int len;
    public ArrayList(){
        data = new int[2];
        len = 0;
    }
    public void add(int data){
        if(len >= this.data.length){
            int[] newData = new int[len*2];
            System.arraycopy(this.data,0,newData,0,len);
            this.data = newData;
        }
        this.data[len] = data;
        len++;
    }
    public int get(int index){
        return data[index];
    }
    public int size(){
        return len;
    }
    public void remove(int index){

    }
    public boolean contains(int data){
        return false;
    }
    public String toString(){
        return "";
    }
    public int sum(){
        return 0;
    }
}    
