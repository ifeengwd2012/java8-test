package fn.wd.stream;

import fn.wd.functiondeduce.Orange;
import fn.wd.lambda.Apple;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.*;

/**
 * Created by fengweidong on 2016/12/28.
 *      Stream概述：支持顺序的和并行的集合操作的元素序列。
 */
public class StreamAPI {

    public static void main(String[] args) {

        List<Apple> apples = Arrays.asList(new Apple("a1", 100l), new Apple("a1", 100l)
                , new Apple("a3", 103l), new Apple("a4", 104l)
                , new Apple("b1", 201l), new Apple("b2", 202l)
                , new Apple("b3", 203l), new Apple("b4", 204l));

        /**
         *  Stream<T> filter(Predicate<? super T> predicate)
         *
         */
        Stream<Apple> stream1 = apples.stream();
        Stream<Apple> appleStream = stream1.filter(a -> a.getWeight() > 103);


        /**
         * <R> Stream<R> map(Function<? super T, ? extends R> mapper);
         *
         */
        Stream<Apple> stream2 = apples.stream();
        Stream<String> stringStream = stream2.map(a -> a.getName());

        /**
         *  IntStream mapToInt(ToIntFunction<? super T> mapper);
         *
         */
        Stream<Apple> stream3 = apples.stream();
        IntStream intStream = stream3.mapToInt(a -> a.getWeight().intValue());

        /**
         *   LongStream mapToLong(ToLongFunction<? super T> mapper);
         *
         */
        Stream<Apple> stream4 = apples.stream();
        LongStream longStream = stream4.mapToLong(Apple::getWeight);//replace lambda to Method reference

        /**
         *  DoubleStream mapToDouble(ToDoubleFunction<? super T> mapper);
         *
         */
        apples.stream().mapToDouble(Apple::getWeight);


        List<Orange> oranges = Arrays.asList(new Orange("o1", "orange", 300L), new Orange("o1", "orange", 302L)
                , new Orange("o1", "orange", 303L), new Orange("o1", "orange", 304L)
                , new Orange("o1", "orange", 305L), new Orange("o1", "orange", 306L));

        ArrayList<List> objects = new ArrayList<>();
        objects.add(apples);
        objects.add(oranges);

        /**
         *   <R> Stream<R> flatMap(Function<? super T, ? extends Stream<? extends R>> mapper);
         *
         *   如果说map()是将Sequence of elements 转换为另一个Sequence of elements
         *   那么flatMap()就是将Stream转换为另一个Stream,最后将这些Stream合并
         *
         */
        objects.stream().flatMap(innerList -> innerList.stream().map(o -> {
            if (o instanceof Orange) {
                return ((Orange) o).getWeight();
            } else if (o instanceof Apple) {
                return ((Apple) o).getWeight();
            }
            return null;
        })).forEach(System.out::println);

        /**
         *     Stream<T> distinct();
         *
         */
        long count = apples.stream().distinct().count();
        System.out.println(count);

        /**
         *   Stream<T> sorted();
         */
        apples.stream().sorted();//If the elements of this stream are not Comparable, a ClassCastException may be thrown when the terminal operation is executed.
        //apples.stream().sorted().count();//error


        /**
         *  Stream<T> sorted(Comparator<? super T> comparator);
         */
        apples.stream().sorted((a1, a2) -> a1.getWeight().compareTo(a2.getWeight())).count();
        System.out.println("======================================================");

        /**
         *  Stream<T> peek(Consumer<? super T> action);
         *
         */
        Stream.of("one", "two", "three", "four")
                .filter(e -> e.length() > 3)
                .peek(e -> System.out.println("Filtered value: " + e))
                .map(String::toUpperCase)
                .peek(e -> System.out.println("Mapped value: " + e))
                .collect(Collectors.toList());

    }
}
