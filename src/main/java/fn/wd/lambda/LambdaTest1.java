package fn.wd.lambda;

import java.util.function.*;

/**
 * Created by fengweidong on 2016/12/24.
 *
 * lambda表达式
 *
 */
public class LambdaTest1 {

    public static void main(String[] args){

        /**
         *  一个入参，一个返回值-->Function
         *
         */
        Function<String,Integer> function = (String s) -> s.length();
        /**
         *
         *  两个入参，一个返回值--->BiFunction
         *
         */
        BiFunction<String, Integer,Character> stringIntegerBiConsumer = (String s1, Integer s2) -> s1.charAt(s2);
        /**
         *
         *  一个入参，无返回值--->Consumer
         *
         */
        Consumer<String> consumer = (String s) -> System.out.println(s);
        /**
         *
         *  两个入参，无返回值---->BiConsumer
         *
         */
        BiConsumer<String, String> biConsumer = (String s1, String s2) -> System.out.println(s1 + "," + s2);
        /**
         *
         *  一个入参，返回值是boolean---->Predicate
         *
         */
        Predicate<String> stringStringBiConsumer = (String s1) -> s1.equals(100);
        /**
         *
         *  无入参，有返回值----->Supplier
         *
         */
        Supplier<String> supplier = () -> new String("11");


    }


}
