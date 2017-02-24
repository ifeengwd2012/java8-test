package fn.wd.collector;

import fn.wd.lambda.Apple;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.concurrent.ConcurrentMap;
import java.util.stream.Collectors;

/**
 * Created by fengweidong on 2017/1/10.
 *
 * Collectors的reduce\summary类型的操作
 *
 */
public class ReduceSummary {

    public static void main(String[] args) {
        //init list
        List<Apple> apples = Arrays.asList(new Apple("a1", 100L), new Apple("a1", 100L)
                , new Apple("a3", 103L), new Apple("a4", 104L)
                , new Apple("b1", 201L), new Apple("b2", 202L)
                , new Apple("b3", 203L), new Apple("b4", 204L));

        //
        testAveragingDouble(apples);
        System.out.println("=========================================");

        //
        testAveragingInt(apples);
        System.out.println("=========================================");

        //
        testAveragingLong(apples);
        System.out.println("=========================================");

        //
        testCollectingAndThen(apples);
        System.out.println("=========================================");

        //
        testGroupingBy(apples);
        System.out.println("=========================================");

        //
        testGroupingBy2(apples);
        System.out.println("=========================================");

        //
        testGroupingBy3(apples);
        System.out.println("=========================================");

        //
        testGroupingByConcurrent(apples);
        System.out.println("=========================================");

        //
        testGroupingByConcurrent2(apples);
        System.out.println("=========================================");

        //
        testGroupingByConcurrent3(apples);
        System.out.println("=========================================");

    }

    private static void testAveragingDouble(List<Apple> list){
        System.out.println("testAveragingDouble");
        Double collect = list.stream().collect(Collectors.averagingDouble(Apple::getWeight));
        System.out.println(collect);

    }

    private static void testAveragingInt(List<Apple> list){
        System.out.println("testAveragingInt");
        Double collect = list.stream().collect(Collectors.averagingInt(a -> a.getWeight().intValue()));
        System.out.println(collect);
    }

    private static void testAveragingLong(List<Apple> list){
        System.out.println("testAveragingLong");
        Double collect = list.stream().collect(Collectors.averagingLong(Apple::getWeight));
        System.out.println(collect);
    }

    private static void testCollectingAndThen(List<Apple> list){
        System.out.println("testCollectingAndThen");
        String collect = list.stream().collect(Collectors.collectingAndThen(Collectors.averagingLong(Apple::getWeight), av -> "these apple average weight is " + av));
        System.out.println(collect);
    }

    private static void testGroupingBy(List<Apple> list){
        System.out.println("testGroupingBy");
        Map<String, List<Apple>> collect = list.stream().collect(Collectors.groupingBy(Apple::getName));
        System.out.println(collect);
    }

    private static void testGroupingBy2(List<Apple> list){
        System.out.println("testGroupingBy2");
        Map<String, Long> collect = list.stream().collect(Collectors.groupingBy(Apple::getName, Collectors.counting()));
        System.out.println(collect);
    }

    private static void testGroupingBy3(List<Apple> list){
        System.out.println("testGroupingBy3");
        Map<String, Long> collect = list.stream().collect(Collectors.groupingBy(Apple::getName, TreeMap::new,Collectors.counting()));
        System.out.println(collect);
    }

    private static void testGroupingByConcurrent(List<Apple> list){
        System.out.println("testGroupingByConcurrent");
        ConcurrentMap<String, List<Apple>> collect = list.stream().collect(Collectors.groupingByConcurrent(Apple::getName));
        System.out.println(collect);
    }

    private static void testGroupingByConcurrent2(List<Apple> list){
        System.out.println("testGroupingByConcurrent");
        ConcurrentMap<String, Long> collect = list.stream().collect(Collectors.groupingByConcurrent(Apple::getName, Collectors.counting()));
        System.out.println(collect);
    }

    private static void testGroupingByConcurrent3(List<Apple> list){//no prefect
        System.out.println("testGroupingByConcurrent");
        ConcurrentMap<String, List<Apple>> collect = list.stream().collect(Collectors.groupingByConcurrent(Apple::getName));
        System.out.println(collect);
    }






}
