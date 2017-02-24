package fn.wd.functiondeduce;

import fn.wd.lambda.Apple;

import java.util.function.*;

/**
 * Created by fengweidong on 2016/12/24.
 * 函数推导
 */
public class FunctionDeduce {

    public static void main(String[] args) {
        /**
         *  静态方法，函数推导
         *
         */
        DoubleConsumer doubleConsumer = Math::abs;


        /**
         *
         *  类方法函数推导,第一种方式
         *
         */
        Function<String, Integer> function = String::length;

        /**
         *  类方法函数推导,第二种方式
         *
         */
        String string = "111";
        Supplier<Integer> supplier = string::length;


        /**
         *
         *  构造函数的函数推导
         *
         */
        Supplier<String> f1 = String::new;
        Function<String,String> f2 = String::new;
        BiFunction<String,Long,Apple> f3=Apple::new;
        TriFunction<String,String,Long,Orange> f4=Orange::new;
    }

}
