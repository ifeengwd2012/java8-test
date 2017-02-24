package fn.wd.concurrent.jdk5manger;

import java.util.concurrent.CountDownLatch;

/**
 * Created by fengweidong on 2017/1/13.
 *
 */
public class CountDownLatchTest {

    private static CountDownLatch countDown = new CountDownLatch(2);

    public static void main(String[] args) {

        Thread thread0 = new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println(Thread.currentThread().getName() + "稍等,处理点事情...");
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(Thread.currentThread().getName() + "即将处理完成...");
                countDown.countDown();

            }
        });

        Thread thread1 = new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println(Thread.currentThread().getName() + "稍等,处理点事情...");
                try {
                    Thread.sleep(1*1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(Thread.currentThread().getName() + "即将处理完成...");
                countDown.countDown();
            }
        });

        thread0.start();
        thread1.start();

        //等待线程1和线程0它们手头的工作做完...
        try {
            countDown.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("GAME OVER!!!");

    }


}
