package fn.wd.collector;

import java.util.*;
import java.util.function.BiConsumer;
import java.util.function.BinaryOperator;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Collector;

/**
 * Created by fengweidong on 2017/1/10.
 *
 * String------->Stream sequence element type
 * List<String>------>mutable accumulation type
 * List<String>-------->result type
 */
public class CustomCollector implements Collector<String,List<String>,List<String>>{


    @Override
    public Supplier<List<String>> supplier() {
        System.out.println("supplier---------->返回mutable accumulation type,即：容器对象");
        return ArrayList::new;
    }

    @Override
    public BiConsumer<List<String>, String> accumulator() {
        System.out.println("accumulator------------>返回累加的具体实现");
        return (list,s)->list.add(s);
    }

    @Override
    public BinaryOperator<List<String>> combiner() {
        System.out.println("combiner--------------->返回不同的cpu计算结果的处理方法");
        return (list1,list2)->{list1.addAll(list2);return list1;};
    }

    @Override
    public Function<List<String>, List<String>> finisher() {
        System.out.println("finisher---------------->返回最终的处理方法");
        return (list)->{
           list.add("zhang xiao san");
            return list;
        };
    }

    @Override
    public Set<Characteristics> characteristics() {
        System.out.println("characteristics-------------->");
        return Collections.unmodifiableSet(EnumSet.of(Characteristics.CONCURRENT));
    }
}
