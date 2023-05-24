import java.util.concurrent.atomic.AtomicInteger;

public class AtomicIntegerTest {

    private static int count;

    public static void main(String[] args) throws InterruptedException {
        AtomicInteger atomicCount = new AtomicInteger(0);
        Thread thread1 = new Thread(() -> {
            for (int i = 0; i < 100000; i++) {
                count++;
                atomicCount.incrementAndGet();
            }
        });

        Thread thread2 = new Thread(() -> {
            for (int i = 0; i < 100000; i++) {
                count++;
                atomicCount.incrementAndGet();
            }
        });

        thread1.start();
        thread2.start();

        Thread.sleep(5000);
        System.out.println("atomic 결과 : " + atomicCount.get());
        System.out.println("int 결과 : " + count);
    }
}