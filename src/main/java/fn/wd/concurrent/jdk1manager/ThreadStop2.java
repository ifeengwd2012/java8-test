package fn.wd.concurrent.jdk1manager;

import java.util.HashSet;
import java.util.Random;
import java.util.Set;

/**
 * Created by fengweidong on 2017/1/11.
 *
 * 本例可以模拟Thread.stop()导致数据不一致的本质:
 *      Thread.stop(),会导致线程立即退出,并释放所持有的锁。
 *
 *  本例中,当set中存放的有当前数据,及其平方数时，才算正常。但由运行结果来看，是数据不一致的。
 *      某次运行结果：当前set中的数据:[4096, 64, 76]
 *
 */
public class ThreadStop2 {

    private static Set<String> set= new HashSet<>();

    public static void main(String[] args) {

        Thread thread1 = new Thread(new Runnable() {
            @Override
            public void run() {
                change();
            }
        });

        Thread thread2 = new Thread(new Runnable() {
            @Override
            public void run() {
                change();
            }
        });

        thread1.start();


        try {
            Thread.sleep(10*1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }


        thread2.start();

        try {
            Thread.sleep(10*1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        thread1.stop();


        try {
            thread2.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("当前set中的数据:"+set);



    }


    private static synchronized void change() {

        int random = new Random().nextInt(100);

        set.add(String.valueOf(random));

        try {
            Thread.sleep(50*1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        set.add(String.valueOf(random*random));
    }
}
