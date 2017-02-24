package fn.wd.concurrent.jdk5manger;

import java.util.concurrent.locks.LockSupport;

/**
 * Create Time: 2017-01-13 15:20
 * Created by fengweidong.
 */
public class LockSupportTest {
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

        //thread1.resume();           //由于CPU调度,此方法很有可能在thread1.suspend()之前调度，这样一来
                                    //被暂停的线程永远也无法正常恢复。可恶的是，它暂停期间并不释放线程所持有的锁
                                     //这样就有可能导致其他线程饥饿
        thread2.start();


        LockSupport.unpark(thread1);        //这个调用，必须在线程的start方法调度之后
        LockSupport.unpark(thread2);         //这个调用，必须在线程的start方法调度之后

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
                    //Thread.currentThread().suspend();       //暂停线程,但是不释放锁,让其他线程无锁可用

                    LockSupport.park();                       //暂停线程,但是不释放锁,让其他线程无锁可用
                                                              //park()，阻塞当前线程，不会抛InterruptException异常
                }
            }
        }
    }

}
