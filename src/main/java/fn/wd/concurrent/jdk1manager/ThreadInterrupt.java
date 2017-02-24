package fn.wd.concurrent.jdk1manager;

/**
 * Created by fengweidong on 2017/1/11.
 *
 */
public class ThreadInterrupt {

    public static void main(String[] args) {

        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                while(true){
                    if(Thread.currentThread().isInterrupted()){
                        System.out.println("线程被打断...");
                        break;
                    }else {
                        System.out.println("正则执行....");
                    }
                    try {
                        this.wait();
                    } catch (InterruptedException e) {

                    }
                }
            }
        });

        thread.start();

        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        thread.interrupt();


        System.out.println("GAME OVER!!!");




    }

}
