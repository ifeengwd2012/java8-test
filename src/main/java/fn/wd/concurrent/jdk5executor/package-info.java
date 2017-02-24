/**
 * Create Time: 2017-01-13 16:43
 * Created by fengweidong.
 */
package fn.wd.concurrent.jdk5executor;

/**
 *
 *      一、本包主要测试JDK5.0之后的Executor框架
 *
 *      首先介绍Executor框架结构
 *         ·Executor接口
 *          *execute(Runnable runnable);
 *          *此接口只有这么一个方法，解耦了任务的执行和调度细节。
 *
 *             ·ExecutorService接口
 *              *shutdown()、isShutdown()、isTerminated()、submit()
 *              *此接口包含一些的管理方法
 *
 *                  ·AbstractExecutorService类
 *                   *此抽象类是ExecutorService的抽象实现
 *
 *                         ·ThreadPoolExecutor类
 *                          *此类是ExecutorService可配置的实现
 *
 *                   ·ScheduledExecutorService类
 *                     *增加了定时、延迟执行的功能
 *
 *                          ·ScheduledThreadPoolExecutor
 *                           *楼上的池化实现
 *
 *          ·Executors
 *              它和Executor的关系就好比Collection和Collections的关系。
 *
 *      1、Executors.java
 *          测试Executors的用法
 *      2、ThreadPoolExecutorTest.java
 *          Executors底层是使用ThreadPoolExecutor来创建的线程池；
 *          ThreadPoolExecutor创建线程池需要一些参数：
 *              corePoolSize:
 *              maximumPoolSize:
 *              keepAliveTime:
 *              keepAliveUnit:
 *              workQueue:
 *              threadFactory:这个是用来创建线程池里面的线程，一般用默认的即可
 *              handler:拒绝策略；当任务太多来不及处理时，对多余的任务的处理办法
 *      3、BlockQueueTest.java
 *              分析不同功能的等待队列
 *
 *           a).public static ExecutorService newFixedThreadPool(int nThreads) {
 *                       return new ThreadPoolExecutor(nThreads, nThreads, 0L, TimeUnit.MILLISECONDS,new LinkedBlockingQueue<Runnable>())};
 *              corePoolSize和maximumPoolSize大小一样：因为线程池大小固定，所以这两个值也就固定；
 *              使用了LinkedBlockingQueue作为任务等待队列：使用无界队列
 *
 *           b).public static ExecutorService newSingleThreadExecutor() {
 *                      return new FinalizableDelegatedExecutorService (new ThreadPoolExecutor(1, 1, 0L, TimeUnit.MILLISECONDS, new LinkedBlockingQueue<Runnable>())); }
 *              corePoolSize和maximumPoolSize大小一样：都为1
 *              是newFixedThreadPool的退化。。。
 *
 *           c).public static ExecutorService newCachedThreadPool() {
 *                   return new ThreadPoolExecutor(0, Integer.MAX_VALUE,60L, TimeUnit.SECONDS,new SynchronousQueue<Runnable>());}
 *               corePoolSize:0
 *               maximumPoolSize:最大
 *               SynchronousQueue：使用直接提交的队列
 *               任务执行完成后，由于corePoolSize==0,所有空闲线程会在60s会被回收
 *
 *     4、RejectStrategy.java
 *             测试JDK提供的拒绝策略：
 *                  AbortPolicy策略：直接抛异常，阻止系统正常运行；
 *                  CallerRunsPolicy策略：在调用者线程中，运行当前被丢弃的任务，可能会导致性能极具下降；
 *                  DiscardOldestPolicy策略：丢弃最老的请求(即将要执行的任务)，执行提交当前任务；
 *                  DiscardPolicy策略：默默丢弃无法处理的任务，不进行任务的额外处理；
 *
 *            自定义拒绝策略：
 *                  实现RejectExecutionHandler接口：
 *
 *     5、ThreadFactoryTest.java
 *              自定义线程创建工厂
 *
 *
 *     二、Fork\Join框架
 *             1、fork\join体现的分治思想
 *             2、
 *                1).毫无顾忌的使用fork开启线程很可能导致系统开启过多的线程而严重影响性能；
 *                2).fork()不着急开启线程，而是交给线程池(ForkJoinPool),以节省系统资源
 *                3).可以向ForkJoinPool提交ForkJoinTask
 *             3、ForkJoinTask的两个子类：
 *                1).RecursiveAction，子任务无返回值
 *                2).RecursiveTask，子任务有返回值
 *             4、
 *                  调用层次深的话，可能会导致异常
 *
 *
 *
 */
