package fn.wd.collector;

import fn.wd.lambda.Apple;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by fengweidong on 2017/1/10.
 */
public class CustomCollect {

    public static void main(String[] args) {

        //init list
        List<String> strings = Arrays.asList("a1", "a2", "a3", "b1", "b2", "b3", "b4","a1","a2");

        //
        CustomCollector customCollector = new CustomCollector();

        List<String> collect = strings.stream().collect(customCollector);

        System.out.println(collect);

        System.out.println("===================================");

        Long collect1 = strings.stream().collect(Collectors.counting());

    }

}
