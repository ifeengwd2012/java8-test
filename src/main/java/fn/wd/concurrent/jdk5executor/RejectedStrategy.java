package fn.wd.concurrent.jdk5executor;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.RejectedExecutionHandler;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * Create Time: 2017-01-13 18:43
 * Created by fengweidong.
 */
public class RejectedStrategy {

    public static void main(String[] args) {

        /**
         *  自定义拒绝策略
         *
         */
        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(
                2,
                4,
                1,
                TimeUnit.MINUTES,
                new ArrayBlockingQueue<Runnable>(10),
                new RejectedExecutionHandler() {
                    @Override
                    public void rejectedExecution(Runnable r, ThreadPoolExecutor executor) {
                        System.out.println(r.toString()+"任务即将被丢弃....");
                    }
        });

        for(int i=0;i<20;i++){
            threadPoolExecutor.execute(new Runnable() {
                @Override
                public void run() {
                    System.out.println(Thread.currentThread().getName()+"线程正在执行操作....");
                }
            });
        }

    }
}
