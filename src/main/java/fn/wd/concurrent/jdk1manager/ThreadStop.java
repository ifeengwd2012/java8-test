package fn.wd.concurrent.jdk1manager;

/**
 * Created by fengweidong on 2017/1/11.
 *
 *  Thread.stop()线程终止的方法,可以会导致数据不一致
 *
 *  业务需求：当i!=j时，表示数据不正常。
 *
 *  本例，可以模拟Thread.stop()导致数据不一致的问题。
 *
 *  但本例并不能说明stop()导致数据不一致的根本原因
 *
 */
public class ThreadStop {

    private static String i = "0";
    private static String j = "0";

    public static void main(String[] args) throws InterruptedException {


        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                i = "1";
                try {
                    Thread.sleep(100 * 1000);
                } catch (InterruptedException e) {
                }

                j = "1";
            }
        });


        thread.start();

        Thread.sleep(10*1000);

        thread.stop();

        System.out.println("当前i的值是:"+i);
        System.out.println("当前j的值是:"+j);

    }

}
