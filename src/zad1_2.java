import java.util.concurrent.TimeUnit;

public class zad1_2 {

    public static void main(String[] args) throws InterruptedException {

        long[] array = new long[101];
        int counter = 0;
        while(counter<100){
            try{
                long startTime = System.nanoTime();
                Thread2 b = new Thread2(counter);
                b.start();
                long dtime = System.nanoTime() - startTime;
                long time = dtime;
                System.err.println("{czas="+time+"ns}");
                counter++;

                array[counter] = time;

            } catch(OutOfMemoryError error) {
                System.err.println("Out of memory.");
            }
        }

        for(int i=0; i<array.length; i++){
            System.out.println(i + "\t" + array[i]);
        }
    }
}

class Thread2 extends Thread {
    String name;

    public Thread2(int name) {
        this.name = Integer.toString(name);
    }

    @Override
    public void run() {
        synchronized (this)
        {
            try {
                Thread.currentThread().wait();
            } catch (InterruptedException e) {}
        }
    }
}