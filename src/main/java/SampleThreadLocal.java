import java.util.ArrayList;

public class SampleThreadLocal implements Runnable {
    int seq;
    public SampleThreadLocal(int seq) {
        this.seq = seq;
    }
    public static ThreadLocal<Integer> threadLocal = new ThreadLocal<>();

    public void run() {
        System.out.println(this.seq+" thread start.");
        threadLocal.set(1);
        try {
            System.out.println(this.seq + " is threading");
            Thread.sleep(10000);
        }catch(Exception e) {
        }
        System.out.println(threadLocal.get());
        System.out.println(this.seq+" thread end.");
    }

    public static void main(String[] args) {
        ArrayList<Thread> threads = new ArrayList<>();
        for(int i=0; i<10; i++) {
            Thread t = new Thread(new SampleThreadLocal(i));
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