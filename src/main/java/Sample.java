import java.util.ArrayList;

public class Sample implements Runnable {
    int seq;
    public Sample(int seq) {
        this.seq = seq;
    }

    public void run() {
        System.out.println(this.seq+" thread start.");
        try {
            System.out.println(this.seq + " is threading");
            Thread.sleep(10000);
        }catch(Exception e) {
        }
        System.out.println(this.seq+" thread end.");
    }

    public static void main(String[] args) {
        ArrayList<Thread> threads = new ArrayList<>();
        for(int i=0; i<10; i++) {
            Thread t = new Thread(new Sample(i));
            t.start();
            threads.add(t);
        }

        for(int i=0; i<threads.size(); i++) {
            Thread t = threads.get(i);
            try {
                t.join();
            }catch(Exception e) {
            }
        }
        System.out.println("main end.");
    }
}