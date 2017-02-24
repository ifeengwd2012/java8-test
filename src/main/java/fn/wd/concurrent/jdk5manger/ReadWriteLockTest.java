package fn.wd.concurrent.jdk5manger;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * Created by fengweidong on 2017/1/13.
 *
 */
public class ReadWriteLockTest {

    private static ReentrantReadWriteLock lock = new ReentrantReadWriteLock();

    private static Lock readLock = lock.readLock();

    private static Lock writeLock = lock.writeLock();

    private static List<Integer> container = new ArrayList<Integer>();

    public static void main(String[] args) {

        Thread write = new Thread(new Runnable() {
            @Override
            public void run() {

                while (true) {

                    try {
                        Thread.sleep(1000);
                        writeLock.lock();

                        int random = new Random().nextInt(100);
                        container.add(random);
                        System.out.println(Thread.currentThread().getName()+"向容器中放入数据:"+random);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    } finally {

                        writeLock.unlock();

                    }

                }
            }
        });

        Thread write2 = new Thread(new Runnable() {
            @Override
            public void run() {

                while (true) {

                    try {
                        Thread.sleep(1*1000);
                        writeLock.lock();

                        int random = new Random().nextInt(100);
                        container.add(random);
                        System.out.println(Thread.currentThread().getName()+"向容器中放入数据:"+random);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    } finally {

                        writeLock.unlock();

                    }

                }
            }
        });

        Thread read = new Thread(new Runnable() {
            @Override
            public void run() {

                while (true) {

                    try {
                        Thread.sleep(1000);
                        readLock.lock();

                        System.out.println(Thread.currentThread().getName()+"当前容器中的数据的个数为:"+container.size());
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    } finally {

                        readLock.unlock();

                    }

                }
            }
        });

        Thread read2 = new Thread(new Runnable() {
            @Override
            public void run() {

                while (true) {

                    try {
                        Thread.sleep(1*1000);
                        readLock.lock();

                        System.out.println(Thread.currentThread().getName()+"当前容器中的数据的个数为:"+container.size());
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    } finally {

                        readLock.unlock();

                    }

                }
            }
        });

        write.start();
        write2.start();
        read.start();
        read2.start();


    }

}
