package fn.wd.concurrent.jdk5executor;

import java.util.Random;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * Create Time: 2017-01-13 18:46
 * Created by fengweidong.
 */
public class ThreadFactoryTest {

    /**
     *  自定义线程池中的线程的创建方式
     *
     */
    public static void main(String[] args) {

        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(
                2,
                4,
                1L,
                TimeUnit.MINUTES,
                new ArrayBlockingQueue<Runnable>(20),
                new ThreadFactory(){
                    @Override
                    public Thread newThread(Runnable r) {
                        int random = new Random().nextInt(100);
                        Thread thread = new Thread(r, "fn-wd-" + random);
                        return thread;
                    }
                }
        );


        for(int i=0;i<20;i++){
            threadPoolExecutor.execute(new Runnable() {
                @Override
                public void run() {
                    System.out.println(Thread.currentThread().getName()+"正在执行任务....");
                }
            });
        }


    }
}
