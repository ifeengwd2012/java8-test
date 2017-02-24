package fn.wd.concurrent.jdk5executor;

import java.util.concurrent.*;

/**
 * Create Time: 2017-01-13 17:58
 * Created by fengweidong.
 *   workQueue:指的是被提交但未执行的任务队列；用来存放被提交的Runnable对象
 */
public class BlockQueueTest{

    public static void main(String[] args) {

        /**
         *  SynchronousQueue——直接提交的队列
         *  ·SynchronousQueue没有容量，每一个插入操作都要等待一个删除操作，反之，每一个删除操作都要等待一个插入操作
         *  ·所以提交的任务不会被真正保存，总是交给线程来处理，如果没有空闲线程，会创建新的线程，如果线程数量达到max,则会执行拒绝策略
         *  ·由于这个队列的特性，max需要设置很大的值，否则很容器执行拒绝策略
         *  ·科普：拒绝策略并不一定是拒绝执行任务。
         *
         */
        ThreadPoolExecutor pool_1 = new ThreadPoolExecutor(2,10,1, TimeUnit.MINUTES,new SynchronousQueue<Runnable>());

        /**
         *  ArrayBlockingQueue——有界的任务队列(队列是有大小的，不是无限大)
         *    · 当使用有界队列时，若有新任务需要执行，如果实际线程数<core线程数,则会创建线程；实际线程数>core线程数,则会将任务添加到等待队列
         *    · 如果等待队列已满，但实际线程数<max线程数，创建新线程执行;若实际线程数>=max线程数,则执行拒绝策略
         *    · 有界队列，仅在任务队列装满时，才会将实际线程数提高到corePoolSize以上
         */
        ThreadPoolExecutor pool_2 = new ThreadPoolExecutor(2,10,1, TimeUnit.MINUTES,new ArrayBlockingQueue<Runnable>(10));

        /**
         *  LinkedBlockingQueue----无界的任务队列(队列无限大，可以接受任意数量的任务)
         *      · 除非资源耗尽，否则不存在任务入队失败的问题
         *      · 由于队列是无界的，所以线程池中的线程数最大是corePoolSize,maximumPoolSize形同虚设
         *      · 若任务创建和任务处理的速度差异很大，无界队列会保持快速增长，直到耗尽系统内存
         */
        ThreadPoolExecutor pool_3 = new ThreadPoolExecutor(2,10,1, TimeUnit.MINUTES,new LinkedBlockingQueue<Runnable>(10));

        /**
         *  PriorityBlockingQueue——优先任务队列
         *      · 它是一个无界队列
         *      · 它不是按照先进先出的算法处理任务的
         *      · 它是根据任务自身的优先级顺序处理
         *
         */
        ThreadPoolExecutor pool_4 = new ThreadPoolExecutor(2,10,1, TimeUnit.MINUTES,new PriorityBlockingQueue<Runnable>(10));

    }

    private static class printMess implements Runnable{

        @Override
        public void run() {
            System.out.println(Thread.currentThread().getName()+"正在执行操作....");
        }
    }

}
