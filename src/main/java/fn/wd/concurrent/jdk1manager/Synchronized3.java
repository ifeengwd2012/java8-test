package fn.wd.concurrent.jdk1manager;

/**
 * Created by fengweidong on 2017/1/12.
 *
 */
public class Synchronized3 {

    public static void main(String[] args) {

        Synchronized3 synchronized_3 = new Synchronized3();
        Synchronized3 synchronized_4 = new Synchronized3();

        Thread thread1 = new Thread(new Runnable() {
            @Override
            public void run() {
                synchronized_3.print5();
            }
        });

        Thread thread2 = new Thread(new Runnable() {
            @Override
            public void run() {
                //synchronized_3.print6();
                synchronized_4.print6();
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


    public void print5(){
        synchronized (this){
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

    private void print6(){
        synchronized (this){
            for(int i=0;i<20;i++){
                System.out.println(Thread.currentThread().getName()+"线程正在执行...."+i);
            }
            try {
                Thread.sleep(1000*2);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
