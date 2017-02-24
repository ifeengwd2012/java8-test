package fn.wd.concurrent.jdk1manager;

/**
 * Created by fengweidong on 2017/1/11.
 *
 */
public class StopMe {

    private static boolean flag=false;

    public static void main(String[] args) throws InterruptedException {

        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                while (true) {
                    if (flag) {
                        return;
                    }
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.println("正在执行....");
                }

            }
        });

        thread.start();


        Thread.sleep(20*1000);

        //自定义线程终止...
        stopme();

        thread.join();//不加它,可能会导致"GAME OVER!!!"和"正则执行...."的乱序....

        System.out.println("GAME OVER!!!");

    }

    private static void stopme(){
        flag=true;
    }

}
