package fn.wd.concurrent.jdk5manger;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

/**
 * Create Time: 2017-01-13 13:48
 * Created by fengweidong.
 */
public class CyclicBarrierTest {

    private static CyclicBarrier cyclic = new CyclicBarrier(4);           //定义循环屏障，并将计数器设置为4

    public static void main(String[] args) {

        Thread thread0 = new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println(Thread.currentThread().getName() + "线程,准备完成,等待其他....");
                try {
                    cyclic.await();                         //计数+1,当到达设定值时才会继续执行(并且会将计数重置)
                } catch (InterruptedException e) {          //通用异常，迫使线程等待的方法都可能会抛出此异常，使得线程在等待时仍然能响应外部紧急事件
                    e.printStackTrace();
                } catch (BrokenBarrierException e) {        //一旦遇到这个异常，表示当前的"循环屏障"已经损坏了,如果继续等待，可能徒劳无功，就地散伙。
                    e.printStackTrace();
                }
                try {
                    Thread.sleep(2*1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(Thread.currentThread().getName() + "线程完成自己的工作,等待其他完成...");
                try {
                    cyclic.await();                           //重新计数+1
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (BrokenBarrierException e) {
                    e.printStackTrace();
                }
            }
        });

        Thread thread1 = new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println(Thread.currentThread().getName() + "线程,准备完成,等待其他....");
                try {
                    cyclic.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (BrokenBarrierException e) {
                    e.printStackTrace();
                }
                try {
                    Thread.sleep(3*1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(Thread.currentThread().getName() + "线程完成自己的工作,等待其他完成...");
                try {
                    cyclic.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (BrokenBarrierException e) {
                    e.printStackTrace();
                }
            }
        });

        Thread thread2 = new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println(Thread.currentThread().getName() + "线程,准备完成,等待其他....");
                try {
                    cyclic.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (BrokenBarrierException e) {
                    e.printStackTrace();
                }
                try {
                    Thread.sleep(5*1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(Thread.currentThread().getName() + "线程完成自己的工作,等待其他完成...");
                try {
                    cyclic.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (BrokenBarrierException e) {
                    e.printStackTrace();
                }
            }
        });

        Thread thread3 = new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println(Thread.currentThread().getName() + "线程,准备完成,等待其他....");
                try {
                    cyclic.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (BrokenBarrierException e) {
                    e.printStackTrace();
                }
                try {
                    Thread.sleep(4*1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(Thread.currentThread().getName() + "线程完成自己的工作,等待其他完成...");
                try {
                    cyclic.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (BrokenBarrierException e) {
                    e.printStackTrace();
                }
            }
        });


        thread0.start();
        thread1.start();
        thread2.start();
        thread3.start();

        try {
            thread0.join();
            thread1.join();
            thread2.join();
            thread3.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("所有人都已经完成任务...");
        System.out.println("GAME OVER!!!");


    }
}
