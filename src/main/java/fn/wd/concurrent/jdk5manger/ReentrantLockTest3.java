package fn.wd.concurrent.jdk5manger;

import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by fengweidong on 2017/1/12.
 *
 */
public class ReentrantLockTest3 {

    private static ReentrantLock lock_1 = new ReentrantLock();

    public static void main(String[] args) {


        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    //if (lock_1.tryLock(5, TimeUnit.SECONDS)) {            //带参数的限时等待，第一参数是:等待的数值，第二个参数是：等待的时间单位
                    if (lock_1.tryLock()) {                                 //不带参数的等待,会尝试获得锁,得到返回true,否则返回false.
                        System.out.println(Thread.currentThread().getName() + "拿到了lock_1的锁");
                        for(int i = 0;i<10;i++){
                            System.out.println("打印...."+i);
                        }
                    }else {
                        System.out.println(Thread.currentThread().getName() + "未拿到了lock_1的锁");
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });

        lock_1.lock();//主线程获得了lock_1的锁

        thread.start();


        try {
            Thread.sleep(1000*6);       //让主线程持有锁睡眠
        } catch (InterruptedException e) {
            e.printStackTrace();
        }finally {
            lock_1.unlock();
        }

        try {
            thread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("GAME OVER!!!");

    }
}
