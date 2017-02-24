package fn.wd.concurrent.jdk1manager;


import java.util.ArrayList;
import java.util.Random;

/**
 * Created by fengweidong on 2017/1/11.
 *
 */
public class WaitNotify {
    private static ArrayList<Integer> container = new ArrayList<>();

    private static Object lock = new Object();

    public static void main(String[] args) {

        //生产者
        Thread productor = new Thread(new Runnable() {
            @Override
            public void run() {
                while(true){
                    synchronized (lock) {                           //1.这里获取对象的锁
                        //容器满了，等待....
                        if (container.size() >= 10) {
                            try {
                                lock.wait();                        //2.只有获取了对象的锁，才能调用对象的wait()
                                                                    //一旦调用了对象的wait()，就意味着当前线程放弃了该对象的锁
                                                                    //所以这条代码最好是最后一行
                            } catch (InterruptedException e) {
                            }
                        } else {

                            try {
                                Thread.sleep(1000);
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }

                            container.add(new Random().nextInt(100));
                            System.out.println("生产了一个产品....");

                            lock.notifyAll();                       //3.唤醒在当前对象上等待的所有线程,去竞争对象的锁
                                                                    //在synchronized退出之前，线程所持有的锁是不释放的
                                                                    //所以,这条代码最好是最后一行
                        }

                    }
                }
            }
        });


        //消费者
        Thread consumer = new Thread(new Runnable() {
            @Override
            public void run() {

                while(true){
                    synchronized (lock) {                              //4.生产者和消费者竞争的是同一把锁
                        //容器空了
                        if (container.size() == 0) {
                            try {
                                lock.wait();
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                        } else {

                            lock.notifyAll();

                            container.remove(0);
                            System.out.println("消费了一个产品....");

                            try {
                                Thread.sleep(1000);
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }

                        }
                    }
                }
            }
        });


        consumer.start();
        productor.start();


        try {
            Thread.sleep(20*1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }


        System.out.println("GAME OVER!!!");

    }


}
