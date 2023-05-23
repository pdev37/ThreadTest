import java.time.LocalDateTime;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class ThreadLocalTest {

    public static void main(String[] args) {
        ThreadLocalTest block = new ThreadLocalTest();

        Thread thread1 = new Thread(() -> {
            System.out.println("스레드1 시작 " + LocalDateTime.now());
            block.syncBlockMethod1("스레드1");
            System.out.println("스레드1 종료 " + LocalDateTime.now());
        });

        Thread thread2 = new Thread(() -> {
            System.out.println("스레드2 시작 " + LocalDateTime.now());
            block.syncBlockMethod2("스레드2");
            System.out.println("스레드2 종료 " + LocalDateTime.now());
        });
        thread1.setPriority(10);
        thread2.setPriority(1);

        thread1.start();
        thread2.start();
    }

    private ReentrantLock lock = new ReentrantLock();
    Condition thread1Con = lock.newCondition();
    public static ThreadLocal<String> threadLocal = new ThreadLocal<>();

    private void syncBlockMethod1(String msg) {
        lock.lock();
        threadLocal.set(msg);
        System.out.println(msg + "의 syncBlockMethod1 실행중" + LocalDateTime.now());
        try {
            TimeUnit.SECONDS.sleep(5);
            System.out.println(threadLocal.get());
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    private void syncBlockMethod2(String msg) {
        lock.lock();
        threadLocal.set(msg);
        System.out.println(msg + "의 syncBlockMethod2 실행중" + LocalDateTime.now());
        try {
            TimeUnit.SECONDS.sleep(5);
            System.out.println(threadLocal.get());
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }
}