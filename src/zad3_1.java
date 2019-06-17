import java.util.LinkedList;
import java.util.concurrent.ThreadLocalRandom;

public class zad3_1 {

    public static LinkedList<String> passwords = new LinkedList<>();

    static int pass_length = 4;
    static int itr = 0;

    public static void main(String[] args) throws InterruptedException {

        char alfabet[] = {'a','b','c','d','e','f','g','h','i','j','k','l','m','n','o','p','r','s','t','u','w','v','x','y','z'};
        char[] haslo = new char[100];
        int n = 4; // dlugosc slowa
        int L = alfabet.length;
        haslogen(n,L,0,alfabet,haslo);

        /*for(String str : passwords){
            System.out.println(str);
        }*/

        //setPassword("aaad");

        myBuffer buffer = new myBuffer(2);

        Thread producer = new Thread(new Runnable() {
            @Override
            public void run() {
                try{
                    buffer.addToBuffer();
                } catch (InterruptedException e){
                    e.printStackTrace();
                }
            }
        });

        Thread consumer = new Thread(new Runnable() {
            @Override
            public void run() {
                try{
                    buffer.remove();
                } catch(InterruptedException e){
                    e.printStackTrace();
                }
            }
        });

        producer.start();
        consumer.start();

        producer.join();
        consumer.join();
    }

    public static class myBuffer {
        public LinkedList<String> list;
        public int capacity;
        public boolean isFound = false;
        public boolean moreElements = true;

        myBuffer(int capacity){
            this.list = new LinkedList<String>();
            this.capacity = capacity;
        }

        public void addToBuffer() throws InterruptedException {

            while(true) {
                synchronized(this) {

                    if(isFound == true){
                        break;
                    }
                    else{
                        while(list.size()>=capacity){
                            wait();
                        }

                        list.add(passwords.get(itr));
                        //System.out.println("Produced " + passwords.get(itr));
                        itr++;

                        notify();
                        //Thread.sleep(1000);
                    }
                }
            }
        }

        public void remove() throws InterruptedException {
            String password = new String("acde");
            while(true){
               synchronized (this){
                   if(isFound == true){
                       break;
                   }
                   else{
                       while(list.size()<=0) {
                           wait();
                       }

                       String sth = list.removeFirst();

                       //System.out.println("sth:" +sth + " pass:"+password);
                       if(password.trim().equals(sth.trim())){
                           isFound = true;
                           System.out.println("Found password: " + sth);
                       }

                       //System.out.println("Consumed " + sth);
                       notify();

                       //Thread.sleep(1000);
                   }
               }
           }
        }
    }

    public static void haslogen(int n, int L, int level, char[] alfabet, char[] haslo)
    {
        if (level == n) {
            haslo[level]=0;
            passwords.add(String.valueOf(haslo));
        }
        else {
            for (int i=0;i<L;i++) {
                haslo[level]=alfabet[i];
                haslogen(n,L,level+1,alfabet,haslo);
            }
        }
    }
}