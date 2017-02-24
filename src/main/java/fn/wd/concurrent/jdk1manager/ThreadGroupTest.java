package fn.wd.concurrent.jdk1manager;

/**
 * Created by fengweidong on 2017/1/12.
 *
 */
public class ThreadGroupTest {

    public static void main(String[] args) {

        ThreadGroup threadGroup = new ThreadGroup("print_thread_group");

        Thread thread1 = new Thread(threadGroup, new Runnable() {
            @Override
            public void run() {
                System.out.println("当前线程" + Thread.currentThread().getName() + "正在执行...");
                try {
                    Thread.sleep(1000*2);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

        Thread thread2 = new Thread(threadGroup, new Runnable() {
            @Override
            public void run() {
                System.out.println("当前线程" + Thread.currentThread().getName() + "正在执行...");
                try {
                    Thread.sleep(1000*3);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

        thread1.start();
        thread2.start();

        System.out.println("调用线程组的方法......");

        System.out.println("当前活跃的线程数目:"+threadGroup.activeCount());



    }
}
