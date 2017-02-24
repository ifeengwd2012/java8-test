package fn.wd.concurrent.jdk1manager;

/**
 * Created by fengweidong on 2017/1/11.
 *
 */
public class SuspendResume {

    public static void main(String[] args) {

        Thread print = new Thread(new Runnable() {
            @Override
            public void run() {

                for (int i = 0; i < 20; i++) {
                    System.out.println("当前打印...." + i);
                    if(i==10){
                        System.out.println("准备暂停print线程....");
                        Thread.currentThread().suspend();
                    }
                }

            }
        });

        print.start();

        try {
            Thread.sleep(1000*2);
            System.out.println("等待....");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("准备恢复print线程....");
        print.resume();


        try {
            print.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("GAME OVER!!!");


    }

}
