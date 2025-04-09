public class MemoryBlock{
    int size;
    int start;
    int end;
    String status;
    String ID;
    int fragmentation;

    public MemoryBlock(int size, int start){
        this.size= size;
        this.start= start;
        this.end= start+size-1;
        status= "free";
        ID= null;
        int fragmentation= 0;

    }


}