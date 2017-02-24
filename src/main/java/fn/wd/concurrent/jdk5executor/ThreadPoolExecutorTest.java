package fn.wd.concurrent.jdk5executor;

import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * Create Time: 2017-01-13 17:44
 * Created by fengweidong.
 */
public class ThreadPoolExecutorTest {

    public static void main(String[] args) {

        /**
         *  corePoolSize:  核心线程数
         *  maximumPoolSize:    最大线程数
         *  keepAliveTime:  线程数超过核心线程数后，多余的线程的活跃时长
         *  keepAliveTimeUnit:  活跃时长的单位
         *  workQueue:  保存已提交但没有线程来做的任务队列
         *
         */
        ThreadPoolExecutor poolExecutor_1 = new ThreadPoolExecutor(2,10,200, TimeUnit.SECONDS, new LinkedBlockingQueue<Runnable>());

        for(int i=0;i<3;i++){
            poolExecutor_1.execute(new PrintMessage());         //execute()不会导致产生大于corePoolSize个数的线程
        }

    }

    private static class PrintMessage implements Runnable{

        @Override
        public void run() {
            System.out.println(Thread.currentThread().getName()+"线程正在执行.....");
            try {
                Thread.sleep(3*1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }


}
