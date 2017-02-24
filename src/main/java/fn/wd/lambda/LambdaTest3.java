package fn.wd.lambda;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;

/**
 * Created by fengweidong on 2016/12/24.
 *
 */
public class LambdaTest3 {


    public static void main(String[] args) {

        List<Apple> apples = Arrays.asList(new Apple("a1", 100l), new Apple("a2", 200l));

        List<Apple> apps=apple1(apples,(a)->a.getWeight()>100);

        System.out.println(apps.size());


    }


    public static List<Apple> apple1(List<Apple> apples, Predicate<Apple> predicate){

        ArrayList<Apple> list = new ArrayList<>();

        for(Apple apple:apples){
            if(predicate.test(apple)){
                list.add(apple);
            }
        }
        return list;

    }

}
