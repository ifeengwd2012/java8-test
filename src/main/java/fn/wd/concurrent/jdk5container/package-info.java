/**
 * Create Time: 2017-01-14 21:15
 * Created by fengweidong.
 */
package fn.wd.concurrent.jdk5container;

/**
 *
 *      一、本包主要测试JDK5提供的并发容器
 *
 *          1、Map的安全
 *              1）、Collections工具类，可以将任意集合类封装为线程安全的集合
 *                  Map map=Collections.synchronizedMap(new HashMap());
 *                  返回的其实是：SynchronizedMap，将所有map的功能交给HashMap实现，而自己负责线程安全。
 *
 *                  并发级别不高，一般够用！但是并发级别很高，效率很低。
 *              2）、
 *                  ConcurrentHashMap
 *                  它可以看做是，专业的高并发的HashMap
 *          2、List的安全
 *              1）ArrayList和Vector都是使用【数组】作为内部实现的List
 *                  ArrayList线程不安全
 *                  Vector线程安全
 *              2）LinkedList使用【链表】作为内部实现的List
 *                  LinkedList不是线程安全的。
 *                  使用Collections工具类将其包装为线程安全的。
 *                  List list=Collections.synchronizedList(new LinkedList());
 *              3）ConcurrentLinkedQueue
 *                  a).这个队列使用链表作为其数据结构
 *                  b).高并发中性能最好的队列、高效的读写队列
 *                  c).性能好是因为内部实现复杂
 *              4) CopyOnWriteArrayList
 *                  a).读尽可能的快、写慢一点也无所谓（这适用于读的次数远大于写的次数的场景）
 *                  b).这个集合实现：
 *                          读不需要锁；
 *                          读读之间、读写之间可以并发；
 *                          写写不能并发
 *
 *                          读写之间可以并发的原理：
 *                          写的时候，将集合复制一份，这样保证多个读线程读的都是同样的数据？？？？
 *                          修改完成后，读取线程可以立即察觉到这个修改。volatile!!
 *               5) BlockingQueue
 *                  a).用于多线程间的数据共享【“意见箱”】
 *                  b).这个是个接口，并非具体的实现：
 *                      ArrayBlockQueue基于数组实现的，适合做“有界队列”，最大元素需要在队列创建时指定；
 *                      LinkedBlockQueue基于链表实现的，适合做“无界队列”，内部元素动态添加，不会因为初始容量很大，一口气吃掉很多内存；
 *                  c)."Blocking"是它作为数据共享通道的关键
 *                      服务线程（消费队列中消息的线程）处理完成队列中的消息后，如何获取下一条消息？
 *                          -->按照一定间隔不停的循环和监控这个队列。low!!!
 *                          -->BlockingQueue会让服务线程在队列为空时，进行等待。当有消息进入队列后，自动将其唤醒。
 *                  d).ArrayBlockQueue
 *                      put();//将元素压入队列末尾，如果队列满了，它会一直等待，直到队列中有空闲的位置。
 *                      offer();//当队列已经满了，立即返回false；否则执行正常的入队操作；
 *
 *                      take();//队列为null时，等待；否则直接返回；
 *                      poll();//队列为null时，直接返回null;
 *
 *                     【 put()\take()才是体现blocking的关键。】
 *
 *              6) ConcurrentSkipListMap
 *                      基于跳表实现的Map，跳表输出是有序的
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 */








