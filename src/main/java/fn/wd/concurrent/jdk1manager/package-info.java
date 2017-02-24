/**
 * Created by fengweidong on 2017/1/11.
 *
 */
package fn.wd.concurrent.jdk1manager;

/**
 *
 *
 *  JDK1.0开始就提供的线程控制的方法：
 *
 *      1、ThreadStop.java\ThreadStop2.java
 *          用来测试Thread.stop()方法
 *      2、StopMe.java
 *          自定义线程终止的方法
 *      3、TreadInterrupt.java
 *          thread1.interrupt();//给线程(thread1)发送一个中断的指令,至于线程怎么处理那是线程自己的事情。
 *
 *          Thread.sleep();//这个JDK提供的方法可以捕获中断标志,然后清除中断标志，最后抛出InterruptedException异常
 *          Object.wait(); //这个JDK提供的方法也可以捕获到中断标志,然后清除中断标志，最后抛出InterruptedException异常
 *
 *          Thread.currentThread().isInterrupted();//判断线程是否存在中断标志
 *          Thread.interrupted();//判断线程是否存在中断标志,并清除中断标志
 *
 *          本例就是使用以上的两个方法，手动捕获中断标志作处理。
 *
 *          所以说，如果线程不使用JDK提供的处理中断的方法(sleep()、wait()等)，又不自己处理中断标志，即使thread1.interrupt()调1W次也是不起任何作用的。
 *
 *      4.WaitNotify.java
 *             典型的生产者和消费者的例子...
 *             当调用obj1.wait();后，当前调用的线程，就进入obj1的等待队列，释放当前线程所持有的obj1的锁,当前线程处于等待状态....
 *             obj1的等待队列上可能有很多的线程,即：可能有多个线程等待obj1对象
 *
 *             当调用obj1.notify()；后，会唤醒obj1等待队列中的任意一个线程。
 *
 *             注意:obj1.wait()和obj1.notify()必须包含在对于的synchronized语句块中，必须先获取obj1的锁。
 *
 *      5.SuspendResume.java
 *              暂停、恢复的正常用法..
 *        SuspendResume2.java
 *              不正常的resume、suspend顺序，将有可能导致线程饥饿
 *      6.JoinYield.java
 *              thread1.join  阻塞当前线程，直到目标线程执行完毕。
 *              Thread.currentThread().yield();线程让步，让出cpu的控制权
 *      7.ThreadGroupTest.java
 *              线程组
 *              所有线程创建还能指定它所属的组
 *      8.DaemonThread.java
 *              线程只是程序的执行流程，没有父子、高低之分，main线程退出对其他用户线程不造成任何影响,其他线程仍然会继续执行
 *              当用户线程执行完毕的时候，守护线程自动退出...
 *      9.Synchronized1.java
 *              synchronized直接作用于类的静态方法，相当于对类对象加锁，进入同步代码需要获取当前类对象(class对象)的锁。
 *        Synchronized2.java
 *               synchronized_1.print3();和synchronized_2.print4();不存在竞争，会并发执行
 *               synchronized_1.print3();和synchronized_1.print4();存在竞争，不会并发执行
 *        Synchronized3.java
 *                synchronized_3.print5();和synchronized_4.print6();存在竞争，不会并发执行
 *                synchronized_3.print5();和synchronized_4.print6();不存在竞争，会并发执行
 *        ------->
 *               以上，分别阐述synchronized三种不同的加锁方式;
 *      10.ArrayListTest.java
 *              ArrayList在多线程的情况下会出现问题
 *              类似的还有:HashMap
 *
 *
 *
 */










