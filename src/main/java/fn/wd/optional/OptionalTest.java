package fn.wd.optional;

import java.util.Arrays;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

/**
 * Created by fengweidong on 2016/12/26.
 *
 */
public class OptionalTest {

    public static void main(String[] args) {

        /**
         * Optional的第一种创建方式
         */
        Optional<Survery> empty = Optional.empty();

        //empty.get();//error

        System.out.println(empty.isPresent());//false

        System.out.println(empty.orElse(new Survery()));

        System.out.println(empty.orElseGet(()->new Survery()));

        //System.out.println(empty.orElseThrow(()->new NoSuchElementException("无")));

        System.out.println("==============================================");

        /**
         * Optional的第二种创建方式
         *
         */
        Question question = new Question(1001, "你喜欢的编程语言?", "A:java B:c++");
        Page page = new Page(101, question);
        Survery survery = new Survery(11, page);

        Optional<Survery> optional1 = Optional.of(survery);
        optional1.ifPresent(o->System.out.println(o));

        //Optional.of(null);//error

        /**
         * Optional的第三种创建方式
         *
         */
        Question question2 = new Question(1002, "你喜欢的编程语言?", "A:java B:c++");
        Page page2 = new Page(101, question2);
        Survery survery2 = new Survery(11, page2);

        Optional<Survery> optional3 = Optional.ofNullable(survery2);


        //此Optional.map和Stream中的map还是有些不一样的
        //注意到：此处的map虽然也是需要一个Function,但是它给apply()传的值不是this,而是this.value[即里面封装的对象]
        //注意到：并且，返回值仍然封装在Optional中
        Optional<Page> page1 = optional3.map(s -> s.getPage());

        //flatMap和Map的区别是：
        optional3.flatMap(s->Optional.ofNullable(s.getPage()));

    }

}
