package fn.wd.concurrent.jdk1manager;

/**
 * Created by fengweidong on 2017/1/12.
 *
 */
public class JoinYield {

    public static void main(String[] args) throws InterruptedException {

        Thread thread1 = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(1000*2);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(Thread.currentThread().getName()+"完成了当前任务...");

                Thread.currentThread().yield();
            }
        });

        Thread thread2 = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(1000 * 2);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(Thread.currentThread().getName() + "完成了当前任务....");

                Thread.currentThread().yield();
            }
        });

        thread1.start();

        thread2.start();


        try {
            thread1.join();     //join的本质是：让调用线程(main线程)wait()在当前线程对象实例(thread1)上
                                //当thread1执行完毕之后,会通知在当前线程对象等待队列上的线程继续执行

            thread2.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("所有线程均完成任务,主线程即将退出......");
        System.out.println("GAME OVER!!!");


    }


}
