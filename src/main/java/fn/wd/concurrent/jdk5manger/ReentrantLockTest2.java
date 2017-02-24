package fn.wd.concurrent.jdk5manger;

import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by fengweidong on 2017/1/12.
 *
 */
public class ReentrantLockTest2 {

    private static ReentrantLock lock1 = new ReentrantLock();
    private static ReentrantLock lock2 = new ReentrantLock();

    public static void main(String[] args) throws InterruptedException {


        Thread thread1 = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    lock1.lockInterruptibly();              //先拿1
                    for (int i = 0; i < 10; i++) {
                        System.out.println(Thread.currentThread().getName() + "正在执行" + i);
                    }
                    Thread.sleep(1000*2);
                    lock2.lockInterruptibly();              //后拿2
                    System.out.println(Thread.currentThread().getName() + "任务完成...");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }finally {
                    lock1.unlock();
                    lock2.unlock();
                }
            }
        });


        Thread thread2 = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    lock2.lockInterruptibly();              //先拿第二把锁
                    for (int i = 0; i < 10; i++) {
                        System.out.println(Thread.currentThread().getName() + "正在执行" + i);
                    }
                    Thread.sleep(1000*2);
                    lock1.lockInterruptibly();               //后拿第一把锁
                    System.out.println(Thread.currentThread().getName() + "任务完成....");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }finally {
                    lock1.unlock();
                    lock2.unlock();
                }
            }
        });

        thread1.start();
        thread2.start();


        try {
            Thread.sleep(4*1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("死锁.........");

        System.out.println("手动打断Thread-0....");
        thread1.interrupt();


        thread1.join();
        thread2.join();
    }
}
