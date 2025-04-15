import java.util.Scanner;

public class Simulation {

    static MemoryBlock[] Memory;

    public static MemoryBlock firstFit(String ID, int size) {
        int firstSize = 0;

        for (int i = 0; i < Memory.length; i++) {
            if (Memory[i].size >= size && Memory[i].status.equalsIgnoreCase("free")) {
                Memory[i].ID = ID;
                Memory[i].status = "allocated";
                Memory[i].fragmentation = Memory[i].size - size;
                firstSize = i;
                return Memory[firstSize];
            }
        }

        return null;
    }

    public static void display(){
        System.out.println("Memory blocks:");
        System.out.println("==========================================================");
        System.out.println("Block#   size    start-end      status");
        System.out.println("==========================================================");
        for (int i = 0; i < Memory.length; i++) {
            if (Memory[i].start<100){
                if(Memory[i].end<100){System.out.println("Block" + i + "   " + Memory[i].size + "        " + Memory[i].start + "-" + Memory[i].end
                + "        " + Memory[i].status);}
                else{
            System.out.println("Block" + i + "   " + Memory[i].size + "        " + Memory[i].start + "-" + Memory[i].end
                    + "       " + Memory[i].status);}}
                    else{
            System.out.println("Block" + i + "   " + Memory[i].size + "      " + Memory[i].start + "-" + Memory[i].end
                    + "       " + Memory[i].status);}

        }
        System.out.println("==========================================================");

    }

    public static void displayFragmentation(){
        System.out.println("Memory blocks:");
        System.out.println("========================================================================");
        System.out.println("Block#   size    start-end      status   ProcessID  InternalFragmentation");
        System.out.println("========================================================================");
        for (int i = 0; i < Memory.length; i++) {
            if(Memory[i].status.equalsIgnoreCase("free")){
            if (Memory[i].start<100){
                if(Memory[i].end<100){System.out.println("Block" + i + "   " + Memory[i].size + "        " + Memory[i].start + "-" + Memory[i].end
                + "        " + Memory[i].status+ "                  " + Memory[i].ID + "          " +
                Memory[i].fragmentation);}
                else{
            System.out.println("Block" + i + "   " + Memory[i].size + "        " + Memory[i].start + "-" + Memory[i].end
                    + "       " + Memory[i].status+ "           " + Memory[i].ID + "          " +
                    Memory[i].fragmentation);}}
                    else{
            System.out.println("Block" + i + "   " + Memory[i].size + "      " + Memory[i].start + "-" + Memory[i].end
                    + "       " + Memory[i].status+ "           " + Memory[i].ID + "          " +
                    Memory[i].fragmentation);}
            }
            else{
                if (Memory[i].start<100){
                    if(Memory[i].end<100){System.out.println("Block" + i + "   " + Memory[i].size + "        " + Memory[i].start + "-" + Memory[i].end
                    + "        " + Memory[i].status+ "      " + Memory[i].ID + "            " +
                    Memory[i].fragmentation);}
                    else{
                System.out.println("Block" + i + "   " + Memory[i].size + "        " + Memory[i].start + "-" + Memory[i].end
                        + "       " + Memory[i].status+ "      " + Memory[i].ID + "            " +
                        Memory[i].fragmentation);}}
                        else{
                System.out.println("Block" + i + "   " + Memory[i].size + "      " + Memory[i].start + "-" + Memory[i].end
                        + "       " + Memory[i].status+ "      " + Memory[i].ID + "            " +
                        Memory[i].fragmentation);}
            }

        }
        System.out.println("==========================================================");

    }

    public static MemoryBlock bestFit(String ID, int size) {
        int bestSize = 0;

        for (int i = 0; i < Memory.length; i++) { 
            if (Memory[i].size >= size && Memory[i].status.equalsIgnoreCase("free")) {
                if (Memory[i].size < Memory[bestSize].size || Memory[bestSize].size < size) { 
                    bestSize = i;
                } else if(Memory[i].size == Memory[bestSize].size){
                    if(Memory[bestSize].status.equalsIgnoreCase("allocated"))
                        bestSize = i;
                }
            }
        }

        if(Memory[bestSize].size>=size && Memory[bestSize].status.equalsIgnoreCase("free")){

        Memory[bestSize].ID = ID;
        Memory[bestSize].status = "allocated";
        Memory[bestSize].fragmentation = Memory[bestSize].size - size;
        return Memory[bestSize];
        }

        else return null;
    }

    public static MemoryBlock worstFit(String ID, int size) {
        int worstSize = 0;

        for (int i = 0; i < Memory.length; i++) {
            if (Memory[i].size >= size && Memory[i].status.equalsIgnoreCase("free")) {
                if (Memory[i].size > Memory[worstSize].size) {
                    worstSize = i;
                } else if (Memory[i].size == Memory[worstSize].size){
                    if(Memory[worstSize].status.equalsIgnoreCase("allocated"))
                        worstSize = i;
                }
            }
        }

        if(Memory[worstSize].size>=size && Memory[worstSize].status.equalsIgnoreCase("free")){

        Memory[worstSize].ID = ID;
        Memory[worstSize].status = "allocated";
        Memory[worstSize].fragmentation = Memory[worstSize].size - size;
        return Memory[worstSize];
        }
        else return null;
    }

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        System.out.println("Enter the total number of blocks: ");
        int noOfBlocks = input.nextInt();
        Memory = new MemoryBlock[noOfBlocks];

        System.out.println("Enter the size of each block in KB: ");
        int start = 0;

        for (int i = 0; i < noOfBlocks; i++) {
            int size = input.nextInt();
            MemoryBlock newBlock = new MemoryBlock(size, start);
            Memory[i] = newBlock;
            start += size;

        }

        System.out.println("Enter allocation strategy (1 for first-fit, 2 for best-fit, 3 for worst-fit):");
        int strategy = input.nextInt();

        System.out.println("Memory blocks are created...");
        display();
       

        while (true) {

            System.out.println("1) Allocates memory blocks");
            System.out.println("2) De-allocates memory blocks");
            System.out.println("3) Print report about the current state of memory and internal Fragmentation");
            System.out.println("4) Exit");
            System.out.println("==========================================================");

            System.out.println("Enter your choice:");
            int choice = input.nextInt();

            switch (choice) {
                case 1:

                    System.out.println("Enter the process ID and size of process:");
                    String ID = input.next();
                    int size = input.nextInt();
                    MemoryBlock addressBlock = null;

                    switch (strategy) {
                        case 1:
                            addressBlock = firstFit(ID, size);
                            break;
                        case 2:
                            addressBlock = bestFit(ID, size);
                            break;
                        case 3:
                            addressBlock = worstFit(ID, size);
                            break;
                        default:
                            break;

                    }

                    if (addressBlock==null)
                    System.out.println("No available block is large enough");

                    else
                    System.out.println(addressBlock.ID + " allocated at address " + addressBlock.start +
                            ", and the internal fragmentation is " + addressBlock.fragmentation);

                    break;

                case 2:

                    System.out.println("Enter the process ID:");
                    String pID = input.next();

                    for (int i = 0; i < Memory.length; i++) {
                        if(Memory[i].ID == null)
                            continue;
                        if (Memory[i].ID.equalsIgnoreCase(pID)) {
                            Memory[i].ID = null;
                            Memory[i].status = "free";
                            Memory[i].fragmentation = 0;
                            break;
                        }
                    }

                    System.out.println(pID + " has been deleted successfully.");
                    break;

                case 3:
                   displayFragmentation();
                    break;

                case 4:
                    input.close();
                    System.exit(0);
                    break;

                default:
                    break;
            }

        }

    }

}