package fn.wd.concurrent.jdk1manager;

/**
 * Created by fengweidong on 2017/1/11.
 *
 */
public class SuspendResume2 {

    private static Object lock = new Object();

    public static void main(String[] args) {

        Thread thread1 = new Thread(new Runnable() {
            @Override
            public void run() {
                    print();
            }
        });

        Thread thread2 = new Thread(new Runnable() {
            @Override
            public void run() {
                print();
            }
        });

        thread1.start();
        thread2.start();

        thread1.resume();           //由于CPU调度,此方法很有可能在thread1.suspend()之前调度，这样一来
                                    //被暂停的线程永远也无法正常恢复。可恶的是，它暂停期间并不释放线程所持有的锁
                                    //这样就有可能导致其他线程饥饿



        try {
            thread1.join();
            thread2.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("GAME OVER!!!");

    }

    private static void print(){
        synchronized (lock){                                //不同线程要执行这段代码，要竞争lock这同一把锁...
            for (int i = 0; i < 20; i++) {
                System.out.println(Thread.currentThread().getName()+"线程打印....." + i);
                if (i == 10){
                    System.out.println("准备暂停print线程....");
                    Thread.currentThread().suspend();       //暂停线程,但是不释放锁,让其他线程无锁可用
                }
            }
        }
    }

}
