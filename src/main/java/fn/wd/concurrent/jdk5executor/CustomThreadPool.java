package fn.wd.concurrent.jdk5executor;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * Create Time: 2017-01-14 17:13
 * Created by fengweidong.
 */
public class CustomThreadPool {

    public static void main(String[] args) {

        MyThreadPoolExecution myThreadPoolExecution = new MyThreadPoolExecution(2, 4, 1L, TimeUnit.MINUTES, new ArrayBlockingQueue<Runnable>(20));

        for(int i=0;i<20;i++){
            myThreadPoolExecution.execute(new Runnable() {
                @Override
                public void run() {
                    System.out.println(Thread.currentThread().getName()+"正在执行任务....");
                }
            });
        }

        /**
         *  1、shutdown关闭线程池，这个是一个比较安全的方法
         *  2、shutdown会等待所有任务都执行完毕之后才关闭线程池
         *  3、shutdown方法执行后,线程池就不能在接受新的任务
         *  4、shutdown就相当于发送了一个关闭信号
         */
        myThreadPoolExecution.shutdown();

    }


    private static class MyThreadPoolExecution extends ThreadPoolExecutor{

        public MyThreadPoolExecution(int corePoolSize, int maximumPoolSize, long keepAliveTime, TimeUnit unit, BlockingQueue<Runnable> workQueue) {
            super(corePoolSize, maximumPoolSize, keepAliveTime, unit, workQueue);
        }


        @Override
        protected void beforeExecute(Thread t, Runnable r) {
            System.out.println("调度前....记录日志...");
            super.beforeExecute(t, r);
        }

        @Override
        protected void afterExecute(Runnable r, Throwable t) {
            System.out.println("调度后.....记录日志...");
            super.afterExecute(r, t);
        }

        @Override
        protected void terminated() {
            System.out.println("整个线程池终止调度.....");
            super.terminated();
        }
    }

}



