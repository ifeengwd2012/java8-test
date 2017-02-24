package fn.wd.concurrent.jdk1manager;

/**
 * Created by fengweidong on 2017/1/12.
 */
public class DaemonThread {

    public static void main(String[] args) {

        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {

                for(int i=0;i<100;i++){
                    System.out.println("正在打印...."+i);
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }

            }
        });

        thread.setDaemon(true);             //分别将这段代码打开或关闭查看运行结果....

        thread.start();


        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {

        }

        System.out.println("GAME OVER!!!");

    }
}
