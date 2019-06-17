import java.util.ArrayList;

public class zad2_5 {

    public static void main(String[] args) throws InterruptedException {

        int[] t1 = {664, 281, 115, 690, 809, 298, 928, 958, 556, 658, 222, 394, 526, 602, 794, 44, 356, 482, 698, 261, 86, 366, 347, 97, 247, 599};
        int[][] t2 = {{664, 281, 115, 690, 809}, {298, 928, 958, 556, 658}, {222, 394, 526, 602, 794}, {44, 356, 482, 698, 261}, {86, 366, 347, 97, 247}};

        if(searchForX(t1, 115) == true){
            System.out.println("ZNALEZIONO");
        }
        else{
            System.out.println("NIE ZNALEZIONO");
        }

        if(searchForX(t2, 115) == true){
            System.out.println("ZNALEZIONO");
        }
        else{
            System.out.println("NIE ZNALEZIONO");
        }
    }

    public static boolean searchForX(int[] t1, int X) throws InterruptedException {

        final int[] counter = {0};
        final int[] found = {0};
        int size = t1.length/4;
        for (int i=0;i<size; i++)
        {
            Runnable task = new Runnable() {
                @Override
                public void run() {
                    for(int i=size*counter[0]; i<size*counter[0]+size; i++)
                    {
                        try
                        {
                            if(X == t1[i])
                            {
                                found[0]++;
                            }
                        }catch(ArrayIndexOutOfBoundsException e)
                        {

                        }
                    }
                }
            };

            Thread thr = new Thread(task);
            thr.start();
            thr.join();
            counter[0]++;
        }

        if(found[0]>0)
        {
            return true;
        }
        return false;
    }

    public static boolean searchForX(int[][] t2, int X) throws InterruptedException {

        int[] counter = {0};
        int[] found = {0};
        int[] length = {t2.length};
        while(length[0] > 0)
        {
            Runnable task = new Runnable() {
                @Override
                public void run() {

                    for(int j=0; j<t2[counter[0]].length; j++)
                    {
                        if(t2[counter[0]][j] == X)
                        {
                            found[0]++;
                        }
                    }
                }
            };

            Thread thr = new Thread(task);
            thr.start();
            thr.join();

            counter[0]++;
            length[0]--;
        }

        if(found[0]>0)
        {
            return true;
        }
        return false;
    }

}