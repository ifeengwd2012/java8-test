package fn.wd.stream;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.stream.Stream;

/**
 * Created by fengweidong on 2016/12/24.
 *  Stream不同的创建方式
 */
public class CreateStream {

    public static void main(String[] args) throws IOException {

        /**
         *  使用集合对象创建
         *
         */

        //基于集合对象(list)创建Stream对象
        List<String> list = Arrays.asList("1", "2", "3", "4");
        Stream<String> stream = list.stream();
        
        //基于集合对象(set)创建stream对象
        HashSet<String> set = new HashSet<>();
        set.add("1");
        set.add("2");
        set.add("3");
        Stream<String> stream1 = set.stream();

        //注意：map不是Collection家族的，所以它没stream方法
        HashMap<String, String> map = new HashMap<>();
        map.put("k1","v1");
        map.put("k2","v2");
        map.put("k3","v3");

        /**
         *
         *  使用数组创建Stream
         */
        String[] strings = {"1", "2", "3", "4"};
        Stream<String> stream2 = Arrays.stream(strings);


        /**
         *  使用Stream的静态方法创建
         *
         */

        //使用Stream的静态方法of()创建
        Stream<String> s1 = Stream.of("1", "2", "3");

        //使用Stream的静态方法generator()创建
        Stream<Integer> s2 = Stream.generate(() -> 100);

        //使用Stream的静态方法iterator()创建
        Stream<Integer> iterate = Stream.iterate(0, n -> n + 2);


        /**
         *  使用Files对象创建stream对象
         *
         */
        Path path = Paths.get("D:\\workspace\\study\\java8-test\\src\\main\\java\\fn\\wd\\stream\\CreateStream.java");
        Stream<String> lines = Files.lines(path);
        lines.forEach((line)->System.out.println(line));

    }
}
