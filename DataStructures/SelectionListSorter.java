public class SelectionListSorter implements ListSorter{
    
    private List list;

    public SelectionListSorter(List list){
        this.list = list;
    }

    public void sort(){
        for(int i = 0; i < list.size(); i++){
            int smallestSoFar = i;
            for(int j = i+1; j < list.size(); j++){
                if(list.get(j) < list.get(smallestSoFar)){
                    smallestSoFar = j;
                }
            }
            int temp = list.get(i);
            list.set(i, list.get(smallestSoFar));
            list.set(smallestSoFar, temp);
        }
    } 
}