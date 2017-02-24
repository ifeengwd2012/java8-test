package fn.wd.concurrent.jdk1manager;

/**
 * Created by fengweidong on 2017/1/12.
 *
 */
public class Synchronized2 {

    public static void main(String[] args) {
        Synchronized2 synchronized_1 = new Synchronized2();
        Synchronized2 synchronized_2= new Synchronized2();


        Thread thread1 = new Thread(new Runnable() {
            @Override
            public void run() {
                synchronized_1.print3();
            }
        });

        Thread thread2 = new Thread(new Runnable() {
            @Override
            public void run() {
                //synchronized_2.print4();
                synchronized_1.print4();
            }
        });

        thread1.start();
        thread2.start();


        try {
            thread1.join();
            thread2.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

    private synchronized void print3(){                 //非静态方法中，synchronized锁的是调用此方法的那个对象
        for(int i=0;i<20;i++){
            System.out.println(Thread.currentThread().getName()+"线程正在执行...."+i);
        }
        try {
            Thread.sleep(1000*3);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

    private synchronized void print4(){
        for(int i=0;i<20;i++){
            System.out.println(Thread.currentThread().getName()+"线程正在执行...."+i);
        }
        try {
            Thread.sleep(1000*3);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }


}
