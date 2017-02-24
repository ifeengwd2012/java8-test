package fn.wd.concurrent.jdk1manager;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by fengweidong on 2017/1/12.
 *  一直说，ArrayList是线程不安全的容器，到底是哪里不安全？？
 *      不安全是指，它不允许多个线程同时对它进行操作，如果同时操作
 *      有可能会出现意料之外的结果
 *
 *      多次执行，会出现小于20000的隐秘错误。
 *
 */
public class ArrayListTest {

    private static List<Integer> container = new ArrayList<>(10);

    public static void main(String[] args) throws InterruptedException {

        Thread thread1 = new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < 10000; i++) {
                    System.out.println(Thread.currentThread().getName()+"正在添加"+i);
                    container.add(i);
                }
            }
        });

        Thread thread2 = new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 10000; i < 20000; i++) {
                    System.out.println(Thread.currentThread().getName()+"正在添加"+i);
                    container.add(i);
                }
            }
        });

        thread1.start();
        thread2.start();


        thread1.join();
        thread2.join();

        System.out.println("容器最终大小:"+container.size());

    }
}
