package fn.wd.concurrent.jdk5manger;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by fengweidong on 2017/1/13.
 */
public class ConditionTest {

    private static ReentrantLock lock = new ReentrantLock();        //生成重入锁实例

    private static Condition condition = lock.newCondition();       //生成一个与重入锁绑定的Condition实例

    private static List<Integer> container = new ArrayList<>();

    public static void main(String[] args) {

        Thread consumer = new Thread(new Runnable() {
            @Override
            public void run() {
                while (true) {
                    try {
                        lock.lock();                //获取重入锁

                        try {
                            Thread.sleep(1000);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }

                        if (container.size() > 0) {
                            Integer remove = container.remove(0);
                            System.out.println(Thread.currentThread().getName() + "消费了一个产品:" + remove);

                            condition.signalAll();
                        } else {
                            try {
                                condition.await();
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                        }
                    } finally {
                        lock.unlock();
                    }
                }
            }
        });

        Thread productor = new Thread(new Runnable() {
            @Override
            public void run() {
                while (true) {
                    try {
                        lock.lock();

                        try {
                            Thread.sleep(1000);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }

                        if (container.size() < 10) {
                            int prod = new Random().nextInt(100);
                            container.add(prod);
                            System.out.println(Thread.currentThread().getName()+"生成了一个产品:"+prod);

                            condition.signalAll();
                        } else {
                            try {
                                condition.await();
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                        }
                    } finally {
                        lock.unlock();
                    }

                }
            }
        });

        productor.start();
        consumer.start();

    }


}
