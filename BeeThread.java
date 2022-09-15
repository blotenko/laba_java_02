
 public class BeeThread {

    static int numberOfBeeTeams = 5;

     static int bear = 2;

    public static void main(String[] args) {

        Forrest forrest = new Forrest();
        for (int i = 0; i < numberOfBeeTeams; i++){

            Thread t = new Thread(new CountThread(forrest,i));
            t.setName("Thread "+ i);
            t.start();
        }
    }
}

class Forrest{

    synchronized void findBear(int threadCount){
        if(threadCount == BeeThread.bear){
            System.out.printf("%s %s %d \n", Thread.currentThread().getName(),"Знайшов та публично покарав ведмедя після перевіки ділянки №",threadCount);
            System.out.printf("%s  %s \n", Thread.currentThread().getName(),"повернувся до вулика");
            Thread.currentThread().interrupt();
        }
        else
        {
            System.out.printf("%s  %s %d \n", Thread.currentThread().getName(),"повернувся до вулика після перевіки ділянки №",threadCount);
            Thread.currentThread().interrupt();
        }

    }
}

class CountThread implements Runnable{
    Forrest res;
    int threadCount;
    CountThread(Forrest res, int count){
        this.res=res;
        threadCount = count;
    }
    public void run(){
        res.findBear(threadCount);
    }
}