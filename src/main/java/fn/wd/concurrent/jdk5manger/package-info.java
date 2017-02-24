/**
 * Created by fengweidong on 2017/1/12.
 *
 */
package fn.wd.concurrent.jdk5manger;

/**
 *      一、本包介绍JDK5提供的同步控制工具
 *          1、ReentrantLockTest.java
 *                重入锁的基本用法
 *             ReentrantLockTest2.java
 *                  ***重入锁提供的，区别于synchronized的高阶功能。
 *                      *对于synchronized来说，如果一个线程在等待锁，那么
 *                           * 它要么获得所继续执行
 *                           * 它要么等待锁
 *                      *对于ReentrantLock来说，如果一个线程在等待锁，那么
 *                           * 它要么获得所继续执行
 *                           * 它要么等待锁
 *                           * 它要么被打断（释放锁的动作必须手动处理） 【响应中断响应】
 *              ReentrantLockTest3.java
 *                    **锁申请等待限时
 *                          避免死锁的另一种方式，给定一个等待时间,让线程自动放弃；
 *                    **锁申请立即返回
 *                          申请成功返回true,否则返回false.
 *              ReentrantLockTest3.java
 *                    **公平锁
 *                      一般来说，锁是不公平的,当多个线程依次申请某锁时,当锁可用的时候，会随机给一个线程；
 *                      公平锁，维护一个等待队列，当锁可用的时候，先申请的，先使用；这样就不会产生饥饿现象
 *
 *          回过头来,再来讨论重入锁是干嘛的？
 *              【重入锁就是synchronized的拓展】,并且支持更加高级的功能:比如响应中断、等待限时、锁申请立即返回、公平锁等。
 *
 *         2、ConditionTest.java
 *              使用重入锁实现的生产者和消费者
 *              【内部锁synchronized】的搭档：Object.wait()  Object.notify()
 *              【重入锁ReentrantLock】的搭档：Condition
 *
 *              reentrantLock.newCondition();//获取和指定重入锁相关的condition对象
 *
 *              condition.await();//释放锁等待，直到获取锁才返回
 *
 *              condition.signalAll();//通知在该锁上等待的线程去竞争锁
 *
 *          总结：无论是内部锁还是外部锁，效果都是一样的，在同一时间只允许一个线程访问一个资源。
 *
 *
 *          3、SemaphoreTest.java
 *              信号量是对锁的拓展，允许指定个线程同时访问一个资源（而锁是同时只允许一个访问资源）
 *
 *              信号量：限制同时执行执行某段代码的线程数。
 *
 *          4、ReadWriteLockTest.java
 *              锁的进步一增强，读写分离锁。
 *              读写锁允许多个线程同时读，读写、写写之间是需要相互等待和持有锁的。
 *
 *              读写分离锁，对于高并发的读是很有帮助的。
 *
 *          5、CountDownLatchTest.java
 *              CountDownLatch线程控制的工具类，可以让一个线程等待其他线程完成特定的事情后再继续执行
 *
 *          6、CyclicBarrierTest.java
 *              CyclicBarrier循环屏障，设置计数值，每调用一次await(),会进行一次计数，并且当前线程会在此方法上等待，直到计数值等于设定值，
 *              然后计数值会重置，重新进行下一轮的等待。
 *
 *          7、LockSupportTest.java
 *              LockSupport线程阻塞的工具，可以让线程在任意的位置
 *              和Thread.suspend()相比：弥补了由于Thread.resume()在前，导致线程无法继续执行且不释放资源的情况
 *              和Object.wait()相比：它不需要获取某个对象的锁，也不会抛InterruptException
 *
 *              LockSupport为每一个线程准备了一个许可：
 *                      LockSupport.unpark()会将许可变为可用
 *                      LockSupport.part()如果许可可用，会消费许可，将许可变为不可用；如果不可用，会阻塞
 *
 *                  当unpart()在前执行,part()在后执行,后者会消费许可，立即返回；
 *                  当part()在前执行，unpart()在后执行,前者会阻塞，当unpart()调用后，part()才会退出阻塞。
 *
 *                  LockSupport.partNanos();//定时阻塞
 *                  LockSupport.partUtil(); //定时阻塞
 *
 *                  LookSupport.part(object);//object会出现在dump中方便分析问题
 *
 *                  LookSupport不会抛出InterruptException异常,会默默返回，但不吃掉这个打断，可以通过Thread.interrupted()查看
 *
 *
 *
 */

