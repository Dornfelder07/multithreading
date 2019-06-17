public class zad1_3 {

    public static int countEdgesSeq(int n, int[][] t2) {
        int k = 0;
        for (int i=0; i<n-1; i++)
            for (int j = i+1; j<n; j++)
                if (t2[i][j] == 1)
                {
                    k++;
                }
        return k;
    }

    public static int countEdgesConc(int n, int[][] t2) throws InterruptedException
    {
        final int[] counter = {0};
        final int[] k = {0};
        final int[] x = {n};

        while(x[0]>0)
        {
            try{
                Runnable task = new Runnable() {
                    @Override
                    public void run() {
                        for(int j=0;j<n;j++)
                            if(t2[x[0]][j]==1)
                            {
                                k[0]++;
                            }
                    }
                };

                x[0]--;

                Thread thr = new Thread(task);
                thr.start();
                thr.join();
            }catch(ArrayIndexOutOfBoundsException e)
            {

            }
        }

        return (k[0]/2);
    }

    public static void main(String[] args) throws InterruptedException {
        int wynik;
        int wynik2;
        int[][] arr = {{0,1,0,1,1},{1,0,1,0,0},{0,1,0,0,0},{1,0,0,0,0},{1,0,0,0,0}};

        long startTime = System.nanoTime();
        wynik = countEdgesSeq(5, arr);
        long dtime = System.nanoTime() - startTime;
        long time = dtime;


        long startTime2 = System.nanoTime();
        wynik2 = countEdgesConc(5,arr);
        long dtime2 = System.nanoTime() - startTime2;
        long time2 = dtime2;


        System.err.println("Krawedzie w grafie (seq): " + wynik);
        System.err.println("{czas(sekwencyjnie)="+time+"ns}");
        System.err.println("Krawedzie w grafie (wspolb): " + wynik2);
        System.err.println("{czas(wspolbieznie)="+time2+"ns}");
    }
}