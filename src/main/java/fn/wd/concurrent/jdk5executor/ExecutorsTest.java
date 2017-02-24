package fn.wd.concurrent.jdk5executor;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * Create Time: 2017-01-13 17:05
 * Created by fengweidong.
 */
public class ExecutorsTest {

    public static void main(String[] args) {
        /**
         *  创建有固定数量线程的线程池
         *  线程数量始终不变
         *  当有空闲线程时,执行提交的任务,如果没有,则将任务放在等待队列,待有空闲线程时调度
         *
         */
        ExecutorService threadPool_1 = Executors.newFixedThreadPool(3);

        for(int i=0;i<2;i++){
            threadPool_1.execute(new PrintInfo());           //execute是正常的使用线程池完成任务
            //threadPool_1.submit(new PrintInfo());          //submit大神的做法
        }
        /**
         *  创建可根据实际情况调整线程数量的线程池
         *  线程数量不确定
         *  若有空闲线程，优先使用空闲线程
         *  若所有线程都在工作，又有新任务提交,则会创建新的线程
         *  所有线程在当前任务执行完后,将返回线程池进行复用
         *
         */
        ExecutorService threadPool_2 = Executors.newCachedThreadPool();
        for(int i=0;i<2;i++){
            threadPool_2.execute(new PrintInfo());
        }
        /**
         *  创建只有一个线程的线程池
         *  多余一个任务被放入线程池，则将会别放到等待队列中,按先入先出依次处理
         *
         */
        ExecutorService threadPool_3 = Executors.newSingleThreadExecutor();
        for(int i=0;i<2;i++){
            threadPool_3.execute(new PrintInfo());          //注意到,这两个任务是同一个线程来做的
        }
        /**
         *  创建的线程池大小为1
         *      提供了周期性、或固定延迟执行任务的能力
         *
         */
        ScheduledExecutorService threadPool_4 = Executors.newSingleThreadScheduledExecutor();
        for(int i=0;i<2;i++){
            threadPool_4.schedule(new PrintInfo(),10, TimeUnit.SECONDS);    //10秒后执行...
        }

        /**
         *   创建指定数量的线程池
         *          提供了周期性、或固定延迟执行任务的能力
         *
         */
        ScheduledExecutorService threadPool_5 = Executors.newScheduledThreadPool(3);
        for(int i=0;i<2;i++){
            threadPool_5.scheduleAtFixedRate(new PrintInfo(),10,8,TimeUnit.SECONDS);   //10秒之后开始执行，并且8秒执行一次
        }

    }

    private static class PrintInfo implements Runnable{

        @Override
        public void run() {
            System.out.println(Thread.currentThread().getName()+"正在执行打印操作....");
        }
    }

}
