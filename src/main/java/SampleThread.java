public class SampleThread extends Thread {
    public void run() {  // Thread 를 상속하면 run 메서드를 구현해야 한다.
        System.out.println("thread run.");
    }

    public static void main(String[] args) {
        SampleThread sampleThread = new SampleThread();
        sampleThread.start();
    }
}