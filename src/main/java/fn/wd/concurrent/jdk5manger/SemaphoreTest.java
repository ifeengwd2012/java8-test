package fn.wd.concurrent.jdk5manger;

import java.util.concurrent.Semaphore;

/**
 * Created by fengweidong on 2017/1/13.
 *
 */
public class SemaphoreTest {

    private static Semaphore semaphore = new Semaphore(2);

    public static void main(String[] args) {


        Thread thread0 = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    semaphore.acquire();
                    System.out.println("...");
                    //限制同时访问这段代码的线程，不能超过2个
                    print();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    semaphore.release();
                }
            }
        });

        Thread thread1 = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    semaphore.acquire();
                    System.out.println("....");

                    //限制同时访问这段代码的线程，不能超过2个
                    print();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    semaphore.release();
                }
            }
        });

        Thread thread2 = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    semaphore.acquire();
                    System.out.println(".....");


                    //限制同时访问这段代码的线程，不能超过2个
                    print();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    semaphore.release();
                }
            }
        });

        Thread thread3 = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    semaphore.acquire();
                    System.out.println("......");

                    //限制同时访问这段代码的线程，不能超过2个
                    print();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    semaphore.release();
                }
            }
        });

        thread0.start();
        thread1.start();
        thread2.start();
        thread3.start();


    }


    private static void print(){
        //模拟耗时工作
        try {
            Thread.sleep(3*1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(Thread.currentThread().getName() + "正在执行...");
    }




}
