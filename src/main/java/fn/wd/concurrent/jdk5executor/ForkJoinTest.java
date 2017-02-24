package fn.wd.concurrent.jdk5executor;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ForkJoinTask;
import java.util.concurrent.RecursiveTask;

/**
 * Create Time: 2017-01-14 20:12
 * Created by fengweidong.
 */
public class ForkJoinTest {

    public static void main(String[] args) throws ExecutionException, InterruptedException {

        /**
         * 创建数列求和任务
         */
        int[] source = {1,2,3,4,5,6,7,8,9,10,1,2,3,4,5,6,7,8,9,10,1,2,3,4,5,6,7,8,9,10};
        MyRecursiveTask task = new MyRecursiveTask(15, source, 0, 30);

        //MyRecursiveTask task = new MyRecursiveTask(5, source, 0, 30);//这样就报错了，因为调用层次有点深了

        /**
         * 创建fork\join线程池
         */
        ForkJoinPool pool = new ForkJoinPool();

        /**
         * 将任务提交给线程池来执行
         *
         */
        ForkJoinTask<Integer> tasks = pool.submit(task);

        /**
         * 在get方法返回结果之前，该方法阻塞
         */
        Integer result = tasks.get();
        System.out.println("最终结果:"+result);


    }

    /**
     *  数列求和。。。。。。需要返回值
     *          选择RecursiveTask作为任务模型
     *
     */
    public static class MyRecursiveTask extends RecursiveTask<Integer>{

        private int threshold;
        private int[] source;
        private int start;
        private int end;

        public MyRecursiveTask(int threshold, int[] source, int start, int end) {
            this.threshold = threshold;
            this.source = source;
            this.start = start;
            this.end = end;
        }

        @Override
        protected Integer compute() {

            boolean canCompute  = end - start <= threshold;
            int temp = 0;

            //判断是否需要子任务的拆分
            if(canCompute){
                for(int i=start;i<end;i++){
                    temp+=source[i];
                }
            }else{
                /**
                 *  根据业务情况将任务拆分
                 *      拆分为相同类型的小规模的任务
                 */
                int mid = (end -start)/2;
                MyRecursiveTask task_1 = new MyRecursiveTask(threshold, source, start, mid);
                MyRecursiveTask task_2 = new MyRecursiveTask(threshold, source, mid, end);

                /**
                 * fork()提交子任务，至于是否需要创建线程，由ForkJoinPool来决定
                 */
                task_1.fork();
                task_2.fork();

                /**
                 * 等待所有子任务结束
                 */
                temp+=task_1.join();
                temp+=task_2.join();
            }

            return temp;
        }
    }

}
