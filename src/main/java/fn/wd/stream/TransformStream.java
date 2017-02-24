package fn.wd.stream;

import fn.wd.lambda.Apple;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

/**
 * Created by fengweidong on 2016/12/24.
 *
 */
public class TransformStream {

    public static void main(String[] args) {
        List<Apple> list = Arrays.asList(
                new Apple("a1",100l),new Apple("b2",101l),
                new Apple("a2",99l),new Apple("a0",103l),
                new Apple("c1",88l),new Apple("a3",22l),
                new Apple("b4",100l),new Apple("a3",22l)
        );

        Stream<Apple> stream = list.stream();

        long count = stream
                .filter((apple) -> apple.getWeight() > 10)
                .distinct()
                .sorted((a1, a2) -> a1.getWeight().compareTo(a2.getWeight()))
                .count();

        System.out.println(count);

        System.out.println("==================================");

        Stream<Apple> stream1 = list.stream();
        stream1
                .map(apple -> apple.getName())
                .forEach(apple->System.out.println(apple));


    }
}
