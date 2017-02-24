package fn.wd.concurrent.jdk5manger;

import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by fengweidong on 2017/1/12.
 *
 */
public class ReentrantLockTest1 {

    private static ReentrantLock lock = new ReentrantLock();

    private static int count = 0;

    public static void main(String[] args) throws InterruptedException {

        Thread thread1 = new Thread(new Runnable() {
            @Override
            public void run() {
                print1();
            }
        });

        Thread thread2 = new Thread(new Runnable() {
            @Override
            public void run() {
                print1();
            }
        });

        thread1.start();
        thread2.start();

        thread1.join();
        thread2.join();

        System.out.println("最终的计数结果..."+count);
        System.out.println("GAME OVER!!!");

    }

    private static void print1(){

        for(int i=0;i<100;i++){
            lock.lock();            //使用重入锁保护临界区资源count。
            count++;
            System.out.println(Thread.currentThread().getName()+"使计数器加1");
            lock.unlock();          //退出临界区资源
        }

    }
}
