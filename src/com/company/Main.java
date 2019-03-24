package com.company;

public class Main {

    public static void main(String[] args) throws Exception {

        int counter = 0;
        while(true){
            try{
                Thread2 b = new Thread2(counter);
                b.start();
                counter++;
                System.out.println("Thread: " + b.name);
            } catch(OutOfMemoryError error) {
                System.err.println("Out of memory.");
            }
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