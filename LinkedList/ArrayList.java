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
        System.arraycopy(data,index+1,data,index,len-index-1);
        len-=1;
    }
    public boolean contains(int data){
        for(int i = 0; i < len; ++i){
            if(data == this.data[i]){
                return true;
            }
        }
        return false;
    }
    public String toString(){
        String returnString = "|";
        for(int i = 0; i < len; ++i){
            returnString = returnString+data[i]+"|";
        }
        return returnString;
    }
    public int sum(){
        int sumData = 0;
        for(int i = 0; i < len; ++i){
            sumData = data[i] + sumData;
        }
        return sumData;
    }
}    
