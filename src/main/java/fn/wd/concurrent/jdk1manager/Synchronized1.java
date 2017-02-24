package fn.wd.concurrent.jdk1manager;

/**
 * Created by fengweidong on 2017/1/12.
 *
 */
public class Synchronized1 {

    public static void main(String[] args) {

        Thread thread1 = new Thread(new Runnable() {
            @Override
            public void run() {
                print1();
            }
        });

        Thread thread2 = new Thread(new Runnable() {
            @Override
            public void run() {
                print2();
            }
        });

        thread1.start();        //线程1和线程2竞争的是同一把锁(当前类对象),所以thread1和thread2不会并发。
        thread2.start();


        try {
            thread1.join();
            thread2.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("GAME OVER!!!");



    }

    private synchronized static void print1(){          //synchronized锁的是当前类对象(class对象,这个是唯一的),所以print1和print2其实是互斥的。
        System.out.println(Thread.currentThread().getName()+"线程正在执行print1操作....");
        try {
            Thread.sleep(1000*30);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private synchronized static void print2(){          //当有线程执行print1时，print2无法执行！因为它拿不到类对象的锁
        System.out.println(Thread.currentThread().getName()+"线程正则执行print2操作.....");
        try {
            Thread.sleep(1000*30);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }


}
