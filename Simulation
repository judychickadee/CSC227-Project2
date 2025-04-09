import java.util.Scanner;

public class Simulation{
    public static void main(String[]args){
        Scanner input= new Scanner(System.in);
        
        System.out.println("Enter the total number of blocks: ");
        int noOfBlocks= input.nextInt();
        MemoryBlock[] Memory = new MemoryBlock[noOfBlocks];

        System.out.println("Enter the size of each block in KB: ");
        int start=0;
        for(int i=0; i<noOfBlocks; i++){
            int size= input.nextInt();
            MemoryBlock newBlock = new MemoryBlock(size, start);
            Memory[i]= newBlock;
            start+= size;

        }

        System.out.println("Memory blocks are created… ");
        System.out.println("Memory blocks:");

        for(int i=0; i<noOfBlocks; i++){
            System.out.println("Block" + i + "   " + Memory[i].size + "   " + Memory[i].start + "-" + Memory[i].end + "   " + Memory[i].status);

    }


}

}