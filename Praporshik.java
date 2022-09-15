 class Producer implements Runnable {
    private Mayno myQueue;

    public Producer(Mayno myQueue) {
        this.myQueue = myQueue;
    }

    @Override
    public void run() {
        for (int i = 0; i < 3; i++) {
            myQueue.put(i);
        }
    }
}


 class Consumer implements Runnable {
    private Mayno myQueue;

    public Consumer(Mayno myQueue) {
        this.myQueue = myQueue;
    }

    @Override
    public void run() {
        for (int i = 0; i < 3; i++) {
            myQueue.get();
        }
    }
}

class Necheporhuk implements Runnable {
    private Mayno myQueue;

    public Necheporhuk(Mayno myQueue) {
        this.myQueue = myQueue;
    }

    @Override
    public void run() {
        for (int i = 0; i < 3; i++) {
            myQueue.count(i);
        }
    }
}


 class Mayno {
    private int n;
    int valueSet = 0;

    public synchronized int get() {
        while (valueSet == 2 || valueSet == 0) {
            System.out.println();
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println("Петров вантажить його в вантажівку");
        valueSet = 2;
        notify();
        return n;
    }

    public synchronized void put(int n) {
        while (valueSet == 1 || valueSet == 2) {
            System.out.println();
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println("Іванов виносить майно зі складу");
        valueSet = 1;
        notify();
    }

     public synchronized void count(int n) {
         while (valueSet == 0 || valueSet == 1) {
             System.out.println();
             try {
                 wait();
             } catch (InterruptedException e) {
                 e.printStackTrace();
             }
         }
         valueSet = 0;
         notify();
         System.out.println("Нечипорчук підраховує  вартість майна");
     }
}
public class Praporshik {
    public static void main(String[] args) {
        Mayno myQueue = new Mayno();

        Consumer consumer = new Consumer(myQueue);
        Producer producer = new Producer(myQueue);
        Necheporhuk necheporhuk = new Necheporhuk(myQueue);

        Thread t1 = new Thread(consumer);
        Thread t2 = new Thread(producer);
        Thread t3 = new Thread(necheporhuk);

        t1.start();
        t2.start();
        t3.start();
    }
}
